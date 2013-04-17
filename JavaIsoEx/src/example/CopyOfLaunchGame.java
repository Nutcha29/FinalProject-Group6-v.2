/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import controller.game.Game;
import example.buildings.Tent;
import example.buildings.Tree_Small;
import example.units.Truck;
import java.awt.Image;
import model.core.PlayfieldGrid;
import model.core.texture.BuildingTextureCollection;
import model.core.texture.TextureCollection;
import model.core.texture.TextureManager;
import variables.GeneralStatic;
import variables.ZoomStatic;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.core.DoublePoint;
import model.core.Square;
import model.core.texture.MoveableTextureCollection;
import model.core.texture.TexturePlane;

/**
 *
 * @author Wouter
 */
public class CopyOfLaunchGame extends Game {

  @Override
  public void onStart() {
    this.setPlayfieldGrid(new PlayfieldGrid(100, 100));
    GeneralStatic.setGameState(GeneralStatic.STATE_RUNNING);
    this.getPlayfieldGrid().setDrawTree(false);
    this.getPlayfieldGrid().setDrawGrid(true);
    this.getPlayfieldGrid().setCurrentZoomLevel(ZoomStatic.ZOOM_LARGE);

    String sep = File.separator, imagesFolder = ".." + sep + "images" + sep;

    // <editor-fold defaultstate="collapsed" desc="Init textures and buildings">
    File test_texture = null, test_texture_2 = null, test_pyramid = null, test_tent = null, tree_small = null;
    File road_straight = null, road_straight_mirror = null, road_corner = null,
            road_corner_flip = null, road_corner_mirror = null, road_corner_flip_mirror = null,
            road_crossroad = null, road_t_split = null, road_t_split_flip = null, road_t_split_mirror = null,
            road_t_split_flip_mirror = null;
    TextureManager manager = TextureManager.getInstance();

    try {
      String path = imagesFolder + "textures" + sep;
      System.out.println(CopyOfLaunchGame.class.getResource(path + "bare2.png").toURI());
      test_texture = new File(CopyOfLaunchGame.class.getResource(path + "bare2.png").toURI());
      test_texture_2 = new File(CopyOfLaunchGame.class.getResource(path + "temperate.png").toURI());

      path = imagesFolder + "objects" + sep;
      test_pyramid = new File(CopyOfLaunchGame.class.getResource(path + "test_object.png").toURI());
      test_tent = new File(CopyOfLaunchGame.class.getResource(path + "test_object2.png").toURI());
      tree_small = new File(CopyOfLaunchGame.class.getResource(path + "tree_1_small.png").toURI());



      // <editor-fold defaultstate="collapsed" desc="Load road textures">
      path = ".." + sep + "images" + sep + "textures" + sep + "roads" + sep;
      road_straight = new File(CopyOfLaunchGame.class.getResource(path + "road_straight.png").toURI());
      road_straight_mirror = new File(CopyOfLaunchGame.class.getResource(path + "road_straight_mirror.png").toURI());
      road_corner = new File(CopyOfLaunchGame.class.getResource(path + "road_corner.png").toURI());
      road_corner_flip = new File(CopyOfLaunchGame.class.getResource(path + "road_corner_flip.png").toURI());
      road_corner_mirror = new File(CopyOfLaunchGame.class.getResource(path + "road_corner_mirror.png").toURI());
      road_corner_flip_mirror = new File(CopyOfLaunchGame.class.getResource(path + "road_corner_flip_mirror.png").toURI());

      road_crossroad = new File(CopyOfLaunchGame.class.getResource(path + "road_crossroad.png").toURI());
      road_t_split = new File(CopyOfLaunchGame.class.getResource(path + "road_t_split.png").toURI());
      road_t_split_flip = new File(CopyOfLaunchGame.class.getResource(path + "road_t_split_flip.png").toURI());
      road_t_split_mirror = new File(CopyOfLaunchGame.class.getResource(path + "road_t_split_mirror.png").toURI());
      road_t_split_flip_mirror = new File(CopyOfLaunchGame.class.getResource(path + "road_t_split_flip_mirror.png").toURI());

      // </editor-fold>
      manager.addCollection(new TextureCollection("Test", ImageIO.read(test_texture)));
      manager.addCollection(new TextureCollection("Test_2", ImageIO.read(test_texture_2)));
      manager.addCollection(new BuildingTextureCollection("Pyramid", ImageIO.read(test_pyramid)));
      manager.addCollection(new BuildingTextureCollection("Tent", ImageIO.read(test_tent)));
      manager.addCollection(new BuildingTextureCollection("Tree_Small", ImageIO.read(tree_small)));

      manager.addCollection(new TextureCollection("road_straight", ImageIO.read(road_straight)));
      manager.addCollection(new TextureCollection("road_straight_mirror", ImageIO.read(road_straight_mirror)));
      manager.addCollection(new TextureCollection("road_corner", ImageIO.read(road_corner)));
      manager.addCollection(new TextureCollection("road_corner_flip", ImageIO.read(road_corner_flip)));
      manager.addCollection(new TextureCollection("road_corner_mirror", ImageIO.read(road_corner_mirror)));
      manager.addCollection(new TextureCollection("road_corner_flip_mirror", ImageIO.read(road_corner_flip_mirror)));

      manager.addCollection(new TextureCollection("road_crossroad", ImageIO.read(road_crossroad)));
      manager.addCollection(new TextureCollection("road_t_split", ImageIO.read(road_t_split)));
      manager.addCollection(new TextureCollection("road_t_split_flip", ImageIO.read(road_t_split_flip)));
      manager.addCollection(new TextureCollection("road_t_split_mirror", ImageIO.read(road_t_split_mirror)));
      manager.addCollection(new TextureCollection("road_t_split_flip_mirror", ImageIO.read(road_t_split_flip_mirror)));

    } catch (URISyntaxException ex) {
      Logger.getLogger(CopyOfLaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(CopyOfLaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    }

    
    for (int i = 0; i < this.getPlayfieldGrid().getGridSizeX(); i++) {
      for (int j = 0; j < this.getPlayfieldGrid().getGridSizeY(); j++) {
        Square s = this.getPlayfieldGrid().getSquares()[i][j];
        if (j % 2 == 0) {
          s.setTexture(new TexturePlane("Test", s));
        } else {
          s.setTexture(new TexturePlane("Test_2", s));
        }
        /*
        if (i % 25 == 0 && j % 25 == 0) {
          s.setBuilding(new Tree_Small(s));
        } else if ((i + 1) % 25 == 0 && (j + 1) % 25 == 0) {
          s.setBuilding(new Tent(s));
        }
        */
      }
    }
     
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Init textures of units">
    Image[] images = new Image[4];
    try {
      images[0] = ImageIO.read(new File(CopyOfLaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "truck_right_top.png").toURI()));
      images[1] = ImageIO.read(new File(CopyOfLaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "truck_right_bottom.png").toURI()));
      images[2] = ImageIO.read(new File(CopyOfLaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "truck_left_top.png").toURI()));
      images[3] = ImageIO.read(new File(CopyOfLaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "truck_left_bottom.png").toURI()));
    } catch (URISyntaxException ex) {
      Logger.getLogger(CopyOfLaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(CopyOfLaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    }
    MoveableTextureCollection collection = new MoveableTextureCollection("Truck", images);
    manager.addCollection(collection);
    // </editor-fold>
    
    Point p = this.getPlayfieldGrid().translateGridToPixel(3, 3);
    DoublePoint dp = DoublePoint.fromPoint(p);
    Truck truck = new Truck(dp, 1, collection);
    // truck.moveTo(this.getPlayfieldGrid().getSquares()[0][15]);

    p = this.getPlayfieldGrid().translateGridToPixel(5, 5);
    dp = DoublePoint.fromPoint(p);
    Truck truck2 = new Truck(dp, 1, collection);
    // truck2.moveTo(null);
     
  }

  @Override
  public void onDraw() {
    // throw new UnsupportedOperationException("Not supported yet.");
  }

  public CopyOfLaunchGame(UpdateGame loop) {
    super(loop);
  }
}
