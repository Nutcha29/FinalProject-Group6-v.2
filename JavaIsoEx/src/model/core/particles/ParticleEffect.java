/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.particles;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import model.core.DoublePoint;
import model.core.DrawableObject;
import model.core.PlayfieldSquare;
import model.core.events.ZoomChangeEvent;
import variables.ZoomStatic;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public abstract class ParticleEffect extends DrawableObject {

  private ArrayList<AbstractParticle> particleList = new ArrayList<AbstractParticle>();
  private int lifespan = Integer.MAX_VALUE;
  private int currentLifespan = 0;
  private int interval = 0;
  private int currentInterval = 0;
  private int particleSpawnCount = 1;
  public static double X_SPEED_MODIFIER = 1, Y_SPEED_MODIFIER = 1;

  /**
   * Destroys the effect, making it stop spawning particles.
   */
  public void destroyEffect() {
    // System.out.println("Destroy effect called!");
    this.setLifespan(0);
  }

  /**
   * Gets the amount of particles that spawn when the currentInterval reaches 0
   * (when a new particle should be created). Default is 1.
   * @return
   */
  public int getParticleSpawnCount() {
    return particleSpawnCount;
  }

  /**
   * Sets the amount of particles that spawn on a tick. Default is 1.
   * @param particleSpawnCount The new amount.
   */
  public void setParticleSpawnCount(int particleSpawnCount) {
    this.particleSpawnCount = particleSpawnCount;
  }

  public int getCurrentLifespan() {
    return currentLifespan;
  }

  public void setCurrentLifespan(int currentLifespan) {
    this.currentLifespan = currentLifespan;
  }

  /**
   * Gets the interval on which to spawn new particles. If this is Integer.MAX_VALUE,
   * no new particles should be spawned.
   * @return
   */
  public int getInterval() {
    return interval;
  }

  /**
   * Sets the interval on which to spawn new particles.
   * @param interval The new interval. Use Integer.MAX_VALUE for no new particles.
   * Use a negative value or 0 for infinite spawns (laggy, but hey, your choice).
   */
  public void setInterval(int interval) {
    this.currentInterval = interval;
    this.interval = interval;
  }

  /**
   * Gets the current interval on which to spawn new particles.
   * @return The current interval.
   */
  public int getCurrentInterval() {
    return currentInterval;
  }

  /**
   * Sets the current interval on which to spawn new particles.
   * @param interval The new current interval.
   */
  public void setCurrentInterval(int interval) {
    this.currentInterval = interval;
  }

  /**
   * Called when the zoom changes.
   * @param event The event.
   */
  public void onZoomChange(ZoomChangeEvent event) {
    this.setPixelLocation(new DoublePoint(this.getPixelLocation().x * event.getxFactor(),
            this.getPixelLocation().y * event.getyFactor()));
    for (int i = 0; i < this.getParticleList().size(); i++) {
      this.getParticleList().get(i).onZoomChange(event);
    }

    ParticleEffect.X_SPEED_MODIFIER = ((double) PlayfieldSquare.WIDTH / (double) ZoomStatic.ZOOM_LARGE_WIDTH);
    ParticleEffect.Y_SPEED_MODIFIER = ((double) PlayfieldSquare.HEIGHT / (double) ZoomStatic.ZOOM_LARGE_HEIGHT);
  }

  /**
   * Is called on death.
   */
  /*public void onDeath() {
  this.getParticleList().clear();
  ParticleEngine.getInstance().getParticleEffects().remove(this);
  }*/
  /**
   * Gets the lifespan in MS of this effect.
   * @return The new lifespan.
   */
  public int getLifespan() {
    return lifespan;
  }

  /**
   * Sets the lifespan of this effect.
   * @param lifespan The lifespan in MS.
   */
  public void setLifespan(int lifespan) {
    this.lifespan = lifespan;
    this.setCurrentLifespan(lifespan);
  }

  /**
   * Gets the list of particles of this effect.
   * @return The list.
   */
  public ArrayList<AbstractParticle> getParticleList() {
    return particleList;
  }

  @Override
  public void draw(Graphics g) {
    // System.out.println("Drawing particle effect @ " + this.getDrawPixelLocation() + "!");
    for (int i = 0; i < particleList.size(); i++) {
      particleList.get(i).draw(g);
    }
  }

  @Override
  public Point getDrawPixelLocation() {
    Point offset = EngineWindow.getInstance().getGame().getPlayfieldGrid().getDrawOffset();
    return new Point((int) ((this.getPixelLocation().x) + offset.x) - (PlayfieldSquare.WIDTH / 2),
            (int) ((this.getPixelLocation().y) + offset.y) - (PlayfieldSquare.HEIGHT / 2));
  }

  public abstract AbstractParticle createNewParticle();

  public ParticleEffect(DoublePoint location) {
    this.setPixelLocation(location);
    // System.out.println("Adding particle effect!");
    ParticleEngine.getInstance().getParticleEffects().add(this);
  }
}
