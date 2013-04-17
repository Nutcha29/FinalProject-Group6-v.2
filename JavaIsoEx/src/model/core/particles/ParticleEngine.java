/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.particles;

import java.util.LinkedList;

/**
 *
 * @author Wouter
 */
public class ParticleEngine {

  private static ParticleEngine instance = null;
  private LinkedList<ParticleEffect> particleEffects = new LinkedList<ParticleEffect>();
  private int waitTime = 10;

  public LinkedList<ParticleEffect> getParticleEffects() {
    return particleEffects;
  }

  public int getWaitTime() {
    return waitTime;
  }

  public void updateParticles() {
    // Remove dead particles
    for (int i = 0; i < this.getParticleEffects().size(); i++) {
      // Get effect
      ParticleEffect effect = this.getParticleEffects().get(i);

      // Update effect lifespan
      if (effect.getLifespan() != Integer.MAX_VALUE) {
        effect.setCurrentLifespan(effect.getCurrentLifespan() - this.getWaitTime());
        if (effect.getCurrentLifespan() < 0) {
          // Effect will die slowly
          effect.setInterval(Integer.MAX_VALUE);
        }
      }

      if (effect.getInterval() != Integer.MAX_VALUE) {
        // Update effect spawn rate
        effect.setCurrentInterval(effect.getCurrentInterval() - this.getWaitTime());
        // If we should, add a new particle
        if (effect.getCurrentInterval() < 0) {
          for (int j = 0; j < effect.getParticleSpawnCount(); j++) {
            effect.createNewParticle();
          }
          effect.setCurrentInterval(effect.getInterval());
        }
      }


      for (int j = 0; j < effect.getParticleList().size(); j++) {
        // Get Particle
        AbstractParticle particle = effect.getParticleList().get(j);
        if (particle.getLifespan() != Integer.MAX_VALUE) {
          // Update its lifespan
          particle.setCurrentLifespan(particle.getCurrentLifespan() - this.getWaitTime());
          // Remove if dead
          if (particle.getCurrentLifespan() < -1) {
            effect.getParticleList().remove(particle);
            if (effect.getParticleList().isEmpty() && effect.getLifespan() == 0) {
              // System.out.println("Removing particle effect, last particle was removed and lifespan was 0.");
              ParticleEngine.getInstance().getParticleEffects().remove(effect);
            }
            j--;
          }
        }
        particle.updateEffect();
        particle.doAdditionalUpdates();
      }
    }
  }

  /*public void run() {
  int count = 0;
  while (GeneralStatic.getGameState() != GeneralStatic.STATE_KILL_ENGINE) {
  while (GeneralStatic.getGameState() == GeneralStatic.STATE_RUNNING) {
  updateParticles();
  // System.out.println("Updated particle effects (" + count + ")");
  try {
  Thread.sleep(10);
  } catch (InterruptedException ex) {
  Logger.getLogger(ParticleEngine.class.getName()).log(Level.SEVERE, null, ex);
  }
  count++;
  }
  // System.out.println("Particle engine waiting...");
  try {
  Thread.sleep(100);
  } catch (InterruptedException ex) {
  Logger.getLogger(ParticleEngine.class.getName()).log(Level.SEVERE, null, ex);
  }
  }
  System.err.println("Particle engine died.");
  }*/
  public static ParticleEngine getInstance() {
    if (instance == null) instance = new ParticleEngine();
    return instance;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Singleton, baby.");
  }

  private ParticleEngine() {
  }
}
