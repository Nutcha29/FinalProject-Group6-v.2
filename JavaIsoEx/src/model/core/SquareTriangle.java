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
public class SquareTriangle extends Triangle {

  private Square square;

  public Square getSquare() {
    return square;
  }

  public void setSquare(Square square) {
    this.square = square;
  }

  public SquareTriangle(Point[] point, Square square) {
    this.setPoints(point);
    this.setSquare(square);
  }
}
