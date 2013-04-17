/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package variables;

import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public class GeneralStatic {
  public static String PROGRAM_TITLE = "WoF-JE Engine Window";

  /**
   * Variable used for independent frame movement. Multiplying your movement
   * variables etc with this variable, will ensure that movement will stay roughly
   * the same with varying FPS.
   * DOES NOT WORK YET
   */
  public static double TIME_STEP = 1;

  public static int MAX_FPS = 60;

  /**
   * The current game state.
   */
  private static int GAME_STATE = 0;

  /**
   * Different states the game can be in.
   */
  public static int STATE_PAUSED = 1;
  public static int STATE_RUNNING = 2;
  public static int STATE_KILL_ENGINE = 3;
  public static int STATE_JUST_PARTICLES = 4;

  /**
   * Sets the game state.
   * STATE_PAUSED
   * STATE_RUNNING
   * @param state The state, as found in Static.<state>
   */
  public static void setGameState(int state){
    if( state == STATE_PAUSED ) {
      EngineWindow.getInstance().getGame().setCurrentFPS(0);
    }
    GAME_STATE = state;
  }

  /**
   * Gets the game state.
   * @return The game state.
   */
  public static int getGameState(){
    return GAME_STATE;
  }
}
