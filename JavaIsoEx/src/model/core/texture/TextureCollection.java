/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.texture;

import variables.ZoomStatic;
import java.awt.Image;

/**
 *
 * @author Wouter
 */
public class TextureCollection extends AbstractCollection {
  private Image[] images = new Image[3];

  protected void setImage(Image[] images) {
    this.images = images;
  }

  /**
   * Gets the image at a specific zoom level
   * @param zoomLevel The zoom level.
   * @return The image, or null if zoomlevel is invalid.
   * @see ZoomStatic.ZOOM_SMALL, ZoomStatic.ZOOM_MEDIUM, ZoomStatic.ZOOM_LARGE
   */
  public Image getImage(int zoomLevel) {
    switch (zoomLevel) {
      case ZoomStatic.ZOOM_SMALL:
        return images[0];
      case ZoomStatic.ZOOM_MEDIUM:
        return images[1];
      case ZoomStatic.ZOOM_LARGE:
        return images[2];
      default:
        return null;
    }
  }

  protected TextureCollection() {
  }

  /**
   * Creates a TextureCollection of the given image. ONLY SUPPLY THE LARGEST SIZE,
   * THE REST IS RESIZED AUTOMATICALLY.
   * @param image The image.
   */
  public TextureCollection(String textureName, Image image) {
    this.setTextureName(textureName);
    images[2] = image.getScaledInstance(ZoomStatic.ZOOM_LARGE_WIDTH + 2, ZoomStatic.ZOOM_LARGE_HEIGHT + 2, Image.SCALE_SMOOTH);
    images[1] = image.getScaledInstance(ZoomStatic.ZOOM_MEDIUM_WIDTH + 2, ZoomStatic.ZOOM_MEDIUM_HEIGHT + 2, Image.SCALE_SMOOTH);
    images[0] = image.getScaledInstance(ZoomStatic.ZOOM_SMALL_WIDTH + 2, ZoomStatic.ZOOM_SMALL_HEIGHT + 2, Image.SCALE_SMOOTH);
  }
}