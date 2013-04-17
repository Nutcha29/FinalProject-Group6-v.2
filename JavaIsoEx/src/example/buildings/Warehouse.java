/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example.buildings;

import model.core.Square;
import model.core.buildings.Building;
import model.core.events.SelectEvent;
import model.core.texture.BuildingTextureCollection;
import model.core.texture.TextureManager;

/**
 *
 * @author Wouter
 */
public class Warehouse extends Building {

  public static Warehouse PREVIEW_WAREHOUSE = new Warehouse(null);

  public Warehouse(Square square) {
    super(square, (BuildingTextureCollection) TextureManager.getInstance().getCollection("Warehouse"), 3, 3);
  }

  @Override
  public void onSelect(SelectEvent event) {
    // throw new UnsupportedOperationException("Not supported yet.");
  }
}
