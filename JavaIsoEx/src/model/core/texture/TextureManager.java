/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.texture;

import controller.game.Game;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Wouter
 */
public class TextureManager {

  private String sep = File.separator;

  private LinkedList<AbstractCollection> collections = new LinkedList<AbstractCollection>();
  private static TextureManager instance = null;

  /**
   * Adds a collection to the texture manager.
   * @param collection The collection to add.
   */
  public void addCollection(AbstractCollection collection) {
    for (AbstractCollection c : collections) {
      if (c.getTextureName().equals(collection.getTextureName())) {
        System.err.println("Cannot add " + collection.getTextureName());
        return;
      }
    }
    collections.add(collection);
  }

  /**
   * Attempts to get the collection that is an ParticleTextureCollection with the given name.
   * @param textureName The name.
   * @return The ParticleTextureCollection corresponding to the name, or null if the
   * name was not found, or the name found was not an instance of ParticleTextureCollection
   */
  public ParticleTextureCollection getParticleTextureCollection(String textureName) {
    for (AbstractCollection c : collections) {
      if (c.getTextureName().equals(textureName)
              && c instanceof ParticleTextureCollection) {
        return (ParticleTextureCollection) c;
      }
    }
    System.err.println("Cannot find " + textureName);
    return null;
  }

  /**
   * Attempts to get the collection that is an MoveableTextureCollection with the given name.
   * @param textureName The name.
   * @return The MoveableTextureCollection corresponding to the name, or null if the
   * name was not found, or the name found was not an instance of MoveableTextureCollection
   */
  public MoveableTextureCollection getMoveableTextureCollection(String textureName) {
    for (AbstractCollection c : collections) {
      if (c.getTextureName().equals(textureName)
              && c instanceof MoveableTextureCollection) {
        return (MoveableTextureCollection) c;
      }
    }
    System.err.println("Cannot find " + textureName);
    return null;
  }

  /**
   * Attempts to get the collection that is an BuildingTextureCollection with the given name.
   * @param textureName The name.
   * @return The BuildingTextureCollection corresponding to the name, or null if the
   * name was not found, or the name found was not an instance of BuildingTextureCollection
   */
  public BuildingTextureCollection getBuildingTextureCollection(String textureName) {
    for (AbstractCollection c : collections) {
      if (c.getTextureName().equals(textureName)
              && c instanceof BuildingTextureCollection) {
        return (BuildingTextureCollection) c;
      }
    }
    System.err.println("Cannot find " + textureName);
    return null;
  }

  /**
   * Attempts to get the collection that is an TextureCollection with the given name.
   * @param textureName The name.
   * @return The TextureCollection corresponding to the name, or null if the
   * name was not found, or the name found was not an instance of TextureCollection
   */
  public TextureCollection getTextureCollection(String textureName) {
    for (AbstractCollection c : collections) {
      if (c.getTextureName().equals(textureName)
              && c instanceof TextureCollection) {
        return (TextureCollection) c;
      }
    }
    System.err.println("Cannot find " + textureName);
    return null;
  }

  /**
   * Attempts to get the collection with the given name.
   * @param textureName The name.
   * @return The Collection corresponding to the name, or null if the name was not
   * found.
   */
  public AbstractCollection getCollection(String textureName) {
    for (AbstractCollection c : collections) {
      if (c.getTextureName().equals(textureName)) {
        return c;
      }
    }
    System.err.println("Cannot find " + textureName);
    return null;
  }

  /**
   * Loads an image, creates a texture collection, and adds it to the list.
   * @param path The path to the image (including the file, ex: C:\path\to\file\test.png
   * @param textureName The name to be assigned to it.
   * @return Whether the operation was successful.
   * @throws IOException Thrown when the file could not be found.
   */
  public boolean loadTextureCollection(String path, String textureName) throws IOException {
    return loadTextureCollection(path, textureName, Game.class);
  }

