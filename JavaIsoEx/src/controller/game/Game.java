/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.game;

import controller.KeyAdapter;
import controller.MouseClickAdapter;
import controller.MouseMotionAdapter;
import java.awt.Point;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import model.core.PlayfieldGrid;
import variables.GeneralStatic;
import view.EngineWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.core.Square;
import model.core.buildings.Building;
import model.core.moveable.Moveable;
import view.Canvas;
import view.SelectBox;

/**
 *
 * @author Wouter
 */
public abstract class Game implements Runnable {

  public abstract void onStart();

  public abstract void onDraw();
  private int totalFrames = 0;
  private double currentFPS = 0;
  private PlayfieldGrid playfieldGrid;
  private int squaresDrawn = 0;
  private LinkedList<Moveable> moveableObjects = new LinkedList<Moveable>();
  private LinkedList<Moveable> selectedUnits = new LinkedList<Moveable>();
  private LinkedList<Building> buildings = new LinkedList<Building>();
  private Building selectedBuilding = null;
  private SelectBox selectBox = null;
  private GameUpdateLoop loop = null;

  // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
  /**
   * Gets the update loop of this game.
   * @return The loop.
   */
  public GameUpdateLoop getLoop() {
    return loop;
  }

  /**
   * Sets the game loop of this game.
   * @param loop
   */
  public void setLoop(GameUpdateLoop loop) {
    this.loop = loop;
  }

  public LinkedList<Building> getBuildingList() {
    return buildings;
  }

  /**
   * Sets the selected building of this game.
   * @param building The new building.
   */
  public void setSelectedBuilding(Building building) {
    this.selectedBuilding = building;
  }

  /**
   * Gets the selected building.
   * @return The building, or null if none was selected.
   */
  public Building getSelectedBuilding() {
    return selectedBuilding;
  }

  /**
   * Gets the Square that is currently selected.
   * @return The Square, or null if there ain't any.
   */
  public Square getSelectedSquare() {
    MouseMotionListener[] listeners = EngineWindow.getInstance().getCanvas().getMouseMotionListeners();
    for (int i = 0; i < listeners.length; i++) {
      if (listeners[i] instanceof MouseMotionAdapter) {
        return ((MouseMotionAdapter) listeners[i]).getPreviousSelectedSquare();
      }
    }
    return null;
  }

  public SelectBox getSelectBox() {
    return selectBox;
  }

  public void setSelectBox(SelectBox selectBox) {
    this.selectBox = selectBox;
  }

  public LinkedList<Moveable> getSelectedUnits() {
    return selectedUnits;
  }

  public LinkedList<Moveable> getMoveableObjects() {
    return moveableObjects;
  }

  public int getSquaresDrawn() {
    return squaresDrawn;
  }

  public void drawnSquare() {
    squaresDrawn++;
  }

  /**
   * Sets the amount of squares drawn this frame.
   * @param squaresDrawn The amount.
   */
  protected void setSquaresDrawn(int squaresDrawn) {
    this.squaresDrawn = squaresDrawn;
  }

  public PlayfieldGrid getPlayfieldGrid() {
    return playfieldGrid;
  }

  public void setPlayfieldGrid(PlayfieldGrid playfieldGrid) {
    this.playfieldGrid = playfieldGrid;
  }

  /**
   * Gets the current FPS (approximate).
   * @return The FPS.
   */
  public double getCurrentFPS() {
    return currentFPS;
  }

  /**
   * Sets the current FPS (merely a number for keeping track, doesn't really change FPS if set to a number.
   * To change FPS, use Static.MAX_FPS.
   * @param currentFPS The new FPS.
   */
  public void setCurrentFPS(double currentFPS) {
    this.currentFPS = currentFPS;
  }

  /**
   * Gets the total frames of this game.
   * @return The int containing the total frames.
   */
  public int getTotalFrames() {
    return totalFrames;
  }

  /**
   * Sets the total frames of this game.
   * @param totalFrames The new int containing the total frames.
   */
  public void setTotalFrames(int totalFrames) {
    this.totalFrames = totalFrames;
  }
  // </editor-fold>

