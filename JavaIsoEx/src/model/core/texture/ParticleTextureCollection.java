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
public class ParticleTextureCollection extends TextureCollection {
  public ParticleTextureCollection(String textureName, Image image) {
    this.setTextureName(textureName);

    Image[] images = new Image[3];
    images[2] = image.getScaledInstance(
            (int) (image.getWidth(null) * 1),
            (int) (image.getHeight(null) * 1),
            Image.SCALE_SMOOTH);
    images[1] = image.getScaledInstance(
            (int) (image.getWidth(null) * (ZoomStatic.ZOOM_MEDIUM_WIDTH / (double)ZoomStatic.ZOOM_LARGE_WIDTH)),
            (int) (image.getHeight(null) * (ZoomStatic.ZOOM_MEDIUM_HEIGHT / (double)ZoomStatic.ZOOM_LARGE_HEIGHT)),
            Image.SCALE_SMOOTH);
    images[0] = image.getScaledInstance(
            (int) (image.getWidth(null) * (ZoomStatic.ZOOM_SMALL_WIDTH / (double)ZoomStatic.ZOOM_LARGE_WIDTH)),
            (int) (image.getHeight(null) * (ZoomStatic.ZOOM_SMALL_HEIGHT / (double)ZoomStatic.ZOOM_LARGE_HEIGHT)),
            Image.SCALE_SMOOTH);
    this.setImage(images);
    // System.out.println(images[0].getWidth(null) + " --- " + images[0].getHeight(null));
  }
}
