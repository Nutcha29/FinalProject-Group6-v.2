/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package example;

import controller.game.GameUpdateLoop;

/**
 *
 * @author Wouter
 */
public class UpdateGame extends GameUpdateLoop {

  @Override
  public void onUpdate() {
    // throw new UnsupportedOperationException("Not supported yet.");
  }


  public UpdateGame(int ticksPerFrame ){
    super(ticksPerFrame);
  }
}
