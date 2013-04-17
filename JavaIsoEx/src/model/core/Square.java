/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core;

import java.awt.Color;
import java.awt.Graphics;
import model.core.buildings.Building;
import model.core.texture.TexturePlane;
import java.awt.Point;
import java.awt.Rectangle;
import model.core.external.Saveable;
import model.core.pathfinding.AStarInfo;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public abstract class Square extends Drawable implements Saveable {

  private int gridX, gridY, pixelX, pixelY;
  /**
   * Amount of frames left for this square to be selected.
   */
  private int framesLeft = -1;
  private Grid grid;
  private boolean selected = false, drawTriangles = false, drawn = false, highlighted;
  private SquareTriangle[] triangles;
  private Point[] cornerPoints, drawCornerPoints = new Point[4];
  private TexturePlane texture;
  private Building building;
  /**
   * Whether this square is blocked by something.
   */
  private AStarInfo pathingInfo;

  // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
  public boolean isHighlighted() {
    return highlighted;
  }

  public void setHighlighted(boolean highlighted) {
    this.highlighted = highlighted;
  }

  public boolean isDrawn() {
    return drawn;
  }

  protected void setDrawn(boolean drawn) {
    this.drawn = drawn;
  }

  public AStarInfo getPathingInfo() {
    return pathingInfo;
  }

  protected void setPathingInfo(AStarInfo pathingInfo) {
    this.pathingInfo = pathingInfo;
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    if (building == null) {
      this.getPathingInfo().setBlocked(false);
      this.setHighlighted(false);
    } else this.getPathingInfo().setBlocked(true);
    this.building = building;
  }

  public TexturePlane getTexture() {
    return texture;
  }

  public void setTexture(TexturePlane texture) {
    this.texture = texture;
  }

  /** Gets the corners of this Square, offsetted by the grid offset.
   * 0: Top
   * 1: Left
   * 2: Right
   * 3: Bottom
   * @return The Point array.
   */
  public Point[] getDrawCornerPoints() {
    return drawCornerPoints;
  }

  /**
   * Gets the corner points of this square
   * 0: Top
   * 1: Left
   * 2: Right
   * 3: Bottom
   * @return The points.
   */
  public Point[] getCornerPoints() {
    return cornerPoints;
  }

  /**
   * Sets the corner points of this square
   * 0: Top
   * 1: Left
   * 2: Right
   * 3: Bottom
   * @param cornerPoints The new draw points.
   */
  protected void setCornerPoints(Point[] cornerPoints) {
    this.cornerPoints = cornerPoints;
  }

  public boolean getDrawTriangles() {
    return drawTriangles;
  }

  public void setDrawTriangles(boolean drawTriangles) {
    this.drawTriangles = drawTriangles;
  }

  public Triangle[] getTriangles() {
    return triangles;
  }

  protected void setTriangles(SquareTriangle[] triangles) {
    this.triangles = triangles;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  /**
   * Amount of frames left for this square to be highlighted.
   * @return The frames, can be negative.
   */
  public int getFramesLeft() {
    return framesLeft;
  }

  /**
   * Sets the frames left for this square to be highlighted.
   * @param framesLeft The amount of frames left.
   */
  public void substractedFramesLeft() {
    this.framesLeft--;
  }

  /**
   * Sets this Square to be selected for a certain amount of frames.
   * @param frames The amount of frames for this Square to be selected.
   */
  public void setHighlighted(int framesLeft) {
    this.framesLeft = framesLeft;
  }

  public Grid getGrid() {
    return grid;
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  public int getGridX() {
    return gridX;
  }

  public void setGridX(int gridX) {
    this.gridX = gridX;
  }

  public int getGridY() {
    return gridY;
  }

  public void setGridY(int gridY) {
    this.gridY = gridY;
  }

  public int getPixelX() {
    return pixelX;
  }

  public int getDrawPixelX() {
    return pixelX + this.getGrid().getDrawOffset().x;
  }

  protected void setPixelX(int pixelX) {
    this.pixelX = pixelX;
  }

  public int getPixelY() {
    return pixelY;
  }

  public int getDrawPixelY() {
    return pixelY + this.getGrid().getDrawOffset().y;
  }

  /**
   * Gets the location of this square, without the offsets of the Grid.
   * @return The point.
   */
  public Point getPixelLocation() {
    return new Point(pixelX, pixelY);
  }

  /**
   * Gets the location of this square, *with* the offsets of the Grid.
   * @return The point.
   */
  public Point getDrawPixelLocation() {
    return new Point(pixelX + this.getGrid().getDrawOffset().x, pixelY + this.getGrid().getDrawOffset().y);
  }

  protected void setPixelY(int pixelY) {
    this.pixelY = pixelY;
  }

  /**
   * Gets the center of this Square.
   * @return The center.
   */
  public Point getCenter() {
    return new Point(this.getPixelLocation().x, this.getPixelLocation().y + (int) (PlayfieldSquare.HEIGHT / 2.0));
  }

  /**
   * Gets the draw center of this Square.
   * @return The draw center.
   */
  public Point getDrawCenter() {
    return new Point(this.getPixelLocation().x + this.getGrid().getDrawOffset().x,
            this.getPixelLocation().y + (int) (PlayfieldSquare.HEIGHT / 2.0) + this.getGrid().getDrawOffset().y);
  }
  // </editor-fold>

  /**
   * Gets whether or not to draw this square
   * @return The flag.
   */
  protected boolean doDraw() {
    int pointsOffScreen = 0;
    Rectangle rect = EngineWindow.getInstance().getCanvas().getViewPort();
    for (int i = 0; i < this.getDrawCornerPoints().length; i++) {
      if (rect.contains(this.getDrawCornerPoints()[i])) {
        break;
      } else {
        pointsOffScreen++;
      }
    }
    // No points on the screen
    if (pointsOffScreen == this.getDrawCornerPoints().length) {
      return false;
    }
    return true;
  }

  /**
   * Re-sets the draw points of this square, only useful to call when the user
   * has changed the offset of the grid.
   */
  public void calculateDrawCornerPoints() {
    // Point[] newDrawPoints = new Point[4];
    for (int i = 0; i < this.getCornerPoints().length; i++) {
      drawCornerPoints[i] = new Point(this.getCornerPoints()[i].x + this.getGrid().getDrawOffset().x,
              this.getCornerPoints()[i].y + this.getGrid().getDrawOffset().y);
    }
  }

  /**
   * Re-calculates the points of this square with respect to its new width / height
   * (x, y, draw x, draw y, etc.) Used when the zoom of the grid changes.
   */
  public void calculatePoints() {
    Point p = ((PlayfieldGrid) this.getGrid()).translateGridToPixel(this.getGridX(), this.getGridY());
    this.setPixelX(p.x);
    this.setPixelY(p.y);
    // this.setDrawSquares(true);

    int halfWidth = PlayfieldSquare.WIDTH / 2, halfHeight = PlayfieldSquare.HEIGHT / 2;
    Point[] points = new Point[3];
    points[0] = new Point(this.getPixelX(), this.getPixelY());
    points[1] = new Point(this.getPixelX() - (halfWidth), this.getPixelY() + (halfHeight));
    points[2] = new Point(this.getPixelX() + (halfWidth), this.getPixelY() + (halfHeight));
    SquareTriangle[] triangles = new SquareTriangle[2]; //local var hides a field
    triangles[0] = new SquareTriangle(points, this);


    points = new Point[3];
    points[0] = new Point(this.getPixelX(), this.getPixelY() + PlayfieldSquare.HEIGHT);
    points[1] = new Point(this.getPixelX() - (halfWidth), this.getPixelY() + (halfHeight));
    points[2] = new Point(this.getPixelX() + (halfWidth), this.getPixelY() + (halfHeight));
    triangles[1] = new SquareTriangle(points, this);

    this.setTriangles(triangles);


    ///           /
    points = new Point[4];
    points[0] = new Point(this.getPixelX(), this.getPixelY());
    points[1] = new Point(this.getPixelX() - (halfWidth), this.getPixelY() + (halfHeight));
    points[2] = new Point(this.getPixelX() + (halfWidth), this.getPixelY() + (halfHeight));
    points[3] = new Point(this.getPixelX(), (int) (this.getPixelY() + PlayfieldSquare.HEIGHT));

    this.setCornerPoints(points);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof Square) {
      Square s = (Square) obj;
      if (s.getGridX() == this.getGridX()
              && s.getGridY() == this.getGridY()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Draws the border of this square, with color c.
   * DON'T USE THIS TO DRAW THE GRID (color black).
   * @param g The Graphics to draw on.
   * @param c The color.
   */
  public void drawBorder(Graphics g, Color c) {
    g.setColor(c);

    g.drawLine(this.getDrawCornerPoints()[0].x, this.getDrawCornerPoints()[0].y,
            this.getDrawCornerPoints()[1].x, this.getDrawCornerPoints()[1].y);
    g.drawLine(this.getDrawCornerPoints()[1].x, this.getDrawCornerPoints()[1].y,
            this.getDrawCornerPoints()[3].x, this.getDrawCornerPoints()[3].y);
    g.drawLine(this.getDrawCornerPoints()[3].x, this.getDrawCornerPoints()[3].y,
            this.getDrawCornerPoints()[2].x, this.getDrawCornerPoints()[2].y);
    g.drawLine(this.getDrawCornerPoints()[2].x, this.getDrawCornerPoints()[2].y,
            this.getDrawCornerPoints()[0].x, this.getDrawCornerPoints()[0].y);
  }

  @Override
  public String toString() {
    return "Square @ (" + this.getGridX() + ", " + this.getGridY() + ")";
  }
}
