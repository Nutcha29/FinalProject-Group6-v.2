/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.pathfinding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.core.Grid;
import model.core.Square;

/**
 *
 * @author Wouter
 */
public class AStarAlgorithm {

  /**
   * Ignores all blocked squares in the algorithm.
   */
  public static final int MODE_IGNORE_BLOCKED = 1;
  /**
   * Ignores only buildings in the algorithm.
   */
  public static final int MODE_IGNORE_BUILDINGS = 2;
  /**
   * Allows everything in the algorithm (all squares are available)
   */
  public static final int MODE_ALLOW_ALL = 3;
  /**
   * Ignores both buildings and blocked squares.
   */
  public static final int MODE_NORMAL = 4;
  private Square start;
private Square end;
  private Grid grid;
  private int mode = 0;

  public Square getEnd() {
    return end;
  }

  public Grid getGrid() {
    return grid;
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  public int getMode() {
    return mode;
  }

  public void setMode(int mode) {
    this.mode = mode;
  }

  public AStarAlgorithm(Grid grid, Square start, Square end, int mode) {
    this.setGrid(grid);
    this.start = start;
    this.end = end;
    this.setMode(mode);
  }

  /**
   * Find the quickest path given the start and end node are set properly.<br>
   * Returns false if the start or end is null, or are not actual start or end points.<br>
   * @return The list containing all points that are required to be visited in order to reach the end.
   */
  public LinkedList<Point> findPath() {
    if (start == null || end == null || start.getBuilding() != null || end.getBuilding() != null) {
      return null;
    }

    List<Square> closedList = new ArrayList();
    List<Square> openList = new ArrayList();
    openList.add(start);

    while (!openList.isEmpty()) {
      Square s = null;
      for (int i = 0; i < openList.size(); i++) {
        Square openSquare = openList.get(i);
        if (s == null
                || s.getPathingInfo().getScore() > openSquare.getPathingInfo().getScore()) {
          s = openSquare;
        }
      }
      if (s.equals(end)) {
        // System.out.println("Found the end at x=" + s.gridX + ", y=" + s.gridY);
        return s.getPathingInfo().buildPath(start);
      }

      // System.out.println("Current square is " + s.gridX + ", " + s.gridY);
      openList.remove(s);
      closedList.add(s);

      List<Square> successorNodes = s.getPathingInfo().getAdjacentSquares(grid, this);
      for (int i = 0; i < successorNodes.size(); i++) {
        Square currentSuccessor = successorNodes.get(i);
        if (closedList.contains((currentSuccessor))) continue;

        int currentClosestDistance = s.getPathingInfo().getCostToStart() + 1;
        boolean better = false;

        if (!openList.contains(currentSuccessor)) {
          openList.add(currentSuccessor);
          better = true;
        } else if (currentClosestDistance < currentSuccessor.getPathingInfo().getCostToStart()) {
          better = true;
        }


        if (better) {
          currentSuccessor.getPathingInfo().setParent(s);
          currentSuccessor.getPathingInfo().setCostToEnd(
                  currentSuccessor.getPathingInfo().calculateScore(end));
          currentSuccessor.getPathingInfo().setCostToStart(currentClosestDistance);
          currentSuccessor.getPathingInfo().setScore(
                  currentSuccessor.getPathingInfo().getCostToStart() + currentSuccessor.getPathingInfo().getCostToEnd());
        }
      }
    }
    return null;
  }
}
