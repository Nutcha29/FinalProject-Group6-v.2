/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.buildings;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import model.core.DoublePoint;
import model.core.DrawableObject;
import model.core.PlayfieldSquare;
import model.core.QuickSort;
import model.core.Square;
import model.core.events.SelectEvent;
import model.core.events.ZoomChangeEvent;
import model.core.texture.BuildingTextureCollection;
import variables.ZoomStatic;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public abstract class Building extends DrawableObject {

  private static final float PREVIEW_TRANSLUCENCY = 0.75f;
  private Square square;
  private int gridXWidth, gridYWidth;
  private BuildingTextureCollection textureCollection;
  private LinkedList<Square> squares = new LinkedList<Square>();
  private boolean selected = false;
  private boolean mouseOver = false;
  private boolean canSelect = true;
  private boolean preview = false;

  // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
  /**
   * Gets the draw center of this Building.
   * @return The point.
   */
  public DoublePoint getDrawCenter() {
    Image image = this.getTextureCollection().getImage(ZoomStatic.CURRENT_ZOOM_LEVEL);
    return new DoublePoint(this.getDrawPixelLocation().x + (image.getWidth(null) / 2),
            this.getDrawPixelLocation().y + (image.getHeight(null) / 2));
  }

  /**
   * Gets the center of this Building.
   * @return The point.
   */
  public DoublePoint getCenter() {
    Image image = this.getTextureCollection().getImage(ZoomStatic.CURRENT_ZOOM_LEVEL);
    return new DoublePoint(this.getPixelLocation().x + (image.getWidth(null) / 2),
            this.getPixelLocation().y + (image.getHeight(null) / 2));
  }

  /**
   * Gets whether or not this building is in preview mode.
   * @return Yes or no.
   */
  public boolean isPreview() {
    return preview;
  }

  /**
   * Sets whether or not this building is in preview mode.
   * @param preview Yes or no!
   */
  public void setPreview(boolean preview) {
    this.preview = preview;
  }

  /**
   * Gets whether or not you can place a building at this location.
   * @param square The square you want to place this building at.
   * @return Yes or no!
   */
  public boolean canPlace(Square square) {
    if (square == null) return false;
    for (int i = square.getGridX(); i > square.getGridX() - this.getGridXWidth(); i--) {
      for (int j = square.getGridY(); j > square.getGridY() - this.getGridYWidth(); j--) {
        try {
          Square s = square.getGrid().getSquares()[i][j];
          if ((s.getBuilding() != null && !s.getBuilding().isPreview())) return false;
        } catch (ArrayIndexOutOfBoundsException e) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Gets whether or not you can select this building.
   * @return The flag.
   */
  public boolean canSelect() {
    return canSelect;
  }

  /**
   * Sets whether or not you can select this building.
   * @param canSelect The new flag.
   */
  public void setCanSelect(boolean canSelect) {
    this.canSelect = canSelect;
  }

  /**
   * Whether or not the mouse is over this building.
   * @return The flag.
   */
  public boolean isMouseOver() {
    return mouseOver;
  }

  /**
   * Sets whether or not this mouse is over this building.
   * @param mouseOver The new flag.
   */
  public void setMouseOver(boolean mouseOver) {
    this.mouseOver = mouseOver;
  }

  /**
   * Gets whether or not this building is selected.
   * @return The flag.
   */
  public boolean isSelected() {
    return selected;
  }

  /**
   * Sets whether or not this building is selected. Note that selecting a building,
   * will deselect all others.
   * @param selected The flag.
   */
  public void setSelected(boolean selected) {
    if (!this.canSelect()) return;
    if (selected) {
      LinkedList<Building> list = EngineWindow.getInstance().getGame().getBuildingList();
      for (Building building : list) {
        building.setSelected(false);
      }
    }
    // If the selected flag actually changed
    if (this.selected != selected) this.onSelect(new SelectEvent(this, selected));
    this.selected = selected;
  }

  /**
   * Gets the TextureCollection of this building.
   * @return The collection.
   */
  public BuildingTextureCollection getTextureCollection() {
    return textureCollection;
  }

  /**
   * Sets the TextureCollection of this building.
   * @param textureCollection The new TextureCollection.
   */
  public void setTextureCollection(BuildingTextureCollection textureCollection) {
    this.textureCollection = textureCollection;
  }

  public Square getSquare() {
    return square;
  }

  /**
   * Gets the bounding box of this Building.
   * @return The bounding box.
   */
  public Rectangle getDrawBoundingBox() {
    Image image = this.getTextureCollection().
            getImage(ZoomStatic.CURRENT_ZOOM_LEVEL);
    Point p = this.getDrawPixelLocation();
    return new Rectangle(p.x, p.y, image.getWidth(null), image.getHeight(null));
  }

  /**
   * Gets the squares that this drawable object is occupying.
   * @return The squares.
   */
  public LinkedList<Square> getSquares() {
    return this.squares;
  }

  /**
   * Sets the parent square of this drawable object. Note that this will 'occupy'
   * all squares within it's reach.
   * @param square The Square.
   */
  public void setSquare(Square square) {
    if (square != null && !canPlace(square)) {
      return;
    }
    this.getSquares().clear();
    // Remove previous building hooks
    if (this.square != null) {
      for (int i = this.square.getGridX(); i > this.square.getGridX() - this.getGridXWidth(); i--) {
        for (int j = this.square.getGridY(); j > this.square.getGridY() - this.getGridYWidth(); j--) {
          Square s = this.square.getGrid().getSquares()[i][j];
          // s.setHighlighted(false);
          s.setBuilding(null);
        }
      }
    }

    if (square == null) {
      this.square = square;
      return;
    }

    // Add new shit!
    for (int i = square.getGridX(); i > square.getGridX() - getGridXWidth(); i--) {
      for (int j = square.getGridY(); j > square.getGridY() - getGridYWidth(); j--) {
        Square s = square.getGrid().getSquares()[i][j];
        // s.setHighlighted(true);
        s.setBuilding(this);
        this.getSquares().add(s);
      }
    }


    this.square = square;
    this.setPixelLocation(new DoublePoint(this.getSquare().getCenter().x, this.getSquare().getCenter().y));
  }

  /**
   * Gets the grid x width of the building that it occupies.
   * @return The amount of squares to the right of the building.
   */
  public int getGridXWidth() {
    return gridXWidth;
  }

  public void setGridXWidth(int gridXWidth) {
    this.gridXWidth = gridXWidth;
  }

  /**
   * Gets the grid y width of the building that it occupies.
   * @return The amount of squares to the bottom of the building.
   */
  public int getGridYWidth() {
    return gridYWidth;
  }

  public void setGridYWidth(int gridYWidth) {
    this.gridYWidth = gridYWidth;
  }

  public Point getDrawPixelLocation() {
    Image image = this.getTextureCollection().getImage(ZoomStatic.CURRENT_ZOOM_LEVEL);

    return new Point(this.getSquare().getDrawPixelX()
            - Math.abs(image.getWidth(null) / 2), // PlayfieldSquare.WIDTH / 2,
            this.getSquare().getDrawPixelY() - Math.abs(PlayfieldSquare.HEIGHT - image.getHeight(null)));
  }
  // </editor-fold>

  public void draw(Graphics g) {
    if (!this.doDraw()) {
      return;
    }
    Point drawLocation = this.getDrawPixelLocation();
    Image image = this.getTextureCollection().
            getImage(ZoomStatic.CURRENT_ZOOM_LEVEL);

    if (this.isPreview()) {
      Graphics2D g2 = (Graphics2D) g;
      Composite old = g2.getComposite();
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Building.PREVIEW_TRANSLUCENCY));
      g.drawImage(image, drawLocation.x, drawLocation.y, null);
      g2.setComposite(old);
    } else {
      g.drawImage(image, drawLocation.x, drawLocation.y, null);

      if ((this.isSelected() || this.isMouseOver()) && this.canSelect()) {
        g.setColor(Color.BLUE);
        int x = drawLocation.x, y = drawLocation.y;
        int w = image.getWidth(null), h = image.getHeight(null);
        // Top left corner
        g.drawLine(x, y, x + (int) (w * 0.2), y);
        g.drawLine(x, y, x, y + (int) (h * 0.2));

        // Top right corner
        g.drawLine(x + (int) (w * 0.8), y, x + w, y);
        g.drawLine(x + w, y, x + w, (int) (y + w * 0.2));

        // Bottom left corner
        g.drawLine(x, y + h, x, y + (int) (h * 0.8));
        g.drawLine(x, y + h, x + (int) (w * 0.2), y + h);

        // Bottom right corner
        g.drawLine(x + w, y + h, x + (int) (w * 0.8), y + h);
        g.drawLine(x + w, y + h, x + w, y + (int) (h * 0.8));
      }
    }
  }

  /**
   * Should be called when the zoom changes.
   */
  public void onZoomChange(ZoomChangeEvent event) {
    if (this.getSquare() == null) return;
    this.setPixelLocation(new DoublePoint(this.getSquare().getCenter().x, this.getSquare().getCenter().y));
  }

  /**
   * Calculates whether or not to draw this object
   * @param image The image that is used to draw this building.
   * @return The flag.
   */
  public boolean doDraw() {
    if (this.getSquare() == null) return false;
    for (Square s : this.getSquares()) {
      if (s.isDrawn()) return true;
    }
    Rectangle visibleRect = EngineWindow.getInstance().getCanvas().getViewPort();
    Rectangle boundingRect = this.getDrawBoundingBox();
    if (visibleRect.intersects(boundingRect)) return true;

    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Building)) {
      return false;
    } else {
      Building b = (Building) obj;
      if (this.isPreview() && b.isPreview()) {
        if (this.getClass().getName().equals(b.getClass().getName())) {
          return true;
        }
      }
      if ((this.isPreview() && !b.isPreview()) || (!this.isPreview() && b.isPreview())) {
        return false;
      }

      if (this.isPreview() && b.isPreview() && this.getClass().getName().equals(b.getClass().getName())) {
        return true;
      }

      try {
        if (!this.getClass().getName().equals(b.getClass().getName())
                || !this.getPixelLocation().equals(b.getPixelLocation())) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  }

  /**
   * Called when the unit is selected, or deselected.
   */
  public abstract void onSelect(SelectEvent event);

  public Building(Square s, BuildingTextureCollection collection, int gridXWidth, int gridYWidth) {
    this.setGridXWidth(gridXWidth);
    this.setGridYWidth(gridYWidth);

    this.setTextureCollection(collection);

    this.setSquare(s);
    if (this.getSquare() != null) {
      this.setPixelLocation(new DoublePoint(this.getSquare().getCenter().x, this.getSquare().getCenter().y));
    }

    EngineWindow.getInstance().getGame().getBuildingList().add(this);    
    ArrayList<DrawableObject> doList = new ArrayList<DrawableObject>();
    for (Building b : EngineWindow.getInstance().getGame().getBuildingList()) {
      doList.add(b);
    }

    ArrayList<DrawableObject> newList = new QuickSort(doList).sort();
    LinkedList<Building> newBuildingList = new LinkedList<Building>();
    for (DrawableObject object : newList) {
      newBuildingList.add((Building) object);
    }

    EngineWindow.getInstance().getGame().getBuildingList().clear();
    EngineWindow.getInstance().getGame().getBuildingList().addAll(newBuildingList);
  }
}
