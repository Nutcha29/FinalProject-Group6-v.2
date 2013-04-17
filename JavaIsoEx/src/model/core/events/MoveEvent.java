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
public class MoveEvent {

  public static final int TYPE_MOVE_STARTED = 1, TYPE_MOVE = 2, TYPE_MOVE_ENDED = 3;
  private DrawableObject object;
  private int type;

  /**
   * Gets the object on which this event occurred.
   * @return
   */
  public DrawableObject getObject() {
    return object;
  }

  /**
   * Sets the object on which this event occurred.
   * @param object
   */
  private void setObject(DrawableObject object) {
    this.object = object;
  }

  /**
   * Gets the type of this MoveEvent.
   * @return The enum containing the type.
   * @see #TYPE_MOVE_STARTED
   * @see #TYPE_MOVE
   * @see #TYPE_MOVE_ENDED
   */
  public int getType() {
    return type;
  }

  /**
   * Sets the type of this MoveEvent.
   * @param The new enum containing the type.
   * @see #TYPE_MOVE_STARTED
   * @see #TYPE_MOVE
   * @see #TYPE_MOVE_ENDED
   */
  private void setType(int type) {
    this.type = type;
  }

  public MoveEvent(DrawableObject object, int type) {
    this.setObject(object);
    this.setType(type);
  }

  /**
   * Converts the type to a String.
   * @return bla.
   */
  private String typeToString() {
    switch (this.getType()) {
      case TYPE_MOVE_STARTED:
        return "MOVE_STARTED";
      case TYPE_MOVE:
        return "MOVE";
      case TYPE_MOVE_ENDED:
        return "MOVE_ENDED";
      default:
        return "INVALID_MOVE_TYPE";
    }
  }

  @Override
  public String toString() {
    return "MoveEvent: " + typeToString();
  }
}
