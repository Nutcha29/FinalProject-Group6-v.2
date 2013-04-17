/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core;

import view.EngineWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Wouter
 */
public class Triangle extends Drawable {

  private Point[] points = new Point[3];
  private boolean useGridOffset = true;

  public boolean getUseGridOffset() {
    return useGridOffset;
  }

  public void setUseGridOffset(boolean useGridOffset) {
    this.useGridOffset = useGridOffset;
  }

  public Point[] getPoints() {
    return points;
  }

  /**
   * Gets the points that can be used for drawing on the grid.
   * USE GETPOINTS FOR OTHER CALCULATIONS OTHER THAN DRAWING!
   * @return The points.
   */
  public Point[] getDrawPoints() {
    if (this.getUseGridOffset()) {
      Point[] newPoints = new Point[3];
      Grid grid = EngineWindow.getInstance().getGame().getPlayfieldGrid();
      for (int i = 0; i < this.getPoints().length; i++) {
        newPoints[i] = new Point(getPoints()[i].x + grid.getDrawOffset().x, getPoints()[i].y + grid.getDrawOffset().y);
      }
      return newPoints;
    } else {
      return getPoints();
    }
  }

  public void setPoints(Point[] points) {
    this.points = points;
  }

  public double areaOf() {
    return Math.abs(points[0].x * points[1].y
            + points[1].x * points[2].y
            + points[2].x * points[0].y
            - points[0].x * points[2].y
            - points[2].x * points[1].y
            - points[1].x * points[0].y) / 2;
  }

  /**
   * Whether this triangle contains a point.
   * @param point The point to check.
   * @return The flag.
   */
  public boolean contains(Point point) {
    double totalArea = 0;
    Point[] newPoints = {this.getPoints()[0], this.getPoints()[1], point};
    Triangle triangle = new Triangle(newPoints);
    totalArea += triangle.areaOf();

    Point[] newPoints2 = {this.getPoints()[1], this.getPoints()[2], point};
    triangle = new Triangle(newPoints2);
    totalArea += triangle.areaOf();

    Point[] newPoints3 = {this.getPoints()[2], this.getPoints()[0], point};
    triangle = new Triangle(newPoints3);
    totalArea += triangle.areaOf();

    // System.out.println("Total area: " + totalArea + ", areaOf: " + this.areaOf() + " (" + (totalArea - this.areaOf()) + ")");
    // Rounding errors
    if (Math.abs(totalArea - this.areaOf()) < 50) {
      return true;
    }

    return false;
  }

  /**
   * Gets a new triangle, offsetted by the grid's draw points.
   * @return The new triangle. Note that calling this function more times in a row
   * will give you wrong results.
   */
  public Triangle getDrawTriangle(){
    return new Triangle(this.getDrawPoints());
  }

  /**
   * Gets the Bari Center of this Triangle.
   * @return The point.
   */
  public Point getBariCenter() {
    return new Point((this.getPoints()[0].x + this.getPoints()[1].x + this.getPoints()[2].x) / 3,
            (this.getPoints()[0].y + this.getPoints()[1].y + this.getPoints()[2].y) / 3);
  }

  /**
   * Gets the Bari Center of this Triangle, with the draw offsets of the grid.
   * @return The point.
   */
  public Point getDrawBariCenter() {
    return new Point((this.getDrawPoints()[0].x + this.getDrawPoints()[1].x + this.getDrawPoints()[2].x) / 3,
            (this.getDrawPoints()[0].y + this.getDrawPoints()[1].y + this.getDrawPoints()[2].y) / 3);
  }

  protected Triangle() {
  }

  public Triangle(Point[] points) {
    this.setPoints(points);
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.RED);
    g.drawLine(this.getDrawPoints()[0].x, this.getDrawPoints()[0].y, this.getDrawPoints()[1].x, this.getDrawPoints()[1].y);
    g.drawLine(this.getDrawPoints()[1].x, this.getDrawPoints()[1].y, this.getDrawPoints()[2].x, this.getDrawPoints()[2].y);
    g.drawLine(this.getDrawPoints()[2].x, this.getDrawPoints()[2].y, this.getDrawPoints()[0].x, this.getDrawPoints()[0].y);
    g.setColor(Color.BLACK);
  }

  @Override
  public String toString() {
    return "1(" + this.getPoints()[0].x + ", " + this.getPoints()[0].y + "), "
            + "2(" + this.getPoints()[1].x + ", " + this.getPoints()[1].y + "), "
            + "3(" + this.getPoints()[2].x + ", " + this.getPoints()[2].y + ")";
  }
}