  /**
   * Loads an image, creates a texture collection, and adds it to the list.
   * @param path The path to the image (including the file, ex: C:\path\to\file\test.png
   * @param textureName The name to be assigned to it.
   * @param toGetResourceFrom The class to get the resource from.
   * @return Whether the operation was successful.
   * @throws IOException Thrown when the file could not be found.
   */
  public boolean loadTextureCollection(String path, String textureName, Class toGetResourceFrom) throws IOException {
    File file = null;
    try {
      //file = new File(toGetResourceFrom.getResource(path).toURI());
      file = new File(path);
    } catch (Exception ex) {
      Logger.getLogger(TextureManager.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    if (!file.exists()) {
      throw new IOException("Unable to load texture collection: file '" + path + "' does not exist!");
    }
    instance.addCollection(new TextureCollection(textureName, ImageIO.read(file)));
    return true;
  }

  /**
   * Loads an image, creates a particle texture collection, and adds it to the list.
   * @param path The path to the image (including the file, ex: C:\path\to\file\test.png
   * @param textureName The name to be assigned to it.
   * @return Whether the operation was successful.
   * @throws IOException Thrown when the file could not be found.
   */
  public boolean loadParticleTextureCollection(String path, String textureName) throws IOException {
    return loadParticleTextureCollection(path, textureName, Game.class);
  }

  /**
   * Loads an image, creates a particle texture collection, and adds it to the list.
   * @param path The path to the image (including the file, ex: C:\path\to\file\test.png
   * @param textureName The name to be assigned to it.
   * @param toGetResourceFrom The class to get the resource from.
   * @return Whether the operation was successful.
   * @throws IOException Thrown when the file could not be found.
   */
  public boolean loadParticleTextureCollection(String path, String textureName, Class toGetResourceFrom) throws IOException {
    File file = null;
    try {
      //file = new File(toGetResourceFrom.getResource(path).toURI());
      file = new File(path);
    } catch (Exception ex) {
      Logger.getLogger(TextureManager.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    if (!file.exists()) {
      throw new IOException("Unable to load particle texture collection: file '" + path + "' does not exist!");
    }
    instance.addCollection(new ParticleTextureCollection(textureName, ImageIO.read(file)));
    return true;
  }

  /**
   * Loads an image, creates a building texture collection, and adds it to the list.
   * @param path The path to the image (including the file, ex: C:\path\to\file\test.png
   * @param textureName The name to be assigned to it.
   * @return Whether the operation was successful.
   * @throws IOException Thrown when the file could not be found.
   */
  public boolean loadBuildingTextureCollection(String path, String textureName) throws IOException {
    return loadBuildingTextureCollection(path, textureName, Game.class);
  }

  /**
   * Loads an image, creates a building texture collection, and adds it to the list.
   * @param path The path to the image (including the file, ex: C:\path\to\file\test.png
   * @param textureName The name to be assigned to it.
   * @param toGetResourceFrom The class to get the resource from.
   * @return Whether the operation was successful.
   * @throws IOException Thrown when the file could not be found.
   */
  public boolean loadBuildingTextureCollection(String path, String textureName, Class toGetResourceFrom) throws IOException {
    File file = null;
    try {
      //file = new File(toGetResourceFrom.getResource(path).toURI());
      file = new File(path);
    } catch (Exception ex) {
      Logger.getLogger(TextureManager.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    if (!file.exists()) {
      throw new IOException("Unable to load building texture collection: file '" + path + "' does not exist!");
    }
    instance.addCollection(new BuildingTextureCollection(textureName, ImageIO.read(file)));
    return true;
  }

  /**
   * Loads an image, creates a building texture collection, and adds it to the list.
   * @param paths The path to the image (including the file, ex: C:\path\to\file\test.png
   * <br>0 = Right top
   * <br>1 = Right bottom
   * <br>2 = Left top
   * <br>3 = Left bottom
   * @param textureNames The name to be assigned to it.
   * @return Whether the operation was successful.
   * @throws IOException Thrown when the file could not be found.
   * @throws IllegalArgumentException Thrown when the paths length is NOT 4.
   */
  public boolean loadMoveableTextureCollection(String[] paths, String textureName)
          throws IOException, IllegalArgumentException {
    return loadMoveableTextureCollection(paths, textureName, Game.class);
  }

  /**
   * Loads an image, creates a building texture collection, and adds it to the list.
   * @param paths The path to the image (including the file, ex: C:\path\to\file\test.png
   * <br>0 = Right top
   * <br>1 = Right bottom
   * <br>2 = Left top
   * <br>3 = Left bottom
   * @param textureNames The name to be assigned to it.
   * @param toGetResourceFrom The class to get the resource from.
   * @return Whether the operation was successful.
   * @throws IOException Thrown when the file could not be found.
   * @throws IllegalArgumentException Thrown when the paths length is NOT 4.
   */
  public boolean loadMoveableTextureCollection(String[] paths, String textureName, Class toGetResourceFrom)
          throws IOException, IllegalArgumentException {
    if (paths.length != 4) {
      throw new IllegalArgumentException("Paths.length != 4. Noob.");
    }
    Image[] images = new Image[4];
    try {
      for (int i = 0; i < images.length; i++) {
        //File file = new File(toGetResourceFrom.getResource(paths[i]).toURI());
        File file = new File(paths[i]);
        if (!file.exists()) {
          throw new IOException("Unable to load building texture collection: file '" + paths[i] + "' does not exist!");
        }
        images[i] = ImageIO.read(file);
      }
    } catch (Exception ex) {
      Logger.getLogger(TextureManager.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    instance.addCollection(new MoveableTextureCollection(textureName, images));
    return true;
  }

  private TextureManager() {
  }

  public static TextureManager getInstance() {
    if (instance == null) {
      instance = new TextureManager();
    }
    return instance;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Singleton, baby");
  }
}
