/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.moveable;

import java.awt.Point;
import model.core.PlayfieldSquare;
import model.core.events.MoveEvent;
import model.core.moveable.Moveable;
import variables.ZoomStatic;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public abstract class RoadVehicle extends Moveable {

  private int xOffset = 0, yOffset = 0;

  private int getxOffset() {
    return xOffset;
  }

  private void setxOffset(int xOffset) {
    this.xOffset = xOffset;
  }

  private int getyOffset() {
    return yOffset;
  }

  private void setyOffset(int yOffset) {
    this.yOffset = yOffset;
  }

  @Override
  public Point getDrawPixelLocation() {
    Point offset = EngineWindow.getInstance().getGame().getPlayfieldGrid().getDrawOffset();
    return new Point((int) ((this.getPixelLocation().x) + offset.x) - (PlayfieldSquare.WIDTH / 2) + this.getxOffset(),
            (int) ((this.getPixelLocation().y) + offset.y) - (PlayfieldSquare.HEIGHT / 2) + this.getyOffset());
  }

  /**
   * Makes sure this Moveable drives on the right side of the road :]
   */
  public void driveOnRightSideOfTheRoadBromateLolz() {
    double modifier = 0;
    switch (ZoomStatic.CURRENT_ZOOM_LEVEL) {
      case ZoomStatic.ZOOM_LARGE:
        modifier = 1;
        break;
      case ZoomStatic.ZOOM_MEDIUM:
        modifier = ZoomStatic.ZOOM_MEDIUM_HEIGHT / (double) ZoomStatic.ZOOM_LARGE_HEIGHT;
        break;
      case ZoomStatic.ZOOM_SMALL:
        modifier = ZoomStatic.ZOOM_SMALL_HEIGHT / (double) ZoomStatic.ZOOM_LARGE_HEIGHT;
        break;
    }
    switch (this.getMoveState()) {
      case RIGHT_TOP: {
        this.setxOffset(20 * (int) modifier);
        this.setyOffset(0 * (int) modifier);
        break;
      }
      case RIGHT_BOTTOM: {
        this.setxOffset(2 * (int) modifier);
        this.setyOffset(-2 * (int) modifier);
        break;
      }
      case LEFT_TOP: {
        this.setxOffset(16 * (int) modifier);
        this.setyOffset(-7 * (int) modifier);
        break;
      }
      case LEFT_BOTTOM: {
        this.setxOffset(2 * (int) modifier);
        this.setyOffset(-7 * (int) modifier);
        break;
      }
    }
  }
}