  public void run() {

    EngineWindow.getInstance().getCanvas().add(EngineWindow.getInstance().getCanvas().getLoadingScreen());
    this.onStart();
    EngineWindow.getInstance().getCanvas().remove(EngineWindow.getInstance().getCanvas().getLoadingScreen());

    // Add listeners
    EngineWindow.getInstance().getCanvas().addMouseMotionListener(new MouseMotionAdapter());
    EngineWindow.getInstance().getCanvas().addMouseListener(new MouseClickAdapter());
    EngineWindow.getInstance().addKeyListener(new KeyAdapter());



    // Start particle engine
    new Thread(this.getLoop()).start();
    this.getPlayfieldGrid().setDrawOffset(
            new Point(-this.getPlayfieldGrid().getSquares()[0][0].getPixelX()
            + (EngineWindow.getInstance().getWidth() / 2), 0));
    System.out.println(this.getPlayfieldGrid().getDrawOffset());
    double msPerFrame = 1000 / GeneralStatic.MAX_FPS;
    long startGameMS = System.currentTimeMillis();


    int previousTickFrames = 0;
    long previousTickMS = 0;
    while (GeneralStatic.getGameState() != GeneralStatic.STATE_KILL_ENGINE) {
      // while (GeneralStatic.getGameState() == GeneralStatic.STATE_RUNNING) {
      long startMS = System.currentTimeMillis();
      // For every second the game has been running
      if ((startMS - previousTickMS) >= 1000) {
        // Update the FPS
        this.setCurrentFPS(this.getTotalFrames() - previousTickFrames);
        // System.out.println("Updating FPS to " + ( TOTAL_FRAMES - previousTickFrames ) );
        previousTickFrames = this.getTotalFrames();
        previousTickMS = startMS;
        EngineWindow.getInstance().getCanvas().setFPS(this.getCurrentFPS());
        EngineWindow.getInstance().getCanvas().setSquaresDrawn(this.getSquaresDrawn());
      }

      // Update the visible rectangle of the screen
      Canvas c = EngineWindow.getInstance().getCanvas();
      c.setViewPort(c.getVisibleRect());
      this.setSquaresDrawn(0);



      onDraw();



      EngineWindow.getInstance().repaint();
      // Now repainting, if it wasn't already
      // EngineWindow.getInstance().getCanvas().setCurrentlyRepainting(true);
      // End game logic
      long runTime = (System.currentTimeMillis() - startMS);
      long msWaitedForRepaint = 0;
      while (EngineWindow.getInstance().getCanvas().isCurrentlyRepainting()) {
        try {
          Thread.sleep(1);
          msWaitedForRepaint++;
          /*if (msWaitedForRepaint >= msPerFrame) {
          System.out.println("Unable to to repaint quicker than the FPS requires!");
          break;
          }*/
        } catch (InterruptedException ex) {
          Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      long msToSleep = (long) Math.round(msPerFrame - (runTime + msWaitedForRepaint));
      if (msToSleep > 0) {
        try {
          Thread.sleep(msToSleep);
        } catch (InterruptedException ex) {
          Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        GeneralStatic.TIME_STEP = 1;
      } else {
        GeneralStatic.TIME_STEP = (runTime + msWaitedForRepaint) / msPerFrame;
      }

      this.setTotalFrames(this.getTotalFrames() + 1);

      // Once every ~10 seconds, assuming 30 FPS is achieved
      if (this.getTotalFrames() % 3000 == 0) {
        // System.out.println("Time step: " + GeneralStatic.TIME_STEP);
        //if (this.getTotalFrames() % 3000 == 0) {
        System.out.println("   " + this.getTotalFrames() + " frames @" + this.getCurrentFPS() + " FPS\n"
                + "   (Running for " + (System.currentTimeMillis() - startGameMS) + "ms)\n"
                + "   (Slept for " + msToSleep + "ms, calculated for " + runTime + "ms and waited " + msWaitedForRepaint + "ms for repaint, out of " + msPerFrame + "ms per frame)\n"
                + "   (Last frame took " + (System.currentTimeMillis() - startMS) + "ms, drawing " + this.getSquaresDrawn() + " squares)");
        //}
      }
      // }
    }
    /*try {
    Thread.sleep(100);
    } catch (InterruptedException ex) {
    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
    }*/

    this.setTotalFrames(0);
  }

  /**
   * Creates a new game with the given game loop to update units etc etc.
   * @param loop The loop.
   */
  public Game(GameUpdateLoop loop) {
    this.setLoop(loop);
  }
}
