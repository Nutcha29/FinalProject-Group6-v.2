/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;

/**
 *
 * @author Wouter
 */
public class Background {

  public static final int BACKGROUND_FILL = 1, BACKGROUND_REPEAT = 2,
          BACKGROUND_NO_REPEAT = 3, BACKGROUND_REPEAT_X = 4, BACKGROUND_REPEAT_Y = 5;
  private Image image;
  private int mode = 0;

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public int getMode() {
    return mode;
  }

  public void setMode(int mode) {
    this.mode = mode;
  }

  public Background(Image image, int mode) {
    this.setImage(image);
    this.setMode(mode);
  }
}
