/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import model.core.Grid;
import model.core.moveable.Moveable;
import variables.GeneralStatic;
import variables.KeyStatic;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public class KeyAdapter implements KeyListener {

  public void keyTyped(KeyEvent e) {
    //throw new UnsupportedOperationException("Not supported yet.");
    // System.out.println("Key typed!");
  }

  public void keyPressed(KeyEvent e) {
    // System.out.println("Key pressed!");
    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
      KeyStatic.SHIFT_HELD = true;
      LinkedList<Moveable> selectedUnits = EngineWindow.getInstance().getGame().getSelectedUnits();
      Grid grid = EngineWindow.getInstance().getGame().getPlayfieldGrid();
      for (int i = 0; i < selectedUnits.size(); i++) {
        Moveable currentUnit = selectedUnits.get(i);
        LinkedList<Point> currentWaypoints = currentUnit.getWaypoints();
        for (int j = 0; j < currentWaypoints.size(); j++) {
          grid.getSquareFromPoint(currentWaypoints.get(j)).setHighlighted(50);
        }
      }
    }

    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      GeneralStatic.setGameState(GeneralStatic.STATE_PAUSED);
      EngineWindow.getInstance().showIngameMenu();
    }

    if (e.getKeyCode() == KeyEvent.VK_P) {
      if (GeneralStatic.getGameState() == GeneralStatic.STATE_RUNNING) {
        GeneralStatic.setGameState(GeneralStatic.STATE_PAUSED);
      } else GeneralStatic.setGameState(GeneralStatic.STATE_RUNNING);
    }
    KeyStatic.ANY_KEY_HELD = true;
  }

  public void keyReleased(KeyEvent e) {
    //throw new UnsupportedOperationException("Not supported yet.");
    // System.out.println("Key released!");
    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
      KeyStatic.SHIFT_HELD = false;
    }
    KeyStatic.ANY_KEY_HELD = false;
  }
}
