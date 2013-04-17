/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.game.Game;
import model.core.PlayfieldGrid;
import variables.MouseStatic;
import view.EngineWindow;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import model.core.Square;
import model.core.buildings.Building;
import model.core.moveable.Moveable;
import variables.KeyStatic;
import view.SelectBox;

/**
 *
 * @author Wouter
 */
public class MouseClickAdapter implements MouseListener {

  public MouseClickAdapter() {
  }

  public void mouseClicked(MouseEvent e) {
    // MouseMo
  }

  public void mousePressed(MouseEvent e) {
    Game game = EngineWindow.getInstance().getGame();
    MouseStatic.LAST_CLICKED_BUTTON = e.getButton();
    if (game != null) {
      PlayfieldGrid grid = game.getPlayfieldGrid();
      if (e.getButton() == MouseEvent.BUTTON1) {
        MouseStatic.LAST_LEFT_MOUSE_CLICKED_POINT = e.getPoint();
        MouseStatic.LAST_LEFT_MOUSE_CLICKED_OFFSET_POINT = new Point(e.getPoint().x - grid.getDrawOffset().x,
                e.getPoint().y - grid.getDrawOffset().y);
        if (!KeyStatic.SHIFT_HELD) {
          deselectMoveables();
          if (!KeyStatic.ANY_KEY_HELD) deselectBuildings();
        }
        //check user isControllable here.
        selectMoveable();
      } else if (e.getButton() == MouseEvent.BUTTON3) {
        MouseStatic.LAST_RIGHT_MOUSE_CLICKED_POINT = e.getPoint();
        MouseStatic.LAST_RIGHT_MOUSE_CLICKED_OFFSET_POINT = new Point(e.getPoint().x - grid.getDrawOffset().x,
                e.getPoint().y - grid.getDrawOffset().y);
      }
    }
    // System.out.println("Pressed at " + e.getPoint());
  }

  public void mouseReleased(MouseEvent e) {
    SelectBox box = EngineWindow.getInstance().getGame().getSelectBox();
    if (box != null) {
      if (!KeyStatic.SHIFT_HELD) {
        deselectMoveables();
      }
      selectMoveablesInBox();
    } else if (!MouseStatic.PERFORMED_DRAG) {
      if (MouseStatic.LAST_CLICKED_BUTTON == MouseEvent.BUTTON1) {
        // Check if the user had selected a unit with a mouseover
        if (!MouseStatic.SELECTED_UNIT_BY_CLICK) {
          deselectMoveables();
          // Select a building if it should
          if (!KeyStatic.ANY_KEY_HELD) selectBuilding();
        } else {
          MouseStatic.SELECTED_UNIT_BY_CLICK = false;
        }
      } else if (MouseStatic.LAST_CLICKED_BUTTON == MouseEvent.BUTTON3) {
        // Do pathfinding in a new thread, to increase performance by a ton!
    	//check user isControllable here.
        moveUnitToTarget();
      }
    }
    MouseStatic.PERFORMED_DRAG = false;
  }

  public void mouseEntered(MouseEvent e) {
    // throw new UnsupportedOperationException("Not supported yet.");
  }

  public void mouseExited(MouseEvent e) {
    // throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * De-selects all moveable units.
   */
  public void deselectMoveables() {
    // User clicked without dragging -> deselect units
    for (Moveable m : EngineWindow.getInstance().getGame().getMoveableObjects()) {
      m.setSelected(false);
    }
  }

  /**
   * De-selects all buildings.
   */
  public void deselectBuildings() {
    Game game = EngineWindow.getInstance().getGame();
    game.setSelectedBuilding(null);
    // Deselect all buildings
    for (Building b : game.getBuildingList()) {
      b.setSelected(false);
    }
  }

  /**
   * Selects all moveable units in the box
   */
  public void selectMoveablesInBox() {
    Game game = EngineWindow.getInstance().getGame();
    SelectBox box = game.getSelectBox();
    // Grid grid = EngineWindow.getInstance().getGame().getPlayfieldGrid();
    for (Moveable m : game.getMoveableObjects()) {
      // Rectangle offsetRect = new Rectangle( box.x, box.y, box.width, box.height);
      if (box.eliminateNegatives().contains(m.getDrawPixelLocation())) {
        m.setSelected(true);
      }
    }
    EngineWindow.getInstance().getGame().setSelectBox(null);
  }

  /**
   * Selects a building if we should.
   */
  public void selectBuilding() {
    Game game = EngineWindow.getInstance().getGame();
    for (Building building : game.getBuildingList()) {
      if (building.isMouseOver()) {
        building.setSelected(!building.isSelected());
        game.setSelectedBuilding(building);
        break;
      }
    }
  }

  /**
   * Selects a moveable if we should.
   */
  public void selectMoveable() {
    Game game = EngineWindow.getInstance().getGame();
    for (Moveable m : game.getMoveableObjects()) {
      if (m.isMouseOver()) {
        m.setSelected(true);
        MouseStatic.SELECTED_UNIT_BY_CLICK = true;
        // Just one
        break;
      }
    }
  }

  /**
   * Spawns a new thread, that will move all selected units to the target.
   */
  public void moveUnitToTarget() {
    Runnable pathFinder = new Runnable() {

      public void run() {
        Game game = EngineWindow.getInstance().getGame();
        Square s = game.getSelectedSquare();
        if (s != null) {
          for (Moveable m : game.getSelectedUnits()) {
        	  if(true){//<------replace true with boolean value from checking isThisYourIceTizen(); //deprecated doesn't need to check. done with tokens.
        		  if (KeyStatic.SHIFT_HELD) {
        			  m.moveToAdditional(s);
        		  }
        		  else {
        			  m.moveTo(s);
        			  //m.moveTo2(50, 50);
        		  }
        	  }
            
          }
        }
      }
    };
    new Thread(pathFinder).start();
  }
}
