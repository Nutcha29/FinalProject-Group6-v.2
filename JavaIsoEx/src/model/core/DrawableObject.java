/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import model.core.events.ZoomChangeEvent;

/**
 *
 * @author Wouter
 */
public abstract class DrawableObject extends Drawable {

  private DoublePoint pixelLocation;
  private int z_index = 0;

  /**
   * Gets the z-index of this object.
   * @return
   */
  public int getZIndex() {
    return z_index;
  }

  /**
   * Sets the Z of this object. This is only used for drawing. Higher value means it gets
   * drawn as if the object was actually higher on the screen (lower y)
   * @param z_index The new z-index.
   */
  public void setZIndex(int z_index) {
    this.z_index = z_index;
  }

  /**
   * Effectively returns (int) (this.getPixelLocation().y + this.getZIndex());
   * @return The draw index, used for computing what to draw when.
   */
  public int getDrawIndex() {
    return (int) (this.getPixelLocation().y + this.getZIndex());
  }

  public DoublePoint getPixelLocation() {
    return pixelLocation;
  }

  public void setPixelLocation(DoublePoint pixelLocation) {
    this.pixelLocation = pixelLocation;
  }

  /**
   * Sorts the list of drawable objects by pixel Y location. (This function is used
   * for drawing only.)
   * @param list The list to sort.
   * @return The sorted list (Descending)
   */
  public static ArrayList<DrawableObject> sortByDrawIndex(ArrayList<DrawableObject> list) {
    QuickSort sort = new QuickSort(list);
    return sort.sort();
  }

  public abstract Point getDrawPixelLocation();

  public abstract void onZoomChange(ZoomChangeEvent event);
}
