/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.tree;

import model.core.Drawable;
import model.core.Grid;
import model.core.Square;
import model.core.Triangle;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Wouter
 */
public class TriangleTree extends Drawable {

  /**
   * Max depth the tree can be, used for constructing the tree
   */
  public static int MAX_DEPTH = 6;
  private Grid grid;
  private TreeRoot treeRoot;
  private ArrayList<TreeNode> leafList = new ArrayList<TreeNode>();

  /**
   * Gets the list containing all leaf TreeNodes (TreeNodes without children).
   * @return The list.
   */
  public ArrayList<TreeNode> getLeafList() {
    return leafList;
  }

  public TreeRoot getTreeRoot() {
    return treeRoot;
  }

  public void setTreeRoot(TreeRoot treeRoot) {
    this.treeRoot = treeRoot;
  }

  public Grid getGrid() {
    return grid;
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  public TriangleTree(Grid grid, TreeRoot root) {
    this.setGrid(grid);
    this.setTreeRoot(root);
  }

  /**
   * Builds the tree starting from the currentChildren
   * (This should start with the TreeRoot's childs
   * @param currentChildren The nodes to start building from.
   */
  public void construct(TreeNode[] currentChildren) {
    for (int i = 0; i < currentChildren.length; i++) {
      TreeNode node = currentChildren[i];
      if (node.getDepth() == TriangleTree.MAX_DEPTH) {
        for (int j = 0; j < currentChildren.length; j++) {
          this.getLeafList().add(currentChildren[j]);
        }
        return;
      } else {
        construct(node.split());
      }
    }
  }

  /**
   * Once the tree has been constructed, we need to allocate squares
   * to TreeNodes. This function will sort that for you.
   */
  public void fillTree() {
    for (int i = 0; i < this.getGrid().getGridSizeX(); i++) {
      for (int j = 0; j < this.getGrid().getGridSizeY(); j++) {
        Square square = this.getGrid().getSquares()[i][j];
        TreeNode[] currentChildren = this.getTreeRoot().getChildren();
        for (int k = 0; k < square.getCornerPoints().length; k++) {
          placeInTree(currentChildren, square, square.getCornerPoints()[k]);
        }
      }
    }
  }

  /**
   * Place the point of the square in the tree
   * @param currentChildren The children to start at (Root's children)
   * @param square The square to assign
   * @return Whether or not the operation was successful. Mostly internal usage
   * as this is a recursive function.
   */
  public boolean placeInTree(TreeNode[] currentChildren, Square square, Point p) {
    for (int i = 0; i < currentChildren.length; i++) {
      TreeNode node = currentChildren[i];
      Triangle t = node.getTriangle();
      if (t.contains(p)) {
        if (node.getChildren() == null) {
          node.addSquare(square, p);
          // System.out.println("Placing square " + square.toString() + " with point " + p.toString() +
          //        " in " + node.toString());
          return true;
        } else {
          placeInTree(node.getChildren(), square, p);
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Draws the complete tree.
   * @param g The Graphics to draw on.
   */
  public void draw(Graphics g) {
    // this.draw(g);
    for (int i = 0; i < this.getTreeRoot().getChildren().length; i++) {
      this.getTreeRoot().getChildren()[i].drawWithChildren(g);
    }
  }
}
