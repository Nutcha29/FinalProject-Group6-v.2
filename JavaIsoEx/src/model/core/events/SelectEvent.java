/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.events;

import model.core.DrawableObject;

/**
 *
 * @author Wouter
 */
public class SelectEvent {

  boolean selected = false;
  DrawableObject object = null;

  /**
   * Whether the object is now selected or not.
   * @return The flag.
   */
  public boolean isSelected() {
    return selected;
  }

  private void setSelected(boolean selected) {
    this.selected = selected;
  }

  /**
   * Gets the object on which this event occurred.
   * @return The Object.
   */
  public DrawableObject getObject() {
    return object;
  }

  private void setObject(DrawableObject object) {
    this.object = object;
  }

  public SelectEvent(DrawableObject object, boolean selected) {
    this.setObject(object);
    this.setSelected(selected);
  }

  @Override
  public String toString() {
    return "SelectEvent; Object: " + this.getObject() + ", flag: " + this.isSelected();
  }
}
