/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.core.moveable;

import controller.game.Game;
import iceworld.given.ICEWorldImmigration;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import example.Myicetz;
import model.core.DoublePoint;
import model.core.DrawableObject;
import model.core.PlayfieldGrid;
import model.core.PlayfieldSquare;
import model.core.Square;
import model.core.events.MoveEvent;
import model.core.events.SelectEvent;
import model.core.events.ZoomChangeEvent;
import model.core.pathfinding.AStarAlgorithm;
import model.core.texture.MoveableTextureCollection;
import variables.ZoomStatic;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public abstract class Moveable extends DrawableObject {

  /**
   * Movement state of this Moveable object.
   */
  public final static int RIGHT_TOP = 0, RIGHT_BOTTOM = 1,
          LEFT_TOP = 2, LEFT_BOTTOM = 3;
  private double speed;
  private LinkedList<Point> waypoints = new LinkedList<Point>();
  /**
   * Offsets used for placing the unit on the correct side of the road.
   */
  public static double X_SPEED_MODIFIER = 1;
  public static double Y_SPEED_MODIFIER = (double) PlayfieldSquare.HEIGHT / (double) PlayfieldSquare.WIDTH;
  private MoveableTextureCollection textures;
  private Square square;
  private int moveState = RIGHT_TOP;
  private boolean selected = false, mouseOver = false;
  private int pathfindingMode = AStarAlgorithm.MODE_NORMAL;
  
  Myicetz icetizen;
  int uid;
  ICEWorldImmigration immigration;
  // <editor-fold defaultstate="collapsed" desc="Getters and setters">
  /**
   * Gets the pathfinding mode of this Moveable. Default is AStarAlgorithm.MODE_NORMAL;
   * @return The mode.
   */
  
  public void setIcetizen(Myicetz my){
	  icetizen = my;
  }
  public void setUID(int u){
	  uid=u;
	  //System.out.println("uuuuuuuuuu---"+u+"---uuuuuuuuuuuuu");
  }
  public void setImmigration(ICEWorldImmigration immi){
	  immigration = immi;
  }
  public int getPathfindingMode() {
    return pathfindingMode;
  }

  /**
   * Sets the pathfinding mode of this Moveable.
   * @param pathfindingMode The new mode.
   */
  public void setPathfindingMode(int pathfindingMode) {
    this.pathfindingMode = pathfindingMode;
  }

  /**
   * Gets whether or not the mouse is over this object.
   * @return The flag.
   */
  public boolean isMouseOver() {
    return mouseOver;
  }

  /**
   * Sets whether or not the mouse is over this object.
   * @param mouseOver True or false.
   */
  public void setMouseOver(boolean mouseOver) {
    this.mouseOver = mouseOver;
  }

  public boolean isSelected() {
    return selected;
  }

  /**
   * Sets whether this object is selected or not by the mouse.
   * @param selected True or false. Go for it.
   */
  public void setSelected(boolean selected) {
    Game game = EngineWindow.getInstance().getGame();
    if (selected) {
      game.getSelectedUnits().add(this);
    } else {
      game.getSelectedUnits().remove(this);
    }
    // If the selected flag actually changed
    if (this.selected != selected) this.onSelect(new SelectEvent(this, selected));
    this.selected = selected;
  }

  public int getMoveState() {
    return moveState;
  }

  /**
   * Sets the move state of this object.
   * @param moveState The state.
   * @see #LEFT_BOTTOM
   * @see #LEFT_TOP
   * @see #RIGHT_BOTTOM
   * @see #RIGHT_TOP
   */
  public void setMoveState(int moveState) {
    this.moveState = moveState;
  }

  /**
   * Gets the square this moveable object is currently in.
   * @return The square.
   */
  public Square getSquare() {
    return square;
  }

  protected void setSquare(Square square) {
    if (this.square != null) {
      this.square.setHighlighted(false);
    }
    this.square = square;
    square.setHighlighted(true);
  }

  public MoveableTextureCollection getMoveableTextureCollection() {
    return textures;
  }

  public void setMoveableTextureCollection(MoveableTextureCollection textures) {
    this.textures = textures;
  }

  public double getSpeed() {
    return speed;
  }

  /**
   * Sets the speed of this moveable object.
   * @param halfMeterPerSecond The speed.
   */
  public void setSpeed(double halfMeterPerSecond) {
    this.speed = halfMeterPerSecond;
  }

  @Override
  public Point getDrawPixelLocation() {
    Point offset = EngineWindow.getInstance().getGame().getPlayfieldGrid().getDrawOffset();
    return new Point((int) ((this.getPixelLocation().x) + offset.x) - (PlayfieldSquare.WIDTH / 2),
            (int) ((this.getPixelLocation().y) + offset.y) - (PlayfieldSquare.HEIGHT / 2));
  }

  public LinkedList<Point> getWaypoints() {
    return waypoints;
  }

  /**
   * Sets the waypoints of this moveable object. The object will then move
   * towards the first point of the waypoints list, when it reaches that point,
   * it will be removed from the list until the list is empty.
   * @param waypoints The List containing the waypoints, in grid location
   */
  public void setWaypoints(LinkedList<Point> waypoints) {
    this.waypoints = waypoints;
  }
  // </editor-fold>

  /**
   * Gets the draw bounding box of this moveable object.
   * @return The Rectangle containing the points etc.
   */
  public Rectangle getDrawBoundingBox() {
    Image image = this.getMoveableTextureCollection().
            getImage(ZoomStatic.CURRENT_ZOOM_LEVEL,
            this.getMoveState());
    Point p = this.getDrawPixelLocation();
    return new Rectangle(p.x, p.y, image.getWidth(null), image.getHeight(null));
  }

  /**
   * Moves this unit towards the first of the points contained in
   * <code>waypoints</code>.
   */
  public void updatePosition() {
    if (this.getWaypoints() == null || this.getWaypoints().isEmpty()) {
      return;
    }

    /*if (EngineWindow.getInstance().getGame().getTotalFrames() % 300 == 0) {
    System.out.println("x: " + (this.getSpeed() * X_SPEED_MODIFIER)
    + ", y: " + (this.getSpeed() * Y_SPEED_MODIFIER));
    }*/

    PlayfieldGrid grid = EngineWindow.getInstance().getGame().getPlayfieldGrid();
    Point target = grid.translateGridToPixel(
            this.getWaypoints().get(0).x, this.getWaypoints().get(0).y);
    target = new Point(target.x, target.y + (int) (PlayfieldSquare.HEIGHT / 2.0));
    boolean movingLeft = false;
    // System.out.println("Moving a unit towards " + target);
    // If the target is to the left of our unit
    if (target.x < this.getPixelLocation().x) {
      // If it's so close that one step would overshoot it
      if (this.getPixelLocation().x - target.x < (this.getSpeed() * X_SPEED_MODIFIER)) {
        // They're equal now
        this.setPixelLocation(new DoublePoint(target.x, this.getPixelLocation().y));
      } else {
        // Move this unit towards the point
        this.setPixelLocation(new DoublePoint(this.getPixelLocation().x
                - (this.getSpeed() * X_SPEED_MODIFIER), this.getPixelLocation().y));
        movingLeft = true;
      }
    } // If the target is to the right of our unit
    else if (target.x > this.getPixelLocation().x) {
      // If it's so close that one step would overshoot it
      if (target.x - this.getPixelLocation().x < (this.getSpeed() * X_SPEED_MODIFIER)) {
        // They're equal now
        this.setPixelLocation(new DoublePoint(target.x, this.getPixelLocation().y));
      } else {
        // Move this unit towards the point
        this.setPixelLocation(new DoublePoint(this.getPixelLocation().x
                + (this.getSpeed() * X_SPEED_MODIFIER), this.getPixelLocation().y));
      }
    }




    // If the target is to the bottom of our unit
    if (target.y < this.getPixelLocation().y) {
      // If it's so close that one step would overshoot it
      if (this.getPixelLocation().y - target.y < (this.getSpeed() * Y_SPEED_MODIFIER)) {
        // They're equal now
        this.setPixelLocation(new DoublePoint(this.getPixelLocation().x, target.y));
      } else {
        if (movingLeft) {
          this.setMoveState(Moveable.LEFT_TOP);
        } else {
          this.setMoveState(Moveable.RIGHT_TOP);
        }
        // Move this unit towards the point
        this.setPixelLocation(new DoublePoint(this.getPixelLocation().x, (this.getPixelLocation().y
                - (this.getSpeed() * Y_SPEED_MODIFIER))));
      }
    } // If the target is to the top of our unit
    else if (target.y > this.getPixelLocation().y) {
      // If it's so close that one step would overshoot it
      if (target.y - this.getPixelLocation().y < (this.getSpeed() * Y_SPEED_MODIFIER)) {
        // They're equal now
        this.setPixelLocation(new DoublePoint(this.getPixelLocation().x, target.y));
      } else {
        if (movingLeft) {
          this.setMoveState(Moveable.LEFT_BOTTOM);
        } else {
          this.setMoveState(Moveable.RIGHT_BOTTOM);
        }
        // Move this unit towards the point
        this.setPixelLocation(new DoublePoint(this.getPixelLocation().x, (this.getPixelLocation().y
                + (this.getSpeed() * Y_SPEED_MODIFIER))));
      }
    }
    onMove(new MoveEvent(this, MoveEvent.TYPE_MOVE));

    if (this.getPixelLocation().x == target.x
            && this.getPixelLocation().y == target.y) {
      // Unhighlight this square, as the current target is reached
      grid.getSquareFromPoint(this.getWaypoints().getFirst()).setHighlighted(0);
      // Current target reached, remove the current target
      this.getWaypoints().removeFirst();
      if (this.getWaypoints().isEmpty()) onMove(new MoveEvent(this, MoveEvent.TYPE_MOVE_ENDED));
    }

    Square s = grid.translatePixelToSquare(this.getPixelLocation().toPoint());
    /*if (this.getSquare() != null) {
    if (!this.getSquare().equals(s)) {
    this.getSquare().setHighlighted(false);
    if (s != null) s.setHighlighted(true);
    }
    }*/
    if (s != null) {
      this.setSquare(s);
    }
  }

  /**
   * Moves to an additional point. This does not clear the current path of the unit,
   * rather adds new waypoints to the current list (if any)
   * @param newTarget The new target to move to.
   */
  public void moveToAdditional(Square target) {
    PlayfieldGrid grid = EngineWindow.getInstance().getGame().getPlayfieldGrid();
    long startMS = System.currentTimeMillis();

    Square start = null;

    if (this.getWaypoints() != null) {
      for (int i = 0; i < this.getWaypoints().size(); i++) {
        grid.getSquareFromPoint(this.getWaypoints().get(i)).setHighlighted(0);
      }
      try {
        start = grid.getSquareFromPoint(this.getWaypoints().getLast());
        if (start.equals(target)) return;
      } catch (NoSuchElementException e) {
        moveTo(target);
        return;
      }
    } else {
      Point gridLocation = grid.translatePixelToGrid((int) this.getPixelLocation().x, (int) this.getPixelLocation().y);
      start = grid.getSquareFromPoint(gridLocation);
    }

    LinkedList<Point> pointList = new AStarAlgorithm(grid,
            start,
            target, this.getPathfindingMode()).findPath();
    if (pointList != null) {
      this.getWaypoints().addAll(pointList);
    }
    System.out.println("Updated path of a unit in " + (System.currentTimeMillis() - startMS) + "ms! From "
            + "(" + start.getGridX() + ", "
            + start.getGridY() + "),  "
            + "(" + target.getGridX() + ", " + target.getGridY() + ")");

    if (this.getWaypoints() != null) {
      for (int i = 0; i < this.getWaypoints().size(); i++) {
        grid.getSquareFromPoint(this.getWaypoints().get(i)).setHighlighted(120);
      }
    }
  }

  /**
   * Moves this unit to the target square.
   * @param grid The grid containing the squares.
   * @param target The target square to reach.
   */
public void moveTo2(int x, int y) {
	  
	  //immigration.walk(target.getGridX() , target.getGridY());
	  
    PlayfieldGrid grid = EngineWindow.getInstance().getGame().getPlayfieldGrid();
    Square target = grid.getSquareFromPoint(new Point(x,y));
    if (this.getSquare() != null && this.getSquare().equals(target)) return;
    
    //get current position of that icetizen
    Point gridLocation = grid.translatePixelToGrid((int) this.getPixelLocation().x, (int) this.getPixelLocation().y);
    
    long startMS = System.currentTimeMillis();
    //System.out.println("Moving2 froms------------------(" + grid.getSquares()[gridLocation.x][gridLocation.y].getGridX() + ", " + grid.getSquares()[gridLocation.x][gridLocation.y].getGridY() + ")---------------------");
    //System.out.println("Moving2 to -------------------(" + target.getGridX() + ", " + target.getGridY() + ")---------------------");
    
    if (this.getWaypoints() != null) {
      for (int i = 0; i < this.getWaypoints().size(); i++) {
        grid.getSquareFromPoint(this.getWaypoints().get(i)).setHighlighted(0);
      }
      //set waypoint
      // Already has waypoints, so we must fire end event
      onMove(new MoveEvent(this, MoveEvent.TYPE_MOVE_ENDED));
    }
    
    //Movement start
    
    System.out.println("Moving2 to something with mode = " + this.getPathfindingMode());
    this.setWaypoints(new AStarAlgorithm(grid,
            grid.getSquareFromPoint(gridLocation),
            target, this.getPathfindingMode()).findPath());
            
    //Movement end

    System.out.println("Updated2 path of a unit in " + (System.currentTimeMillis() - startMS) + "ms! From2 "
            + "(" + grid.getSquares()[gridLocation.x][gridLocation.y].getGridX() + ", "
            + grid.getSquares()[gridLocation.x][gridLocation.y].getGridY() + "),  "
            + "(" + target.getGridX() + ", " + target.getGridY() + ")");
    
    if (this.getWaypoints() != null) {
      onMove(new MoveEvent(this, MoveEvent.TYPE_MOVE_STARTED));
      for (int i = 0; i < this.getWaypoints().size(); i++) {
        grid.getSquareFromPoint(this.getWaypoints().get(i)).setHighlighted(120);
      }
    }
    
  }
  public void moveTo(Square target) {
	  
	  immigration.walk(target.getGridX() , target.getGridY());
	  
    if (this.getSquare() != null && this.getSquare().equals(target)) return;
    PlayfieldGrid grid = EngineWindow.getInstance().getGame().getPlayfieldGrid();
    Point gridLocation = grid.translatePixelToGrid((int) this.getPixelLocation().x, (int) this.getPixelLocation().y);
    long startMS = System.currentTimeMillis();
    System.out.println("-------------------(" + grid.getSquares()[gridLocation.x][gridLocation.y].getGridX() + ", " + grid.getSquares()[gridLocation.x][gridLocation.y].getGridY() + ")---------------------");
    System.out.println("-------------------(" + target.getGridX() + ", " + target.getGridY() + ")---------------------");
    
    if (this.getWaypoints() != null) {
      for (int i = 0; i < this.getWaypoints().size(); i++) {
        grid.getSquareFromPoint(this.getWaypoints().get(i)).setHighlighted(0);
      }
      //set waypoint
      // Already has waypoints, so we must fire end event
      onMove(new MoveEvent(this, MoveEvent.TYPE_MOVE_ENDED));
    }
    
    //Movement start
    System.out.println("Moving to something with mode = " + this.getPathfindingMode());
    this.setWaypoints(new AStarAlgorithm(grid,
            grid.getSquareFromPoint(gridLocation),
            target, this.getPathfindingMode()).findPath());
    //Movement end

    System.out.println("Updated path of a unit in " + (System.currentTimeMillis() - startMS) + "ms! From "
            + "(" + grid.getSquares()[gridLocation.x][gridLocation.y].getGridX() + ", "
            + grid.getSquares()[gridLocation.x][gridLocation.y].getGridY() + "),  "
            + "(" + target.getGridX() + ", " + target.getGridY() + ")");
    
    if (this.getWaypoints() != null) {
      onMove(new MoveEvent(this, MoveEvent.TYPE_MOVE_STARTED));
      for (int i = 0; i < this.getWaypoints().size(); i++) {
        grid.getSquareFromPoint(this.getWaypoints().get(i)).setHighlighted(120);
      }
    }
    
  }

  /**
   * Call this when the zoom has changed
   */
  public void onZoomChange(ZoomChangeEvent event) {
    // Assuming that the square has already been adjusted to a new point
    Square s = this.getSquare();
    this.setPixelLocation(DoublePoint.fromPoint(s.getCenter()));

    /*this.setPixelLocation(new DoublePoint(this.getPixelLocation().x * event.getxFactor(),
    this.getPixelLocation().y * event.getyFactor()));*/

    // Grid grid = EngineWindow.getInstance().getGame().getPlayfieldGrid();
    Moveable.X_SPEED_MODIFIER = ((double) PlayfieldSquare.WIDTH / (double) ZoomStatic.ZOOM_LARGE_WIDTH);
    Moveable.Y_SPEED_MODIFIER = ((double) PlayfieldSquare.HEIGHT / (double) ZoomStatic.ZOOM_LARGE_HEIGHT)
            / (ZoomStatic.ZOOM_LARGE_WIDTH / (double) ZoomStatic.ZOOM_LARGE_HEIGHT);
  }

  public void draw(Graphics g) {
    Image image = this.getMoveableTextureCollection().getImage(ZoomStatic.CURRENT_ZOOM_LEVEL, this.getMoveState());
    Point drawLocation = this.getDrawPixelLocation();
    int x = (int) drawLocation.x,
            y = (int) drawLocation.y - Math.abs(PlayfieldSquare.HEIGHT - image.getHeight(null));
    //if (this.isMouseOver()) {
    // <editor-fold defaultstate="collapsed" desc="Attempt at flood filling etc .. I give up">
      /*Graphics2D g2 = (Graphics2D) g;
    Composite old = g2.getComposite();
    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
    g2.setComposite(ac);
    g2.drawImage(image, x, y, null);
    g2.setComposite(old);
    int w = image.getWidth(null), h = image.getHeight(null);
    Graphics g2 = g.create(0 - w, 0 - h, w, h);

    DebugGraphics temp = new DebugGraphics(g2);
    // Fill with pink
    temp.setColor(new Color(255, 0, 255));
    temp.fillRect(0, 0, w, h);
    // Draw image
    temp.drawImage(image, 0, 0, null);
    // Draw half-red rectangle
    temp.setColor(new Color(255, 0, 0, 127));
    temp.fillRect(0, 0, w, h);

    // Now make the previous pixels translucent again
    //g2.setColor(new Color(0, 0, 0, 255));
    //g2.fillRect(x, y, w, h);
    // Draw a new image
    g.drawImage(DrawStatic.floodFill(temp, toBufferedImage(image), 0, 0, new Color(0, 0, 0, 0)), x, y, null);
    temp.dispose();*/
    // </editor-fold>
    //} else {
    g.drawImage(image, x, y, null);
    //}


    if (this.isSelected() || this.isMouseOver()) {
      g.setColor(Color.BLUE);
      int w = image.getWidth(null), h = image.getHeight(null);
      // Top left corner
      g.drawLine(x, y, x + (int) (w * 0.2), y);
      g.drawLine(x, y, x, y + (int) (h * 0.2));

      // Top right corner
      g.drawLine(x + (int) (w * 0.8), y, x + w, y);
      g.drawLine(x + w, y, x + w, (int) (y + w * 0.2));

      // Bottom left corner
      g.drawLine(x, y + h, x, y + (int) (h * 0.8));
      g.drawLine(x, y + h, x + (int) (w * 0.2), y + h);

      // Bottom right corner
      g.drawLine(x + w, y + h, x + (int) (w * 0.8), y + h);
      g.drawLine(x + w, y + h, x + w, y + (int) (h * 0.8));
    }
  }

  public abstract void onMove(MoveEvent event);

  /**
   * Called when the unit is selected, or deselected.
   */
  public abstract void onSelect(SelectEvent event);

  public Moveable() {

    EngineWindow.getInstance().getGame().getMoveableObjects().add(this);
    Moveable.X_SPEED_MODIFIER = ((double) PlayfieldSquare.WIDTH / (double) ZoomStatic.ZOOM_LARGE_WIDTH);
    Moveable.Y_SPEED_MODIFIER = ((double) PlayfieldSquare.HEIGHT / (double) ZoomStatic.ZOOM_LARGE_HEIGHT)
            / (ZoomStatic.ZOOM_LARGE_WIDTH / (double) ZoomStatic.ZOOM_LARGE_HEIGHT);
  }
  // <editor-fold defaultstate="collapsed" desc="Failing toBufferedImage code">
  /*public static BufferedImage toBufferedImage(Image image) {
  if (image instanceof BufferedImage) {
  return (BufferedImage) image;
  }

  // This code ensures that all the pixels in the image are loaded
  image = new ImageIcon(image).getImage();

  // Determine if the image has transparent pixels; for this method's
  // implementation, see Determining If an Image Has Transparent Pixels
  boolean hasAlpha = true;

  // Create a buffered image with a format that's compatible with the screen
  BufferedImage bimage = null;
  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  try {
  // Determine the type of transparency of the new buffered image
  int transparency = Transparency.OPAQUE;
  if (hasAlpha) {
  transparency = Transparency.BITMASK;
  }

  // Create the buffered image
  GraphicsDevice gs = ge.getDefaultScreenDevice();
  GraphicsConfiguration gc = gs.getDefaultConfiguration();
  bimage = gc.createCompatibleImage(
  image.getWidth(null), image.getHeight(null), transparency);
  } catch (HeadlessException e) {
  // The system does not have a screen
  }

  if (bimage == null) {
  // Create a buffered image using the default color model
  int type = BufferedImage.TYPE_INT_RGB;
  if (hasAlpha) {
  type = BufferedImage.TYPE_INT_ARGB;
  }
  bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
  }

  // Copy image to buffered image
  Graphics g = bimage.createGraphics();

  // Paint the image onto the buffered image
  g.drawImage(image, 0, 0, null);
  g.dispose();

  return bimage;
  }*/
  // </editor-fold>
}
