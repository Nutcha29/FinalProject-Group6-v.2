/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.awt.Dimension;
import java.awt.Toolkit;

import controller.MenuButtonListener;
import view.EngineWindow;
import view.menu.MenuGroup;
import view.menu.MenuItem;

/**
 *
 * @author Wouter
 */
public class CopyOfTestEngineWindow extends EngineWindow {


  CopyOfTestEngineWindow engineWindow;
  /**
   * Main Menu Group with Menu Items.
   */
  MenuGroup mainMenuGroup;
  MenuItem newGame;
  MenuItem options;
  MenuItem quitGame;
  /**
   * Options Menu Group with Menu Items.
   */
  MenuGroup optionsGroup;
  MenuItem videoOptions = new MenuItem();
  MenuItem soundOptions = new MenuItem();
  MenuItem optionsBack = new MenuItem();
  /**
   * Ingame Menu Group with Menu Items.
   */
  MenuGroup ingameMenuGroup;
  MenuItem ingameResumeGame = new MenuItem();
  MenuItem ingameQuitGame = new MenuItem();

  public CopyOfTestEngineWindow() {
    start();
  }

  private void start() {
    engineWindow = this;
    // Semi-singleton
    EngineWindow.setInstance(this);
    initialise();
    showMainMenu();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
    }
    CopyOfTestEngineWindow tew = new CopyOfTestEngineWindow();
  }

  @Override
  public void showMainMenu() {
    mainMenuGroup = new MenuGroup(this);
    // Height is 0 because it is managed by the Menu Group
    mainMenuGroup.setBounds((this.getWidth() / 2) - 200, (this.getHeight() / 2) - 125,
            400, 0);

    mainMenuGroup.setVisible(true);

    newGame = new MenuItem();
    newGame.setText("Start Simulation");
    newGame.addActionListener(new MenuButtonListener(1));

    options = new MenuItem();
    options.setText("Options");
    options.addActionListener(new MenuButtonListener(2));

    quitGame = new MenuItem();
    quitGame.setText("Quit Simulation");
    quitGame.addActionListener(new MenuButtonListener(3));

    mainMenuGroup.addMenuItem(newGame);
    mainMenuGroup.addMenuItem(options);
    mainMenuGroup.addMenuItem(quitGame);

    this.getCanvas().add(mainMenuGroup);
    this.getCanvas().validate();
    this.getCanvas().repaint();
  }

  /**
   * Re-creates and shows the options menu.
   */
  public void showOptionsMenu() {
    optionsGroup = new MenuGroup(this);
    // Height is 0 because it is managed by the Menu Group
    optionsGroup.setBounds((this.getWidth() / 2) - 200, (this.getHeight() / 2) - 125,
            400, 0);

    optionsGroup.setVisible(true);

    videoOptions = new MenuItem();
    videoOptions.setText("Video Options");
    videoOptions.addActionListener(new MenuButtonListener(4));
    optionsGroup.addMenuItem(videoOptions);

    soundOptions = new MenuItem();
    soundOptions.setText("Sound Options");
    soundOptions.addActionListener(new MenuButtonListener(5));
    optionsGroup.addMenuItem(soundOptions);

    optionsBack = new MenuItem();
    optionsBack.setText("Back");
    optionsBack.addActionListener(new MenuButtonListener(6));
    optionsGroup.addMenuItem(optionsBack);


    this.getCanvas().add(optionsGroup);
    this.getCanvas().validate();
    this.getCanvas().repaint();
  }

  @Override
  public void showIngameMenu() {
    ingameMenuGroup = new MenuGroup(this);
    // Height is 0 because it is managed by the Menu Group
    ingameMenuGroup.setBounds((this.getWidth() / 2) - 200, (this.getHeight() / 2) - 125,
            400, 0);

    ingameMenuGroup.setVisible(true);

    ingameResumeGame = new MenuItem();
    ingameResumeGame.setText("Resume Simulation");
    ingameResumeGame.addActionListener(new MenuButtonListener(7));
    ingameMenuGroup.addMenuItem(ingameResumeGame);

    ingameQuitGame = new MenuItem();
    ingameQuitGame.setText("Quit Simulation");
    ingameQuitGame.addActionListener(new MenuButtonListener(3));
    ingameMenuGroup.addMenuItem(ingameQuitGame);


    this.getCanvas().add(ingameMenuGroup);
    this.getCanvas().validate();
    this.getCanvas().repaint();
  }
}
