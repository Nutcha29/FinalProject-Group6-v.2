/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core;

import model.core.tree.TreeNode;
import model.core.tree.TreeNode.SquarePoint;
import java.awt.Point;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Wouter
 */
public class PlayfieldGrid extends Grid {

  /**
   * Translates the given grid location to a pixel location
   * @param gridX The grid X.
   * @param gridY The grid Y.
   * @return The Point containing the pixel X and Y of the screen.
   */
  public Point translateGridToPixel(int gridX, int gridY) {
    // Calculate X
    // double squareDiagonal = PlayfieldSquare.diagonal;
    double gridPixelWidth = Math.round(PlayfieldSquare.WIDTH * this.getGridSizeX());

    double pixelX = gridPixelWidth / 2;
    pixelX += (gridX * (PlayfieldSquare.WIDTH / 2));
    pixelX -= (gridY * (PlayfieldSquare.WIDTH / 2));

    // Calculate Y
    double pixelY = 0;
    pixelY += (gridX * (PlayfieldSquare.HEIGHT / 2));
    pixelY += (gridY * (PlayfieldSquare.HEIGHT / 2));


    return new Point((int) pixelX, (int) pixelY);
  }

  public Square translatePixelToSquare(Point pixelLocation) {
    Point p = translatePixelToGrid(pixelLocation.x, pixelLocation.y);
    if (p == null) return null;
    else return this.getSquares()[p.x][p.y];
  }

  public Point translatePixelToGrid(int pixelX, int pixelY) {
    if (this.getTree() == null) {
      return null;
    }
    Square s = findSquareInTree(this.getTree().getTreeRoot().getChildren(), new Point(pixelX, pixelY));
    if (s != null) {
      return new Point(s.getGridX(), s.getGridY());
    } else {
      return null;
    }
  }

  private Square findSquareInTree(TreeNode[] currentChildren, Point p) {
    for (int i = 0; i < currentChildren.length; i++) {
      TreeNode node = currentChildren[i];
      Triangle t = node.getTriangle();
      if (t.contains(p)) {
        if (node.getChildren() == null) {
          ArrayList<SquarePoint> list = node.getUnderlyingSquares();
          for (int j = 0; j < list.size(); j++) {
            SquarePoint sp = list.get(j);
            for (int k = 0; k < sp.getSquare().getTriangles().length; k++) {
              if (sp.getSquare().getTriangles()[k].contains(p)) {
                return sp.getSquare();
              }
            }
          }
        } else {
          // System.out.println("Traversing farther");
          return findSquareInTree(node.getChildren(), p);
        }
      }
    }
    return null;
  }

  @Override
  public void initSquares() {
    this.setSquares(new Square[this.getGridSizeX()][this.getGridSizeY()]);
    for (int i = 0; i < this.getGridSizeX(); i++) {
      for (int j = 0; j < this.getGridSizeY(); j++) {
        this.getSquares()[i][j] = new PlayfieldSquare(this, i, j);
      }
    }
  }

  public Node toXML(Document document) {
    Node grid = document.createElement("PlayfieldGrid");
    for (int i = 0; i < this.getGridSizeX(); i++) {
      for (int j = 0; j < this.getGridSizeY(); j++) {
        grid.appendChild(((PlayfieldSquare) this.getSquares()[i][j]).toXML(document));
      }
    }
    return grid;
  }

  public PlayfieldGrid(int sizeX, int sizeY) {
    this.setGridSizeX(sizeX);
    this.setGridSizeY(sizeY);

    initSquares();
  }
}
