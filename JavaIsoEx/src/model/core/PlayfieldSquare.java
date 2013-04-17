/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core;

import view.EngineWindow;
import java.awt.Graphics;
import model.core.pathfinding.AStarInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Wouter
 */
public class PlayfieldSquare extends Square {

  public static int WIDTH = 0, HEIGHT = 0;

  @Override
  public void draw(Graphics g) {
    this.setDrawn(false);
    
    if (!doDraw()) return;

    if (this.getTexture() != null) {
      this.getTexture().draw(g);
    }

    EngineWindow.getInstance().getGame().drawnSquare();
    this.setDrawn(true);

    if (this.getDrawTriangles()) {
      for (int i = 0; i < this.getTriangles().length; i++) {
        this.getTriangles()[i].draw(g);
      }
    }
  }

  public Node toXML(Document document) {
    Element node = document.createElement("Square");

    node.setAttribute("x", this.getGridX() + "");
    node.setAttribute("y", this.getGridY() + "");

    node.setAttribute("texture", this.getTexture().getName());



    return node;
  }

  public PlayfieldSquare(PlayfieldGrid grid, int gridX, int gridY) {
    this.setGrid(grid);
    this.setGridX(gridX);
    this.setGridY(gridY);

    this.setPathingInfo(new AStarInfo(this));

    this.calculatePoints();
  }
}
