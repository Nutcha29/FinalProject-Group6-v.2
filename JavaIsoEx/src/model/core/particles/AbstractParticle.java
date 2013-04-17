/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.particles;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import model.core.DoublePoint;
import model.core.DrawableObject;
import model.core.PlayfieldSquare;
import model.core.events.ZoomChangeEvent;
import model.core.texture.ParticleTextureCollection;
import variables.ZoomStatic;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public abstract class AbstractParticle extends DrawableObject {

  private double rotation = 0.0, rotationSpeed = 0.0;
  private double scale = 1.0;
  private double previousScale = 0;
  private double xSpeed = 0, ySpeed = 0;
  private int lifespan = -1, currentLifespan = 0;
  private ParticleTextureCollection texture = null;
  private ParticleEffect effect = null;
  private int opacity = 100;
  private int hypotenuse = 0;

  // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
  /**
   * Gets the previous scale at which the image was scaled.
   * @return The previous scale.
   */
  private double getPreviousScale() {
    return previousScale;
  }

  /**
   * Sets the previous scale at which the image was scaled.
   * @param previousScale The new previous scale.
   */
  private void setPreviousScale(double previousScale) {
    this.previousScale = previousScale;
  }

  /**
   * Gets the scale of this particle. [0..~], default = 1.0.
   * @param scale The scale.
   */
  public double getScale() {
    return scale;
  }

  /**
   * Sets the scale of this particle. [0..~], default = 1.0. Scales smaller than
   * 0 will be ignored.
   * @param scale The new scale.
   */
  public void setScale(double scale) {
    if (scale < 0) return;
    this.scale = scale;
  }

  /**
   * Gets the hypotenuse of this particle.
   * @return The hypotenuse, rounded up.
   */
  private int getHypotenuse() {
    return hypotenuse;
  }

  /**
   * Sets the hypotenuse of this particle.
   * @param hypotenuse The new hypotenuse (round this up).
   */
  private void setHypotenuse(int hypotenuse) {
    this.hypotenuse = hypotenuse;
  }

  /**
   * Gets the percentage at which the lifespan of this particle is.
   * @return 100 when this particle is fully alive, 0 when dead, or any value in between
   */
  public int getLifespanPercentage() {
    return (int) ((this.getCurrentLifespan() / (double) this.getLifespan()) * 100);
  }

  /**
   * Gets the current lifespan of this particle.
   * @return The current lifespan.
   */
  public int getCurrentLifespan() {
    return currentLifespan;
  }

  /**
   * Sets the current lifespan of this particle.
   * @param currentLifespan The new current lifespan.
   */
  public void setCurrentLifespan(int currentLifespan) {
    this.currentLifespan = currentLifespan;
  }

  /**
   * Gets the opacity of this particle.
   * @return The opacity
   */
  public int getOpacity() {
    return opacity;
  }

  /**
   * Sets the opacity of this particle. Valid ranges: 0-100, and will be changed
   * if invalid ranges are entered (-50 = 0, 150 = 100).
   * @param opacity The new opacity.
   */
  public void setOpacity(int opacity) {
    if (opacity < 0) opacity = 0;
    else if (opacity > 100) opacity = 100;
    this.opacity = opacity;
  }

  public ParticleEffect getParticleEffect() {
    return effect;
  }

  private void setParticleEffect(ParticleEffect effect) {
    this.effect = effect;
  }

  /**
   * Sets the lifespan of this particle. Will stay the same, and mainly functions
   * as the 'max lifespan'. This will also set the currentLifespan.
   * @param lifespan The new maximum lifespan.
   */
  public void setLifespan(int lifespan) {
    this.lifespan = lifespan;
    this.setCurrentLifespan(lifespan);
  }

  /**
   * Gets the max lifespan of this particle effect.
   * @return The length in MS.
   */
  public int getLifespan() {
    return lifespan;
  }

  public double getRotation() {
    return rotation;
  }

  /**
   * Sets the rotation (0..360) of this particle. Effective rotation is <code>
   * rotation % 360 </code>.
   * @param rotation The new rotation.
   */
  public void setRotation(double rotation) {
    this.rotation = rotation % 360;
  }

  /**
   * Gets the rotationspeed of this particle.
   * @return The speed, in angle per update.
   */
  public double getRotationSpeed() {
    return rotationSpeed;
  }

  /**
   * Sets the speed at which to rotate every update.
   * @param rotationSpeed The speed.
   */
  public void setRotationSpeed(double rotationSpeed) {
    this.rotationSpeed = rotationSpeed;
  }

  public ParticleTextureCollection getTexture() {
    return texture;
  }

  public void setTexture(ParticleTextureCollection texture) {
    this.texture = texture;

    Image image = this.getTexture().getImage(ZoomStatic.CURRENT_ZOOM_LEVEL);
    this.setHypotenuse((int) Math.ceil(Math.sqrt(Math.pow(image.getWidth(null), 2) + Math.pow(image.getHeight(null), 2))));
  }

  public double getXSpeed() {
    return xSpeed;
  }

  public void setXSpeed(double xSpeed) {
    this.xSpeed = xSpeed;
  }

  public double getYSpeed() {
    return ySpeed;
  }

  public void setYSpeed(double ySpeed) {
    this.ySpeed = ySpeed;
  }

  /**
   * Gets the draw pixel location of this particle.
   * @return The Point of the location on the screen, where it should be drawn.
   */
  public Point getDrawPixelLocation() {
    Point offset = EngineWindow.getInstance().getGame().getPlayfieldGrid().getDrawOffset();
    return new Point((int) ((this.getPixelLocation().x) + offset.x) - (PlayfieldSquare.WIDTH / 2),
            (int) ((this.getPixelLocation().y) + offset.y) - (PlayfieldSquare.HEIGHT / 2));
  }
  // </editor-fold>

  /**
   * Updates the basic effect. Don't call this function, it will be done for you
   * in the ParticleEngine. Use doAdditionalUpdates(); instead.
   */
  public void updateEffect() {
    this.setPixelLocation(
            new DoublePoint(
            this.getPixelLocation().x + (this.getXSpeed() * ParticleEffect.X_SPEED_MODIFIER),
            this.getPixelLocation().y + (this.getYSpeed() * ParticleEffect.Y_SPEED_MODIFIER)));
    this.setRotation(this.getRotation() + this.getRotationSpeed());
  }

  /**
   * Updates the effect of the particle, perform rotation and movement in here
   */
  public abstract void doAdditionalUpdates();

  @Override
  public void draw(Graphics g) {
    Point location = this.getDrawPixelLocation();
    Image image = this.getTexture().getImage(ZoomStatic.CURRENT_ZOOM_LEVEL);
    // If we should draw this particle ..
    if (!EngineWindow.getInstance().getCanvas().getViewPort().contains(location)
            && !EngineWindow.getInstance().getCanvas().getViewPort().contains(
            new Point(location.x + image.getWidth(null), location.y + image.getHeight(null)))) return;
    // Yes, do translucency
    Graphics2D g2 = (Graphics2D) g;


    Composite old = g2.getComposite();
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) this.getOpacity() / 100));
    // End translucency




    // Start scaling
    if (this.getScale() != 1.0 && Math.abs(this.getScale() - this.getPreviousScale()) > 0.2) {
      image = image.getScaledInstance((int) (image.getWidth(null) * this.getScale()),
              (int) (image.getHeight(null) * this.getScale()), Image.SCALE_SMOOTH);
      this.setHypotenuse((int) Math.ceil(Math.sqrt(Math.pow(image.getWidth(null), 2)
              + Math.pow(image.getHeight(null), 2))));
      this.setPreviousScale(this.getScale());
    }
    // End scaling


    // Start rotation, and check if we should, as the computation is heavy
    if (this.getRotation() == 0) {
      g2.drawImage(image, location.x, location.y, null);
      g2.setComposite(old);
      return;
    }

    // Yes, do it
    g2 = (Graphics2D) g2.create(location.x, location.y, this.getHypotenuse(), this.getHypotenuse());

    AffineTransform origXform = g2.getTransform();
    AffineTransform newXform = (AffineTransform) (origXform.clone());
    double halfHypo = this.getHypotenuse() / 2.0;
    //center of rotation is center of the panel
    newXform.rotate(Math.toRadians(this.getRotation()), halfHypo, halfHypo);
    g2.setTransform(newXform);
    //draw image centered in panel    

    g2.drawImage(image, 0, 0, null);

    g2.setTransform(origXform);
    ((Graphics2D) g).setComposite(old);
  }

  @Override
  public void onZoomChange(ZoomChangeEvent event) {
    this.setPixelLocation(new DoublePoint(this.getPixelLocation().x * event.getxFactor(),
            this.getPixelLocation().y * event.getyFactor()));

    Image image = this.getTexture().getImage(event.getNewZoom());
    this.setHypotenuse((int) Math.ceil(Math.sqrt(Math.pow(image.getWidth(null), 2) + Math.pow(image.getHeight(null), 2))));
  }

  public AbstractParticle(ParticleTextureCollection texture, ParticleEffect effect) {
    this.setTexture(texture);
    this.setParticleEffect(effect);
    this.setPixelLocation(effect.getPixelLocation());
    effect.getParticleList().add(this);
  }
}
