/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.texture;

import java.awt.Image;
import variables.ZoomStatic;

/**
 *
 * @author Wouter
 */
public class MoveableTextureCollection extends AbstractCollection {

  private Image[][] images = new Image[4][3];
  
  /**
   * Gets the image at a specific zoom level
   * @param zoomLevel The zoom level.
   * @return The images, or null if zoomlevel is invalid.
   * @see ZoomStatic.ZOOM_SMALL, ZoomStatic.ZOOM_MEDIUM, ZoomStatic.ZOOM_LARGE
   */
  public Image[] getImageSet(int zoomLevel) {
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

  /**
   * Gets the image with current zoomLevel, and with given moveState.
   * @param zoomLevel The zoom level, as found in ZoomStatic.xxx
   * @param moveState The move state, as found in Moveable.xxx
   * @return The proper image, or null if you screwed up somewhere.
   */
  public Image getImage(int zoomLevel, int moveState){
    switch (zoomLevel) {
      case ZoomStatic.ZOOM_SMALL:
        return images[moveState][0];
      case ZoomStatic.ZOOM_MEDIUM:
        return images[moveState][1];
      case ZoomStatic.ZOOM_LARGE:
        return images[moveState][2];
      default:
        return null;
    }
  }

  protected void setImage(Image[][] images) {
    this.images = images;
  }

  /**
   * Creates a MoveableTextureCollection of the given image array. 
   * SUPPLY ONLY THE LARGEST SIZES OF THE 4 IMAGES OF THE MOVEABLE OBJECT.
   *
   * THE REST IS RESIZED AUTOMATICALLY.
   *
   * RIGHT_TOP = 0, RIGHT_BOTTOM = 1, LEFT_TOP = 2, LEFT_BOTTOM = 3; // In the array
   * @param textureName The name of this moveable object.
   * @param image The image.
   */
  public MoveableTextureCollection(String textureName, Image[] image) {
    this.setTextureName(textureName);
    for( int i = 0; i < image.length; i++ ){
      Image[] scaledImage = new Image[3];
      scaledImage[2] = image[i].getScaledInstance(
              (int) (image[i].getWidth(null) * 1),
              (int) (image[i].getHeight(null) * 1),
              Image.SCALE_SMOOTH);
      scaledImage[1] = image[i].getScaledInstance(
              (int) (image[i].getWidth(null) * (ZoomStatic.ZOOM_MEDIUM_WIDTH / (double)ZoomStatic.ZOOM_LARGE_WIDTH)),
              (int) (image[i].getHeight(null) * (ZoomStatic.ZOOM_MEDIUM_HEIGHT / (double)ZoomStatic.ZOOM_LARGE_HEIGHT)),
              Image.SCALE_SMOOTH);
      scaledImage[0] = image[i].getScaledInstance(
              (int) (image[i].getWidth(null) * (ZoomStatic.ZOOM_SMALL_WIDTH / (double)ZoomStatic.ZOOM_LARGE_WIDTH)),
              (int) (image[i].getHeight(null) * (ZoomStatic.ZOOM_SMALL_HEIGHT / (double)ZoomStatic.ZOOM_LARGE_HEIGHT)),
              Image.SCALE_SMOOTH);
      images[i] = scaledImage;
    }
  }
}
