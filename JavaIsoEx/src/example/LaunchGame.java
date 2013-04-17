/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import controller.game.Game;
import controller.game.GameUpdateLoop;
import example.units.Alien;
import example.units.IceTizen;
import example.units.IceTizenName;

import iceworld.given.ICEWorldImmigration;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import model.core.PlayfieldGrid;
import model.core.texture.BuildingTextureCollection;
import model.core.texture.TextureCollection;
import model.core.texture.TextureManager;
import variables.GeneralStatic;
import variables.ZoomStatic;
import view.EngineWindow;

import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

import model.core.DoublePoint;
import model.core.Square;
import model.core.texture.MoveableTextureCollection;
import model.core.texture.TexturePlane;
/**
 *
 * @author Wouter
 */

public class LaunchGame extends Game{
	char colon = ':';
	char quotationMark = '"';
	BufferedImage[] image;
	Myicetz player = new Myicetz();
	int uid;
	ICEWorldImmigration immigration;
	User[] userList=null;
	GameUpdateLoop uloop;
	double halfMeterPerSecond = 0.5;
	int Cnt;
	Graphics g;
	//IceTizenName icePlayerName;
	Font nameFont = new Font("Tahoma",Font.PLAIN,11);
	FontRenderContext frc = new FontRenderContext(null,true,true);
	int playerNameW;
	int playerNameH;
	
	BufferedImage OtherImageHolder;
	
	ProcessState ps;
	
	User otherUser;
    Point otherPlayerPoint;
    DoublePoint otherPlayerDoublePoint;
    MoveableTextureCollection otherPlayerCollection;
    
    MoveableTextureCollection[] otherAlienCollection;
    
    Image otherPlayerImageHolder;
    Image[] otherPlayerImage;
    int IndexCnt;
    
    User[] otherUserList;
    User[] allUserList;
    public User playerUser;
    public IceTizen icePlayer;
    Point pl;
    DoublePoint dpl;
    Point[] otherPoint;
    DoublePoint[] otherDoublePoint;
    Myicetz[] otherIcetizen;

    User newUser;
    Point newUserPoint;
    DoublePoint newUserDoublePoint;
    MoveableTextureCollection newUserCollection;
    Image newUserImageHolder;
    Image[] newUserImage;
    int newUserIndex=1;
    
    BufferedImage[] playerImage;
    BufferedImage[] playerNameImage;
    BufferedImage myImage;
    MoveableTextureCollection playerCollection;
    MoveableTextureCollection playerNameCollection;
    
    BufferedImage[][] otherImageList;
    BufferedImage[] otherImage;
    MoveableTextureCollection[] otherCollection;
    IceTizen[] otherList;
    Rectangle2D playerNameBound;
    
    TextureManager manager;
    
