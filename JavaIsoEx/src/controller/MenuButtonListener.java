/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import example.LaunchGame;
import example.TestEngineWindow;
import example.UpdateGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public class MenuButtonListener implements ActionListener {

  private int btnNr = 0;

  public MenuButtonListener(int btnNr) {
    this.btnNr = btnNr;
  }

  public void actionPerformed(ActionEvent e) {
    switch (btnNr) {
      // Main menu, new game
      case 1: {
        // Hide the main menu
        EngineWindow.getInstance().removeAllMenus();

        System.out.println("Starting new game..");

        // Instantise a new game.
        EngineWindow.getInstance().setGame(new LaunchGame(new UpdateGame(60)));
        // Starts the game
        new Thread(EngineWindow.getInstance().getGame()).start();
        EngineWindow.getInstance().getCanvas().addZoomButtons();
        break;
      }
      // Main menu, options
      case 2: {
        EngineWindow.getInstance().removeAllMenus();
        ((TestEngineWindow) EngineWindow.getInstance()).showOptionsMenu();
        break;
      }
      // Main menu, exit
      case 3: {
        System.exit(0);
        break;
      }
      // Options menu, Videao
      case 4: {
        break;
      }
      // Options menu, Sound options
      case 5: {
        break;
      }
      // Options menu, Back
      case 6: {
        EngineWindow.getInstance().removeAllMenus();
        EngineWindow.getInstance().showMainMenu();
        break;
      }
      case 7: {
    	  EngineWindow.getInstance().removeAllMenus();
    	  break;
      }
    }
  }
}
