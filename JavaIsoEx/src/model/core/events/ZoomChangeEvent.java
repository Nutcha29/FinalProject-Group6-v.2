/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.events;

import variables.ZoomStatic;

/**
 *
 * @author Wouter
 */
public class ZoomChangeEvent {

  private int previousZoom, newZoom;
  private double xFactor, yFactor;

  public int getNewZoom() {
    return newZoom;
  }

  public void setNewZoom(int newZoom) {
    this.newZoom = newZoom;
  }

  public int getPreviousZoom() {
    return previousZoom;
  }

  public void setPreviousZoom(int previousZoom) {
    this.previousZoom = previousZoom;
  }

  /**
   * Gets the factor at which the X has changed.
   * @return The factor.
   */
  public double getxFactor() {
    return xFactor;
  }

  /**
   * Sets the factor at which the X has changed.
   * @param xFactor The new factor.
   */
  public void setxFactor(double xFactor) {
    this.xFactor = xFactor;
  }

  /**
   * Gets the factor at which the Y has changed.
   * @return The factor.
   */
  public double getyFactor() {
    return yFactor;
  }

  /**
   * Sets the factor at which the Y has changed.
   * @param yFactor The new factor.
   */
  public void setyFactor(double yFactor) {
    this.yFactor = yFactor;
  }

  public ZoomChangeEvent(int previousZoom, int newZoom) {
    this.setPreviousZoom(previousZoom);
    this.setNewZoom(newZoom);
    switch (previousZoom) {
      case ZoomStatic.ZOOM_LARGE: {
        // New zoom is always medium, can only go smaller
        this.setxFactor(ZoomStatic.ZOOM_MEDIUM_WIDTH / (double) ZoomStatic.ZOOM_LARGE_WIDTH);
        this.setyFactor(ZoomStatic.ZOOM_MEDIUM_HEIGHT / (double) ZoomStatic.ZOOM_LARGE_HEIGHT);
        break;
      }
      case ZoomStatic.ZOOM_MEDIUM: {
        if (newZoom == ZoomStatic.ZOOM_LARGE) {
          this.setxFactor(ZoomStatic.ZOOM_LARGE_WIDTH / (double) ZoomStatic.ZOOM_MEDIUM_WIDTH);
          this.setyFactor(ZoomStatic.ZOOM_LARGE_HEIGHT / (double) ZoomStatic.ZOOM_MEDIUM_HEIGHT);
        } else if( newZoom == ZoomStatic.ZOOM_SMALL ){
          this.setxFactor(ZoomStatic.ZOOM_SMALL_WIDTH / (double) ZoomStatic.ZOOM_MEDIUM_WIDTH);
          this.setyFactor(ZoomStatic.ZOOM_SMALL_HEIGHT / (double) ZoomStatic.ZOOM_MEDIUM_HEIGHT);
        }
        break;
      }
      case ZoomStatic.ZOOM_SMALL: {
        // New zoom is always medium, can only go smaller
        this.setxFactor(ZoomStatic.ZOOM_MEDIUM_WIDTH / (double) ZoomStatic.ZOOM_SMALL_WIDTH);
        this.setyFactor(ZoomStatic.ZOOM_MEDIUM_HEIGHT / (double) ZoomStatic.ZOOM_SMALL_HEIGHT);
        break;
      }
    }
  }
}
