/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core;

import java.awt.Point;

/**
 *
 * @author Wouter
 */
public class DoublePoint {

  public double x, y;

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public Point toPoint() {
    return new Point((int) x, (int) y);
  }

  public static DoublePoint fromPoint(Point p) {
    return new DoublePoint(p.x, p.y);
  }

  public DoublePoint(double x, double y) {
    this.setX(x);
    this.setY(y);
  }

  @Override
  public String toString() {
    return "DoublePoint (" + this.getX() + ", " + this.getY() + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof DoublePoint)) {
      return false;
    } else {
      DoublePoint dp = (DoublePoint) obj;
      if (dp.getX() != this.getX() || dp.getY() != this.getY()) {
        return false;
      }
    }
    return true;
  }
}
