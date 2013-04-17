/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.game.Game;
import variables.GeneralStatic;
import view.menu.MenuGroup;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Wouter
 */
public abstract class EngineWindow extends JFrame {
	Toolkit toolkit = Toolkit.getDefaultToolkit(); // get screen size
	Dimension screensize = toolkit.getScreenSize(); // get screen size
  private Canvas canvas = null;
  private Game game;
  private static EngineWindow ENGINE_WINDOW = null;
  private Background backgroundImage;

  // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
  public static EngineWindow getInstance() {
    return ENGINE_WINDOW;
  }

  protected static void setInstance(EngineWindow instance) {
    EngineWindow.ENGINE_WINDOW = instance;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

  public Background getBackgroundImage() {
    return backgroundImage;
  }

  public void setBackgroundImage(Background backgroundImage) {
    this.backgroundImage = backgroundImage;
  }
  // </editor-fold>

  public void initialise() {
    // Hide while loading
    setVisible(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setResizable(true);
    setFocusable(true);

    setSize(screensize);

    setTitle(GeneralStatic.PROGRAM_TITLE);
    setVisible(true);

    this.setCanvas(new Canvas());
    add(this.getCanvas());
    this.center();
    this.repaint(50);

  }

  /**
   * Centers the UI in the middle of the screen.
   */
  public void center() {
    this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getWidth() / 2,
            Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getHeight() / 2);
    this.validate();
    this.repaint();
  }

  /**
   * Re-creates and shows the main menu.
   */
  public abstract void showMainMenu();

  /**
   * Re-creates and shows the ingame menu.
   */
  public abstract void showIngameMenu();

  /**
   * Hides the main menu.
   */
  public void removeAllMenus() {
    for (int i = 0; i < canvas.getComponentCount(); i++) {
      if (canvas.getComponent(i) instanceof MenuGroup) {
        canvas.remove(i);
      }
    }
    canvas.validate();
    canvas.repaint();
  }

public void setRI(int parseInt) {
	
}

public void talk(String talktext) {
	
}


}
