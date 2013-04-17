/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.tree;

import model.core.Drawable;
import java.awt.Graphics;

/**
 *
 * @author Wouter
 */
public class TreeRoot extends Drawable {

  private TreeNode[] children;

  public TreeNode[] getChildren() {
    return children;
  }

  public void setChildren(TreeNode[] children) {
    this.children = children;
  }

  public TreeRoot(TreeNode[] children) {
    this.setChildren(children);
  }

  @Override
  public String toString() {
    String result = "TreeRoot: {\n";
    for (int i = 0; i < this.getChildren().length; i++) {
      result += this.getChildren()[i];
    }
    return result + "}\n";
  }

  @Override
  public void draw(Graphics g) {
    for (int i = 0; i < this.getChildren().length; i++) {
      this.getChildren()[i].draw(g);
    }
  }
}