    public TestEngineWindow tew;

    
	//public String dummyText = "hellou";
	
	
  @Override
  public void onStart() {
    this.setPlayfieldGrid(new PlayfieldGrid(100, 100));
    GeneralStatic.setGameState(GeneralStatic.STATE_RUNNING);
    this.getPlayfieldGrid().setDrawTree(false);
    this.getPlayfieldGrid().setDrawGrid(true);
    this.getPlayfieldGrid().setCurrentZoomLevel(ZoomStatic.ZOOM_MEDIUM);
    System.out.print("start");
    String sep = File.separator, imagesFolder = ".." + sep + "images" + sep;

    // <editor-fold defaultstate="collapsed" desc="Init textures and buildings">
    File test_texture = null, test_texture_2 = null, test_pyramid = null, test_tent = null, tree_small = null;
    File road_straight = null, road_straight_mirror = null, road_corner = null,
            road_corner_flip = null, road_corner_mirror = null, road_corner_flip_mirror = null,
            road_crossroad = null, road_t_split = null, road_t_split_flip = null, road_t_split_mirror = null,
            road_t_split_flip_mirror = null;
    manager = TextureManager.getInstance();

    try {
      String path = imagesFolder + "textures" + sep;
      System.out.println(LaunchGame.class.getResource(path + "bare2.png").toURI());
      test_texture = new File(LaunchGame.class.getResource(path + "bare2.png").toURI());
      test_texture_2 = new File(LaunchGame.class.getResource(path + "temperate.png").toURI());

      path = imagesFolder + "objects" + sep;
      test_pyramid = new File(LaunchGame.class.getResource(path + "test_object.png").toURI());
      test_tent = new File(LaunchGame.class.getResource(path + "test_object2.png").toURI());
      tree_small = new File(LaunchGame.class.getResource(path + "tree_1_small.png").toURI());



      // <editor-fold defaultstate="collapsed" desc="Load road textures">
      path = ".." + sep + "images" + sep + "textures" + sep + "roads" + sep;
      road_straight = new File(LaunchGame.class.getResource(path + "road_straight.png").toURI());
      road_straight_mirror = new File(LaunchGame.class.getResource(path + "road_straight_mirror.png").toURI());
      road_corner = new File(LaunchGame.class.getResource(path + "road_corner.png").toURI());
      road_corner_flip = new File(LaunchGame.class.getResource(path + "road_corner_flip.png").toURI());
      road_corner_mirror = new File(LaunchGame.class.getResource(path + "road_corner_mirror.png").toURI());
      road_corner_flip_mirror = new File(LaunchGame.class.getResource(path + "road_corner_flip_mirror.png").toURI());

      road_crossroad = new File(LaunchGame.class.getResource(path + "road_crossroad.png").toURI());
      road_t_split = new File(LaunchGame.class.getResource(path + "road_t_split.png").toURI());
      road_t_split_flip = new File(LaunchGame.class.getResource(path + "road_t_split_flip.png").toURI());
      road_t_split_mirror = new File(LaunchGame.class.getResource(path + "road_t_split_mirror.png").toURI());
      road_t_split_flip_mirror = new File(LaunchGame.class.getResource(path + "road_t_split_flip_mirror.png").toURI());

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
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
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
    	
      images[0] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "truck_right_top.png").toURI()));
      images[1] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "truck_right_bottom.png").toURI()));
      images[2] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "truck_left_top.png").toURI()));
      images[3] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "truck_left_bottom.png").toURI()));

    } catch (URISyntaxException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    MoveableTextureCollection collection = new MoveableTextureCollection("Truck", images);
    manager.addCollection(collection);
    
    Image[] teslaImages = new Image[4];
    try {
      
      teslaImages[0] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "tesla_right_top.png").toURI()));
      teslaImages[1] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "tesla_right_bottom.png").toURI()));
      teslaImages[2] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "tesla_left_top.png").toURI()));
      teslaImages[3] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "tesla_left_bottom.png").toURI()));
      
      
    } catch (URISyntaxException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    }
    MoveableTextureCollection teslaCollection = new MoveableTextureCollection("TeslaTank", teslaImages);
    manager.addCollection(teslaCollection);
    
    BufferedImage[] commanderImage = new BufferedImage[4];
    try {
      
    	commanderImage[0] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "general.png").toURI()));
    	commanderImage[1] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "general.png").toURI()));
    	commanderImage[2] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "general.png").toURI()));
    	commanderImage[3] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "general.png").toURI()));
      
      
    } catch (URISyntaxException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    }
    MoveableTextureCollection commanderCollection = new MoveableTextureCollection("Commander", commanderImage);
    manager.addCollection(commanderCollection);
    
    /*
    BufferedImage[] playerImage = new BufferedImage[4];
    BufferedImage myImage = getImage(uid);
    playerImage[0] = myImage;
	playerImage[1] = myImage;
	playerImage[2] = myImage;
	playerImage[3] = myImage;

    MoveableTextureCollection playerCollection = new MoveableTextureCollection("Player", playerImage);
    manager.addCollection(playerCollection);
    */
    
    BufferedImage[] alienImage = new BufferedImage[4];
    try {
      
    	
    	//correct one
    	alienImage[0] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_right.png").toURI()));
    	alienImage[1] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_right.png").toURI()));
    	alienImage[2] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_left.png").toURI()));
    	alienImage[3] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_left.png").toURI()));

    	//Aesthetic one
    	/*
    	alienImage[0] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_left.png").toURI()));
    	alienImage[1] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_left.png").toURI()));
    	alienImage[2] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_right.png").toURI()));
    	alienImage[3] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_right.png").toURI()));
      */
      
    } catch (URISyntaxException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, ex);
    }
    MoveableTextureCollection alienCollection = new MoveableTextureCollection("Alien", alienImage);
    manager.addCollection(alienCollection);
    // </editor-fold>
    /*
    for (int i =0;i<10;i++){
    	for (int j =0 ; j<10;j++){
    		Point p = this.getPlayfieldGrid().translateGridToPixel(i, j);
    	    DoublePoint dp = DoublePoint.fromPoint(p);
    	    Truck truck = new Truck(dp, 1, collection);
    	}
    }
    */
    /*
    for (int i =0;i<50;i++){
    	for (int j =0 ; j<55;j++){
    		Point p = this.getPlayfieldGrid().translateGridToPixel(i,j);
    	    DoublePoint dp = DoublePoint.fromPoint(p);
    	    Alien alien = new Alien(dp, 1, alienCollection);
    	}
    }
    */
    /*
    for(int x=0; x<4;x++){
    	for(int y=0; y<3;y++){
    		for (int i =0;i<11;i++){
    	    	for (int j =0 ; j<5;j++){
    	    		Point p = this.getPlayfieldGrid().translateGridToPixel(i+(12*x)+1, 99-((2*j)+(12*y)));
    	    	    DoublePoint dp = DoublePoint.fromPoint(p);
    	    	    TeslaTank tesla = new TeslaTank(dp, 1, teslaCollection);
    	    	}
    	    }
        }
    }
    */
    /*
    Point p = this.getPlayfieldGrid().translateGridToPixel(24,62);
    DoublePoint dp = DoublePoint.fromPoint(p);
    IceTizen NattWara = new IceTizen(dp, 1, commanderCollection);
    */
    ps = new ProcessState();
    allUserList = ps.getAllUserData();
    uloop.setUserList(allUserList);
    
    System.out.print(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"+uid+";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
    for (int i=0;i<allUserList.length;i++){
    	if(allUserList[i].getUID()==uid){
    		playerUser = allUserList[i];
    	}
    }
    Cnt = 0;
    otherUserList = new User[allUserList.length-1];

    for (int i=0;i<allUserList.length;i++){
    	if(allUserList[i].getUID()!=uid){
    		System.out.println(allUserList[i].getAllUserData());
    		otherUserList[Cnt] = allUserList[i];
    		Cnt += 1;
    		
    	}
    }

    /*
    Cnt = 0;
    for (User element : allUserList) {
    	if(element.getUID()==uid){
    		playerUser = element;
    		break;
    	} else {
    		otherUserList[Cnt] = element;
    		Cnt += 1;
    	}
    }
    */
    
    //add yourself
    
    pl = this.getPlayfieldGrid().translateGridToPixel(playerUser.getLKDPosX(),playerUser.getLKDPosY());
    dpl = DoublePoint.fromPoint(pl);
    
    if(playerUser.getType()==1){
    	playerImage = new BufferedImage[4];
    	myImage = showName(getImage(uid),playerUser);
    	
    	playerImage[0] = myImage;
    	playerImage[1] = myImage;
    	playerImage[2] = myImage;
    	playerImage[3] = myImage;

    	playerCollection = new MoveableTextureCollection("Player", playerImage);
    	manager.addCollection(playerCollection);
    	icePlayer = new IceTizen(dpl, 1, playerCollection);

    } else {
    	icePlayer = new IceTizen(dpl, 1, alienCollection);
    }
    icePlayer.setSpeed(halfMeterPerSecond);
    icePlayer.setIcetizen(player);
    icePlayer.setUID(uid);
    icePlayer.setImmigration(immigration);
    
    /*
    g = PlayerName.getGraphics();
    g.drawString(playerUser.getUsername(), 0, 0);
    
    playerNameImage[0] = PlayerName;
    playerNameImage[1] = PlayerName;
    playerNameImage[2] = PlayerName;
    playerNameImage[3] = PlayerName;
    
    playerNameCollection = new MoveableTextureCollection("Player", playerNameImage);
	manager.addCollection(playerNameCollection);
	icePlayerName = new IceTizenName(dpl, 1, playerNameCollection);
    icePlayerName.setZIndex(20);
	*/
    /*
    Graphics gg = PlayerName.getGraphics();
    gg.drawString(playerUser.getUsername(), icePlayer.getSquare().getDrawPixelX()-20, icePlayer.getSquare().getDrawPixelY()-15);
    
    */
    //TestEngineWindow.getInstance().getCanvas().add(PlayerName);
    // add other player
    
    otherPoint = new Point[otherUserList.length];
    otherDoublePoint = new DoublePoint[otherUserList.length];
    otherImage = new BufferedImage[otherUserList.length];
    otherImageList = new BufferedImage[otherUserList.length][4];
    otherCollection = new MoveableTextureCollection[otherUserList.length];
    otherAlienCollection = new MoveableTextureCollection[otherUserList.length];
    otherList = new IceTizen[otherUserList.length];
    otherIcetizen = new Myicetz[otherUserList.length];
    
    for (int i = 0;i<otherUserList.length;i++){
    	otherPoint[i] = this.getPlayfieldGrid().translateGridToPixel(otherUserList[i].getLKDPosX(),otherUserList[i].getLKDPosY());
    	otherDoublePoint[i] = DoublePoint.fromPoint(otherPoint[i]);
    	
    	if(otherUserList[i].getType()==1){
    		otherImageList[i] = new BufferedImage[4];
    		otherImage[i] = getImage(otherUserList[i].getUID());
    		
    		OtherImageHolder = showName(otherImage[i],otherUserList[i]);
    		
    		otherImageList[i][0] = OtherImageHolder;
    		otherImageList[i][1] = OtherImageHolder;
    		otherImageList[i][2] = OtherImageHolder;
    		otherImageList[i][3] = OtherImageHolder;

        	otherCollection[i] = new MoveableTextureCollection(otherUserList[i].getUsername(), otherImageList[i]);
        	manager.addCollection(otherCollection[i]);
        	otherList[i] = new IceTizen(otherDoublePoint[i], 1, otherCollection[i]);

        } else {
        	otherList[i] = new IceTizen(otherDoublePoint[i], 1, alienCollection);
        }
    	otherList[i].setSpeed(halfMeterPerSecond);
    	//otherList[i].setIcetizen(player);
    	//otherList[i].setUID(uid);
    	//otherList[i].setImmigration(immigration);
    }
    
    
    EngineWindow.getInstance().setContentPane(EngineWindow.getInstance().getCanvas());
  }
    
    
        /*
    	otherList[IndexCnt] = new IceTizen(otherPlayerDoublePoint, 1,otherPlayerCollection);
    	otherList[IndexCnt].setUser(userList[i]);
    	IndexCnt += 1;
    	*/
    
    
    /*
    BufferedImage[] playerImage = new BufferedImage[4];
    playerImage[0] = getImage(uid);
	playerImage[1] = getImage(uid);
	playerImage[2] = getImage(uid);
	playerImage[3] = getImage(uid);

    MoveableTextureCollection playerCollection = new MoveableTextureCollection("Player", playerImage);
    manager.addCollection(playerCollection);
     */
    		

    /*
    for (int i =0;i<8;i++){
    	for (int j =0 ; j<8;j++){
    		Point p = this.getPlayfieldGrid().translateGridToPixel(i, j);
    	    DoublePoint dp = DoublePoint.fromPoint(p);
    	    TeslaTank truck = new TeslaTank(dp, 1, teslaCollection);
    	}
    }
    */
    /*
    Point p = this.getPlayfieldGrid().translateGridToPixel(3, 3);
    DoublePoint dp = DoublePoint.fromPoint(p);
    Truck truck = new Truck(dp, 1, collection);
    // truck.moveTo(this.getPlayfieldGrid().getSquares()[0][15]);

    p = this.getPlayfieldGrid().translateGridToPixel(5, 5);
    dp = DoublePoint.fromPoint(p);
    Truck truck2 = new Truck(dp, 1, collection);
    // truck2.moveTo(null);
     */
    
    

  

  @Override
  public void onDraw() {
    // throw new UnsupportedOperationException("Not supported yet.");
  }
  /*
  public UpdateGame getGameLoop(){
	  super(l)
  }
  */
  /*
  public void testParsing(String s){
	  System.out.print(s+"------------------------------------"+s);
  }
  */

  public LaunchGame(UpdateGame loop) {
	  super(loop);
	  System.out.print("lg");
	  
	  loop.setLaunchGame(this);
	  loop.setUID(uid);
	  uloop = loop;
  }
  /*
  public void printSth(String sth){
	  System.out.println("ooooooooo"+sth+"ooooooooo");
  }
  */
  public void setIcetizen(Myicetz il){
	  player=il;
  }
  public void setPlayerUID(int i){
	  uid=i;
  }
  public void setImmigration(ICEWorldImmigration immi){
	  immigration = immi;
  }
  public BufferedImage getImage(int id){
	  BufferedImage combined, result,newCombined,indicator = null;
		BufferedImage[] bi=null;
		String[] res;
		try {
			res = refineGraphicResouces(ProcessReq("gresources "+id));
			System.out.println(uid+"---------");

			bi = new BufferedImage[4];
			
			for(int i=0;i<bi.length;i++){
				System.out.println(res[i]+"//////////////");
				bi[i] = ImageIO.read(new URL(refineLink(ProcessReq("gurl "+res[i]))));
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// create the new image, canvas size is the max. of both image sizes
		int w = bi[0].getWidth();
		int h = bi[0].getHeight();
		 combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		for (int i = 0;i<bi.length;i++){
			g.drawImage(bi[i], 0, 0, null);
		}
		
		//resize
		combined = resize(combined, 40, 50);
		result = new BufferedImage(60, 50, BufferedImage.TYPE_INT_ARGB);
		g = result.getGraphics();
		g.drawImage(combined, 10, 0, null);
		/*
		try {
			ImageIO.write(combined, "PNG", new File("../combined.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		if (id == uid){

			newCombined = new BufferedImage(60, 50, BufferedImage.TYPE_INT_ARGB);
	
			// paint both images, preserving the alpha channels
			String sep = File.separator, imagesFolder = ".." + sep + "images" + sep;
			try {
				indicator = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "indicator.png").toURI()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//resize indicator
			indicator = resize(indicator, 60, 50);
			
			g = newCombined.getGraphics();
			g.drawImage(indicator, 0, 0, null);
			g.drawImage(result, 0, 0, null);
			/*
			//resize
			newCombined = resize(newCombined, 40, 50);
			result = new BufferedImage(60, 50, BufferedImage.TYPE_INT_ARGB);
			g = result.getGraphics();
			g.drawImage(newCombined, 10, 0, null);
			*/
			result = newCombined;
		}
		return result;
	}
  public BufferedImage showPlayerName(BufferedImage ice){
	  
	  playerNameBound = nameFont.getStringBounds(playerUser.getUsername(), frc);
	  playerNameW = (int) playerNameBound.getWidth();
	  playerNameH = (int) playerNameBound.getHeight();
	  BufferedImage PlayerName=new BufferedImage(playerNameW,playerNameH, BufferedImage.TYPE_INT_ARGB);
	  Graphics2D g2d = PlayerName.createGraphics();
	  g2d.setColor(Color.CYAN);
	  g2d.setFont(nameFont);
	  g2d.drawString(playerUser.getUsername(), (float) playerNameBound.getX(),(float) -playerNameBound.getY());
	  g2d.dispose();
	  
	  int w =Math.max(playerNameW, 60);
	  int h =50+playerNameH;
	  BufferedImage merged = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	  
	  Graphics g;
	  g =  merged.getGraphics();
	  g.drawImage(ice, (w/2)-30, h-50, null);
	  g.drawImage(PlayerName, 0, 0, null);
	  
	  return merged;
  }
public BufferedImage showName(BufferedImage ice,User u){
	  
	  playerNameBound = nameFont.getStringBounds(u.getIP(), frc);
	  playerNameW = (int) playerNameBound.getWidth();
	  playerNameH = (int) playerNameBound.getHeight();
	  BufferedImage PlayerIP=new BufferedImage(playerNameW,playerNameH, BufferedImage.TYPE_INT_ARGB);
	  Graphics2D g2d = PlayerIP.createGraphics();
	  g2d.setColor(Color.CYAN);
	  g2d.setFont(nameFont);
	  g2d.drawString(u.getIP(), (float) playerNameBound.getX(),(float) -playerNameBound.getY());
	  g2d.dispose();
	  
	  int w =Math.max(playerNameW, 60)+6;
	  int h =50+playerNameH;
	  BufferedImage merged = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	  
	  Graphics g;
	  g =  merged.getGraphics();
	  if(playerNameW > 60){
		  g.drawImage(ice, (w/2)-30, h-50, null);
		  g.drawImage(PlayerIP, 3, 0, null);
	  } else {
		  g.drawImage(ice, 3, h-50, null);
		  g.drawImage(PlayerIP, (w/2)-(playerNameW/2), 0, null);
	  }
	   
	  BufferedImage bufferI = merged;
	  
	  playerNameBound = nameFont.getStringBounds(u.getUsername(), frc);
	  playerNameW = (int) playerNameBound.getWidth();
	  playerNameH = (int) playerNameBound.getHeight();
	  BufferedImage PlayerName=new BufferedImage(playerNameW,playerNameH, BufferedImage.TYPE_INT_ARGB);
	  g2d = PlayerName.createGraphics();
	  g2d.setColor(Color.CYAN);
	  g2d.setFont(nameFont);
	  g2d.drawString(u.getUsername(), (float) playerNameBound.getX(),(float) -playerNameBound.getY());
	  g2d.dispose();
	  
	  w =Math.max(playerNameW, bufferI.getWidth())+6;
	  h =bufferI.getHeight()+playerNameH;
	  merged = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	  g =  merged.getGraphics();
	  if(playerNameW > bufferI.getWidth()){
		  g.drawImage(bufferI, (w/2)-(bufferI.getWidth()/2), h-bufferI.getHeight(), null);
		  g.drawImage(PlayerName, 3, 0, null);
	  } else {
		  g.drawImage(bufferI, 3, h-bufferI.getHeight(), null);
		  g.drawImage(PlayerName, (w/2)-(playerNameW/2), 0, null);
	  }
	  
	  return merged;
  }
  public static BufferedImage resize(BufferedImage image, int width, int height) {
		int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
		} 
  public String removeQuotationMark(String s){
		String r="";
		for (int i=0;i<s.length();i++){
			if (s.charAt(i) != quotationMark){
				r += s.charAt(i);
			}
		}
		return r;
	}
	public  String refineLink(String s){
		System.out.println(s);
		return "http://iceworld.sls-atl.com/"+(s.substring(s.lastIndexOf(":")+2,s.indexOf("/")-1))+(s.substring(s.indexOf("/"),s.lastIndexOf("/")-1))+(s.substring(s.lastIndexOf("/"),s.length()-4));
		//System.out.println("http://iceworld.sls-atl.com/"+(s.substring(s.lastIndexOf(":")+2,s.indexOf("/")-1))+(s.substring(s.indexOf("/"),s.lastIndexOf("/")-1))+(s.substring(s.lastIndexOf("/"),s.length()-4)));
		//return null;
	}
	public  String[] refineGraphicResouces(String s){
		String[] type = {"B001","H008","S019","W044"};
		String [] iLook = new String [4];
		int cCnt;
		int itr;
		
		cCnt=0;
		itr=0;
		for (int i=0;i<s.length();i++){
			if (s.charAt(i) == colon){
				cCnt += 1;
				switch (cCnt) {
	            case 1:
	            	break;
	            case 2:
	            	break;
	            case 3:
	            	itr=i+1;
	            	break;
	            case 4:
	            	iLook[0]= s.substring(itr,i-4);
	            	itr=i+1;
		            break;
	            case 5:
	            	iLook[1]= s.substring(itr,i-4);
	            	itr=i+1;
	               	break;
	            case 6:
	            	iLook[2]= s.substring(itr,i-4);
	            	itr=i+1;
	            	iLook[3]= s.substring(itr,s.length()-4);
	              	break;
	            default:
	            	iLook=null;
	              	break;
				}
			}
		}
		for(int i=0;i<iLook.length;i++){
			//System.out.println(iLook[i]+"++++++++++++++++++++++");
			//System.out.println(uid);
			//System.out.println();
			iLook[i]=removeQuotationMark(iLook[i]);
			if(iLook[i].equalsIgnoreCase("null")){
				iLook[i]=type[i];
			}
		}
		return iLook;
	}
	public  String ProcessReq(String req) throws MalformedURLException, IOException{
		
		String result = "";
		
		if (req.indexOf(' ') == -1){
			URL a = new URL("http://iceworld.sls-atl.com/api/&cmd="+req);
	        URLConnection yc = a.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        
	        while ((inputLine = in.readLine()) != null) {
	            result += inputLine+"\n";
	        }
	        in.close();
		}else{
			result = ProcessReq(req.substring(0,req.indexOf(' ')),req.substring(req.indexOf(' ')+1,req.length()));
		}
		
		return result;
	}
	public  String ProcessReq(String req1, String req2) throws MalformedURLException, IOException{
		String result="";
		String s = "";
		if(req1.equalsIgnoreCase("actions")){
			s=req1+"&from="+req2;
		} else if(req1.equalsIgnoreCase("gresources")){
			s=req1+"&uid="+req2;
		} else if(req1.equalsIgnoreCase("gurl")){
			s=req1+"&gid="+req2;
		}
		
		URL a = new URL("http://iceworld.sls-atl.com/api/&cmd="+s);
      URLConnection yc = a.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
      String inputLine;
      
      while ((inputLine = in.readLine()) != null) {
          result += inputLine+"\n";
      }
      in.close();
		
		return result;
	}
	
	//otherList -> IceTizen
	//otherUserList -> User
	public void removeUser(int UID){
		if(UID != uid){
		int indexCounter=0;
		IceTizen[] i = new IceTizen[otherList.length-1];
		User[] u = new User[otherUserList.length-1];
		for (int n =0 ;n<otherList.length;n++){
			if (otherUserList[n].getUID() != UID){
				i[indexCounter] = otherList[n];
				u[indexCounter] = otherUserList[n];
				indexCounter+=1;
			}
		}
		otherList = i;
		otherUserList = u;
	}
	}
	
	//otherList -> IceTizen
	//otherUserList -> User
	public void addUser(User u){
		if (u.getUID()!=uid){
			
		
		IceTizen[] newOtherList = new IceTizen[otherList.length+1];
		User[] newOtherUserList = new User[otherUserList.length+1];
		for (int n =0 ;n<otherList.length;n++){
			newOtherList[n] = otherList[n];
			newOtherUserList[n] = otherUserList[n];
		}
		
		
		
		//;        //turn user u into icetizen i
		
		//--------------------
		
		newUser = u;
	    Point newUserPoint = this.getPlayfieldGrid().translateGridToPixel(u.getLKDPosX(),u.getLKDPosY());
	    DoublePoint newUserDoublePoint = DoublePoint.fromPoint(newUserPoint);
	    
	    newUserImageHolder = getImage(newUser.getUID());
	    newUserImage=new Image[4];
    	for (int j=0;j<4;j++){
    		newUserImage[j] = newUserImageHolder;
    	}
	    
    	newUserCollection = new MoveableTextureCollection("new User Image" + newUserIndex, newUserImage);
        manager.addCollection(newUserCollection);
	    
	    IceTizen i = new IceTizen(newUserDoublePoint, 1, newUserCollection);	
	    i.setSpeed(halfMeterPerSecond);
	    
	    newUserIndex +=1;
		//--------------------
	    newOtherList[otherList.length]=i;
		newOtherUserList[otherUserList.length]=u;
		otherList = newOtherList;
		otherUserList = newOtherUserList;
	}
	}
	//otherList -> IceTizen
	//otherUserList -> User
	public void moveOldUser(int u, int x, int y){
		if(u != uid){
			for (int i = 0; i<otherList.length;i++){
				if(otherUserList[i].getUID()==u){
					otherList[i].moveTo2(x,y);
				}
			}
		}
	}
	
	public void moveIcetizenTo(Action a){
		int x = Integer.parseInt(a.detail.substring(1,a.detail.indexOf(",")));
		int y = Integer.parseInt(a.detail.substring(a.detail.indexOf(",")+1,a.detail.length()-1));
		for (int i =0;i<otherList.length;i++){
			if(a.getUID() == otherUserList[i].getUID() &&((otherUserList[i].getLKDPosX() != x)||(otherUserList[i].getLKDPosY() != y))){
				otherList[i].moveTo2(x, y);
			}
		}
	}
	public void setTEW(TestEngineWindow t){
		tew=t;
	}
}
