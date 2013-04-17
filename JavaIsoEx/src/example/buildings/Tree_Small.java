/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example.buildings;

import model.core.buildings.Building;
import model.core.Square;
import model.core.events.SelectEvent;
import model.core.texture.BuildingTextureCollection;
import model.core.texture.TextureManager;

/**
 *
 * @author Wouter
 */
public class Tree_Small extends Building {

  public Tree_Small(Square square) {
    super(square, (BuildingTextureCollection) TextureManager.getInstance().getCollection("Tree_Small"), 1, 1);
    this.setCanSelect(false);
  }

  @Override
  public void onSelect(SelectEvent event) {
    // throw new UnsupportedOperationException("Not supported yet.");
  }
}
