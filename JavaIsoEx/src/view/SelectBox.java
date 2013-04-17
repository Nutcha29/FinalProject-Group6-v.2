/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Rectangle;

/**
 *
 * @author Wouter
 */
public class SelectBox extends Rectangle {

  /**
   * Returns the rectangle that does NOT have negative widths or heights, and adjusts
   * the points etc etc accordingly.
   * @param rect The old rect.
   * @return The new rect.
   */
  public SelectBox eliminateNegatives() {
    int w = this.width, h = this.height;
    if (w < 0 && h > 0) {
      return new SelectBox(x + w, y, w * -1, h);
    } else if (w > 0 && h < 0) {
      return new SelectBox(x, y + h, w, h * -1);
    } else if (w < 0 && h < 0) {
      return new SelectBox(x + w, y + h, w * -1, h * -1);
    }
    return this;
  }

  public SelectBox(int x, int y, int w, int h){
    this.setBounds(x, y, w, h);
  }
}
