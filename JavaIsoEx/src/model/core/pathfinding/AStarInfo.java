/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.pathfinding;

import java.awt.Point;
import java.util.LinkedList;
import model.core.Grid;
import model.core.Square;

/**
 *
 * @author Wouter
 */
public class AStarInfo {

  /**
   * Variable to determine scoring for the AStar pathfinding algorithm
   */
  private int score = 0;
  /**
   * The state of this node.
   */
  private int state = 0;
  /**
   * The states this node can be.
   */
  public static final int DEFAULT = 201,
          VISITED = 202;
  /**
   * The square that is the owner of this class.
   */
  private Square square;
  /**
   * The parent of this class (used in the algorithm).
   */
  private Square parent;
  /**
   * Used for storing costs in.
   */
  private int costToStart;
  private int costToEnd;
  private boolean blocked = false;

  // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  /**
   * Get the cost to the end.
   * @return The cost.
   */
  public int getCostToEnd() {
    return costToEnd;
  }

  /**
   * Sets the cost to the end.
   * @param costToEnd The cost.
   */
  public void setCostToEnd(int costToEnd) {
    this.costToEnd = costToEnd;
  }

  /**
   * Gets the cost to the start.
   * @return The cost.
   */
  public int getCostToStart() {
    return costToStart;
  }

  /**
   * Sets the cost to the start.
   * @param costToStart The cost.
   */
  public void setCostToStart(int costToStart) {
    this.costToStart = costToStart;
  }

  /**
   * Get the parent of this square.
   * @return The square.
   */
  public Square getParent() {
    return parent;
  }

  /**
   * Set the parent of this square.
   * @param square The square.
   */
  public void setParent(Square parent) {
    this.parent = parent;
  }

  /**
   * Get the square that this info belongs to.
   * @return The square.
   */
  public Square getSquare() {
    return square;
  }

  /**
   * Set the square that this info belongs to.
   * @param square The square.
   */
  public void setSquare(Square square) {
    this.square = square;
  }

  /**
   * Gets the state of this square.
   * @return The AStar state.
   */
  public int getState() {
    return state;
  }

  /**
   * Sets the state of this square.
   * @param score The new state.
   */
  public void setState(int state) {
    this.state = state;
  }

  /**
   * Gets the score of this square.
   * @return The AStar pathing score.
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the score of this square.
   * @param score The new score.
   */
  public void setScore(int score) {
    this.score = score;
  }
  // </editor-fold>

  public LinkedList<Square> getAdjacentSquares(Grid grid, AStarAlgorithm astar) {
    LinkedList<Square> result = new LinkedList();
    // Get the x'ed squares if they are not blocked
    //   [x]
    //[x][ ][x]
    //   [x]
    // West one first
    Square s;
    int gridX = this.getSquare().getGridX();
    int gridY = this.getSquare().getGridY();
    Square[][] squares = grid.getSquares();
    int mode = astar.getMode();
    if (gridX > 0) {
      s = squares[gridX - 1][gridY];
      if (shouldAdd(s, mode)) result.add(s);
    }
    // North one
    if (gridY > 0) {
      s = squares[gridX][gridY - 1];
      if (shouldAdd(s, mode)) result.add(s);
    }
    // East one
    if (gridX + 1 != squares.length) {
      s = squares[gridX + 1][gridY];
      if (shouldAdd(s, mode)) result.add(s);
    }
    // South one
    if (gridY + 1 != squares[0].length) {
      s = squares[gridX][gridY + 1];
      if (shouldAdd(s, mode)) result.add(s);
    }
    return result;
  }

  /**
   * Checks whether or not the square should be added to the list when called by
   * getAdjacentSquares.
   * @param s The square in question.
   * @param mode The mode of the AStarAlgorithm.
   * @return Yes or no.
   */
  protected boolean shouldAdd(Square s, int mode) {
    switch (mode) {
      case AStarAlgorithm.MODE_ALLOW_ALL:
        return true;
      case AStarAlgorithm.MODE_IGNORE_BLOCKED:
        if (s.getBuilding() == null) {
          return true;
        }
        break;
      case AStarAlgorithm.MODE_IGNORE_BUILDINGS:
        if (!s.getPathingInfo().isBlocked()) {
          return true;
        }
        break;
      case AStarAlgorithm.MODE_NORMAL:
        if (!s.getPathingInfo().isBlocked() && s.getBuilding() == null) {
          return true;
        }
        break;
    }
    return false;
  }

  /**
   * Get the score for this node.
   * @param end The node to calculate to.
   * @return The score.
   */
  public int calculateScore(Square end) {
    int differenceX = 0;
    int differenceY = 0;

    if (this.getSquare().getGridX() < end.getGridX()) {
      differenceX = end.getGridX() - this.getSquare().getGridX();
    } else {
      differenceX = this.getSquare().getGridX() - end.getGridX();
    }

    if (this.getSquare().getGridY() < end.getGridY()) {
      differenceY = end.getGridY() - this.getSquare().getGridY();
    } else {
      differenceY = this.getSquare().getGridY() - end.getGridY();
    }

    return differenceX + differenceY;
  }

  public LinkedList<Point> buildPath(Square start) {
    LinkedList<Square> path = new LinkedList();
    Square s = this.getSquare();
    int count = 0;
    path.add(s);
    while (s.getPathingInfo().getParent() != null) {
      Square currentParent = s.getPathingInfo().getParent();
      if (!currentParent.equals(start)) {
        path.add(currentParent);
        s = currentParent;
        count++;
        if (count > 100) {
          break;
        }
      } else {
        break;
      }
    }

    // Reverse the list, as its from End to Start now,
    // instead of the other way around
    LinkedList<Point> pointPath = new LinkedList();
    for (int i = path.size() - 1; i >= 0; i--) {
      s = path.get(i);
      pointPath.add(new Point(s.getGridX(), s.getGridY()));
    }
    return pointPath;
  }

  public AStarInfo(Square s) {
    this.setSquare(s);
  }
}
