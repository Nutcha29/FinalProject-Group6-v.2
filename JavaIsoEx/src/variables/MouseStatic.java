/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package variables;

import java.awt.Point;

/**
 *
 * @author Wouter
 */
public class MouseStatic {

  public static int LAST_CLICKED_BUTTON = -1;
  /**
   * Last clicked point by the right mouse.
   */
  public static Point LAST_RIGHT_MOUSE_CLICKED_POINT = new Point(0, 0);
  /**
   * Last clicked point by the left mouse.
   */
  public static Point LAST_LEFT_MOUSE_CLICKED_POINT = new Point(0, 0);
  /**
   * Last clicked point by the right mouse, adjusted by the offset.
   */
  public static Point LAST_RIGHT_MOUSE_CLICKED_OFFSET_POINT = new Point(0, 0);
  /**
   * Last clicked point by the left mouse, adjusted by the offset.
   */
  public static Point LAST_LEFT_MOUSE_CLICKED_OFFSET_POINT = new Point(0, 0);
  /**
   * Whether the user has performed a drag prior to a MouseRelease event
   */
  public static boolean PERFORMED_DRAG = false;
  /**
   * Will be set to true if the user has selected a unit by the mouse (to prevent
   * deselecting *all* units when user has clicked on a unit)
   */
  public static boolean SELECTED_UNIT_BY_CLICK = false;
  /**
   * Will be set to true if the user has selected a building by the mouse
   */
  public static boolean SELECTED_BUILDING_BY_CLICK = false;
}
