/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.game.Game;
import java.awt.Cursor;
import model.core.PlayfieldGrid;
import model.core.Square;
import variables.MouseStatic;
import view.EngineWindow;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import model.core.buildings.Building;
import model.core.moveable.Moveable;
import view.SelectBox;

/**
 *
 * @author Wouter
 */
public class MouseMotionAdapter implements MouseMotionListener {

  private Square previousSelectedSquare;
  private static final Cursor DEFAULT_CURSOR = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR),
          HAND_CURSOR = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

  public Square getPreviousSelectedSquare() {
    return previousSelectedSquare;
  }

  public void setPreviousSelectedSquare(Square previousSelectedSquare) {
    this.previousSelectedSquare = previousSelectedSquare;
  }

  public void mouseDragged(MouseEvent e) {
    if (MouseStatic.LAST_CLICKED_BUTTON == MouseEvent.BUTTON1) {
      Game game = EngineWindow.getInstance().getGame();
      game.setSelectBox(
              new SelectBox(
              MouseStatic.LAST_LEFT_MOUSE_CLICKED_POINT.x,
              MouseStatic.LAST_LEFT_MOUSE_CLICKED_POINT.y,
              e.getX() - MouseStatic.LAST_LEFT_MOUSE_CLICKED_POINT.x,
              e.getY() - MouseStatic.LAST_LEFT_MOUSE_CLICKED_POINT.y));
    } else if (MouseStatic.LAST_CLICKED_BUTTON == MouseEvent.BUTTON3) {
      Point offset = new Point(e.getX() - MouseStatic.LAST_RIGHT_MOUSE_CLICKED_OFFSET_POINT.x,
              e.getY() - MouseStatic.LAST_RIGHT_MOUSE_CLICKED_OFFSET_POINT.y);
      // Point offset = new Point(MouseStatic.LAST_CLICKED_POINT.x - e.getX(),
      //        MouseStatic.LAST_CLICKED_POINT.y - e.getY());
      EngineWindow.getInstance().getGame().getPlayfieldGrid().setDrawOffset(offset);
    }
    MouseStatic.PERFORMED_DRAG = true;
  }

  public void mouseMoved(MouseEvent e) {
    if (EngineWindow.getInstance().getGame() == null) return;
    Game game = EngineWindow.getInstance().getGame();
    PlayfieldGrid grid = game.getPlayfieldGrid();
    if (grid == null) return;
    Point p = grid.translatePixelToGrid(e.getX() - grid.getDrawOffset().x, e.getY() - grid.getDrawOffset().y);
    if (p != null) {
      if (this.getPreviousSelectedSquare() != null) {
        this.getPreviousSelectedSquare().setSelected(false);
      }
      this.setPreviousSelectedSquare(grid.getSquares()[p.x][p.y]);
      this.getPreviousSelectedSquare().setSelected(true);
    } else if (this.getPreviousSelectedSquare() != null) {
      this.getPreviousSelectedSquare().setSelected(false);
      this.setPreviousSelectedSquare(null);
    }
    handleMouseOver(e);
  }

  /**
   * Handle the mouseover of buildings and moveables.
   */
  public void handleMouseOver(MouseEvent e) {
    Game game = EngineWindow.getInstance().getGame();

    boolean didMouseOverMoveable = false;
    for (Moveable moveable : game.getMoveableObjects()) {
      if (moveable.getDrawBoundingBox().contains(e.getPoint())) {
        // Only one at a time
        if (!didMouseOverMoveable) {
          moveable.setMouseOver(true);
          didMouseOverMoveable = true;
          // Change cursor, as we've selected something
          EngineWindow.getInstance().setCursor(HAND_CURSOR);
        }
      } // But still deselect other units
      else moveable.setMouseOver(false);
    }
    if (!didMouseOverMoveable) EngineWindow.getInstance().setCursor(DEFAULT_CURSOR);

    boolean didMouseOverBuilding = false;
    for (Building building : game.getBuildingList()) {
      if (building.canSelect() && building.getDrawBoundingBox().contains(e.getPoint())) {
        // Only one at a time
        if (!didMouseOverBuilding) {
          building.setMouseOver(true);
          didMouseOverBuilding = true;
          // Change cursor, as we've selected something
          EngineWindow.getInstance().setCursor(HAND_CURSOR);
        }
      } // But still deselect other units
      else building.setMouseOver(false);
    }
    if (!didMouseOverMoveable && !didMouseOverBuilding)
      EngineWindow.getInstance().setCursor(DEFAULT_CURSOR);
  }

  public MouseMotionAdapter() {
  }
}
