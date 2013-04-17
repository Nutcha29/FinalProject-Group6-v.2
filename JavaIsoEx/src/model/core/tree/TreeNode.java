/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.tree;

import model.core.Drawable;
import model.core.Square;
import model.core.Triangle;
import view.EngineWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Wouter
 */
public class TreeNode extends Drawable {

  private Triangle triangle;
  private TreeNode parent;
  private TreeNode[] children;
  private ArrayList<SquarePoint> squares = new ArrayList<SquarePoint>();
  public boolean drawConnections = false;

  public boolean getDrawConnections() {
    return drawConnections;
  }

  public void setDrawConnections(boolean drawConnections) {
    this.drawConnections = drawConnections;
  }

  /**
   * Gets all the squares of all the children's nodes, or of this node if this
   * node has no children.
   * @return The list containing the squares.
   */
  public ArrayList<SquarePoint> getUnderlyingSquares() {
    ArrayList<SquarePoint> squaresList = new ArrayList<SquarePoint>();
    if (this.getChildren() != null) {
      for (int i = 0; i < this.getChildren().length; i++) {
        TreeNode[] node = this.getChildren();
        squaresList.addAll(node[i].getUnderlyingSquares());
      }
    } else {
      try {
        squaresList.addAll(this.getSquarePointList());
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Caught an index out of bounds exception!");
      }
    }
    return squaresList;
  }

  /**
   * Gets the depth of this TreeNode in the tree
   * @return The depth in nodes, ranging from 0 .. ~
   */
  public int getDepth() {
    int depth = 0;
    TreeNode node = this;
    while (node.parent != null) {
      node = node.parent;
      depth++;
    }
    return depth;
  }

  private ArrayList<SquarePoint> getSquarePointList() {
    return squares;
  }

  public void addSquare(Square square, Point p) throws IllegalArgumentException {
    if (this.getChildren() != null) {
      throw new IllegalArgumentException("You cannot add squares to a TreeNode that has children.");
    } else {
      this.getSquarePointList().add(new SquarePoint(square, p));
    }
  }

  public TreeNode[] getChildren() {
    return children;
  }

  public void setChildren(TreeNode[] children) {
    this.children = children;
  }

  public TreeNode getParent() {
    return parent;
  }

  public void setParent(TreeNode parent) {
    this.parent = parent;
  }

  public Triangle getTriangle() {
    return triangle;
  }

  public void setTriangle(Triangle triangle) {
    this.triangle = triangle;
  }

  public TreeNode[] split() {
    int differenceX = this.getTriangle().getPoints()[1].x - this.getTriangle().getPoints()[2].x, newX = 0;
    int differenceY = this.getTriangle().getPoints()[1].y - this.getTriangle().getPoints()[2].y, newY = 0;

    if (differenceX > 0) {
      newX = this.getTriangle().getPoints()[2].x + (differenceX / 2);
    } else {
      newX = this.getTriangle().getPoints()[1].x + Math.abs(differenceX / 2);
    }

    if (differenceY > 0) {
      newY = this.getTriangle().getPoints()[2].y + (differenceY / 2);
    } else {
      newY = this.getTriangle().getPoints()[1].y + Math.abs(differenceY / 2);
    }


    Point[] points = new Point[3];
    points[0] = new Point(newX, newY);
    points[1] = new Point(this.getTriangle().getPoints()[2]);
    points[2] = new Point(this.getTriangle().getPoints()[0]);

    TreeNode[] newNodes = new TreeNode[2];
    newNodes[0] = new TreeNode(this, new Triangle(points));

    Point[] points2 = new Point[3];
    points2[0] = new Point(newX, newY);
    points2[1] = new Point(this.getTriangle().getPoints()[1]);
    points2[2] = new Point(this.getTriangle().getPoints()[0]);
    newNodes[1] = new TreeNode(this, new Triangle(points2));

    this.setChildren(newNodes);

    return newNodes;
  }

  @Override
  public void draw(Graphics g) {
    this.getTriangle().draw(g);

    if (this.getDrawConnections()) {
      for (int i = 0; i < this.getSquarePointList().size(); i++) {
        Point squarePoint = this.getSquarePointList().get(i).getPoint();
        Point offset = EngineWindow.getInstance().getGame().getPlayfieldGrid().getDrawOffset();
        squarePoint = new Point(squarePoint.x + offset.x,
                squarePoint.y + offset.y);
        Point p = this.getTriangle().getDrawBariCenter();
        g.setColor(Color.BLUE);
        g.drawLine(squarePoint.x, squarePoint.y, p.x, p.y);
        g.setColor(Color.RED);
      }
    }
  }

  /**
   * Draws this TreeNode including its children.
   * @param g The Graphics object to draw on.
   */
  public void drawWithChildren(Graphics g) {
    if (this.getChildren() == null) {
      this.draw(g);
      return;
    }
    for (int i = 0; i < this.getChildren().length; i++) {
      TreeNode[] nodes = this.getChildren();
      nodes[i].drawWithChildren(g);
    }
  }

  public TreeNode(TreeNode parent, Triangle triangle) {
    this.setParent(parent);
    this.setTriangle(triangle);
  }

  @Override
  public String toString() {
    return "Node: {\n" + this.getTriangle().toString() + "\n}";
  }

  public class SquarePoint {

    private Square square;
    private Point point;

    public Point getPoint() {
      return point;
    }

    public void setPoint(Point point) {
      this.point = point;
    }

    public Square getSquare() {
      return square;
    }

    public void setSquare(Square square) {
      this.square = square;
    }

    public SquarePoint(Square s, Point p) {
      this.setSquare(s);
      this.setPoint(p);
    }
  }
}
