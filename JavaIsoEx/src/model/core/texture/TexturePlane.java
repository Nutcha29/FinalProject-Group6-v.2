/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.texture;

import model.core.Drawable;
import model.core.PlayfieldSquare;
import model.core.Square;
import java.awt.Graphics;
import variables.ZoomStatic;

/**
 *
 * @author Wouter
 */
public class TexturePlane extends Drawable {

  private String name;
  private Square square;

  /**
   * Gets the name of this TexturePlane.
   * @return The name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this TexturePlane.
   * @param name The new name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the TextureCollection of this plane.
   * @return The collection.
   */
  public TextureCollection getTextureCollection() {
    return TextureManager.getInstance().getTextureCollection(name);
  }

  /**
   * Gets the Square of this TexturePlane.
   * @return The square.
   */
  public Square getSquare() {
    return square;
  }

  /**
   * Sets the Square of this TexturePlane.
   * @param square The new Square.
   */
  public void setSquare(Square square) {
    this.square = square;
  }

  public TexturePlane clone() {
    return new TexturePlane(this.getName(), this.getSquare());
  }

  public TexturePlane(String name, Square square){
    this.setSquare(square);
    this.setName(name);
  }

  /*public TexturePlane(String name, TextureCollection textureCollection, Square square) {
    this.setSquare(square);
    this.setTextureCollection(textureCollection);
    this.setName(name);
  }*/

  @Override
  public void draw(Graphics g) {
    g.drawImage(
            this.getTextureCollection().getImage( ZoomStatic.CURRENT_ZOOM_LEVEL ),
            this.getSquare().getDrawPixelX() - PlayfieldSquare.WIDTH / 2,
            this.getSquare().getDrawPixelY(), null);
  }
}
