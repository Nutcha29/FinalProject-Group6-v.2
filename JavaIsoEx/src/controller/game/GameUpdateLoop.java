/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import example.Action;
import example.LaunchGame;
import example.ProcessAction;
import example.ProcessState;
import example.Processor;
import example.TestEngineWindow;
import example.Timer2;
import example.User;
import example.newFetchState;
import model.core.particles.ParticleEngine;
import variables.GeneralStatic;
import view.Background;
import view.EngineWindow;
import view.Timer;

/**
 *
 * @author Wouter
 */
public abstract class GameUpdateLoop implements Runnable {

	String sep = File.separator, imagesFolder = ".." + sep + "images" + sep;
	String cWeather;
	BufferedImage weather[];
	String w;
	int counter;
	int nameCounter;
	User[] oldUser;
	User[] newUser;
	boolean shouldStay;
	boolean shouldAdd;
	boolean shouldMove;
	public int REFRESH_INTERVAL=5;
	ProcessAction pa = new ProcessAction();
	Processor p = new Processor();
	ProcessState ps = new ProcessState();
	Action[] currentAction;
	int uid;
	String t;
	newFetchState nfs = new newFetchState();
	
	BufferedImage playerName = null;
	
  private int ticksPerSecond = 0;
  LaunchGame lGame;

  /**
   * Gets the amount of ticks per second that this loop does.
   * @return The amount.
   */
  public int getTicksPerSecond() {
    return ticksPerSecond;
  }

  /**
   * Sets the amount of ticks per second that this loop does. (1 .. 1000, closest
   * value is chosen. May be adjusted on runtime!
   * @param ticksPerSecond The amount.
   */
  public void setTicksPerSecond(int ticksPerSecond) {
    if (ticksPerSecond < 1) ticksPerSecond = 1;
    else if (ticksPerSecond > 1000) ticksPerSecond = 1000;
    this.ticksPerSecond = ticksPerSecond;
  }
public void setLaunchGame(LaunchGame lg){
	lGame = lg;
}
public void setUserList(User[] uList){
	oldUser = uList;
}
public void setUID(int u){
	uid=u;
}
public User[] fetchUser(){
	User[] nUser;
	ProcessState ps = new ProcessState();
	nUser = ps.getAllUserData();
	return nUser;
}
public void setRI(int ri){
	REFRESH_INTERVAL = ri;
	counter = 0;
}
  public void run() {
    while (GeneralStatic.getGameState() != GeneralStatic.STATE_KILL_ENGINE) {
      while (GeneralStatic.getGameState() == GeneralStatic.STATE_RUNNING
              || GeneralStatic.getGameState() == GeneralStatic.STATE_JUST_PARTICLES) {
        double msPerFrame = 1000 / this.getTicksPerSecond();
        Game game = EngineWindow.getInstance().getGame();
        long startUpdateMS = System.currentTimeMillis();
        
        
        // Update units
        
        //System.out.println("555+");
        if (GeneralStatic.getGameState() != GeneralStatic.STATE_JUST_PARTICLES) {
          for (int i = 0; i < game.getMoveableObjects().size(); i++) {
            game.getMoveableObjects().get(i).updatePosition();
          }
        }

        // Update particles
        ParticleEngine.getInstance().updateParticles();


        if (GeneralStatic.getGameState() != GeneralStatic.STATE_JUST_PARTICLES) {
          onUpdate();
        }

        long msWaitedForExecution = System.currentTimeMillis() - startUpdateMS;
        long msToSleep = (long) Math.round(msPerFrame - msWaitedForExecution);
        
        //---------------
        //lGame.printSth(lGame.dummyText);
        //---------------
        
        /* counter = fps
         * from 0 - fps
         * fetch
         * update
         * 
         * Update everystate
         * 
         * reset counter
         */
        /*
       nameCounter += 1;
       if (nameCounter == getTicksPerSecond()){
    	   Graphics gg = lGame.PlayerName.getGraphics();
    	   gg.drawString(lGame.playerUser.getUsername(), lGame.icePlayer.getSquare().getDrawPixelX()-20, lGame.icePlayer.getSquare().getDrawPixelY()-15);  
    	   
    	   Graphics gg1 = TestEngineWindow.getInstance().getCanvas().getGraphics();
    	   gg1.drawImage(lGame.PlayerName, lGame.icePlayer.getSquare().getDrawPixelX()-20, lGame.icePlayer.getSquare().getDrawPixelY()-15, TestEngineWindow.getInstance().getCanvas());
    	   
    	   nameCounter = 0;
       }
        */
        /*
        if (nameCounter == getTicksPerSecond()){
        	Graphics gg1 = lGame.PlayerName.getGraphics();
            gg1.drawString("HELLOOOO", lGame.icePlayer.getSquare().getDrawPixelX(), lGame.icePlayer.getSquare().getDrawPixelY()-20);
        	Graphics g = TestEngineWindow.getInstance().getCanvas().getGraphics();
        	g.drawImage(lGame.PlayerName, lGame.icePlayer.getSquare().getDrawPixelX()-20, lGame.icePlayer.getSquare().getDrawPixelY()-15, null);
        }
        */
        counter +=1;
        
        
        //Graphics gg1 = playerName.getGraphics();
        
        
        //Graphics gg2 = TestEngineWindow.getInstance().getCanvas().getGraphics();
        //gg2.drawString(lGame.playerUser.getUsername(), lGame.icePlayer.getSquare().getDrawPixelX(), lGame.icePlayer.getSquare().getDrawPixelY());
        
        
        if(counter == getTicksPerSecond()*REFRESH_INTERVAL){
        	System.out.println("RIRIRIRIRIRIRI "+REFRESH_INTERVAL+" RIRIRIRIRIRIRI");
			try {
				t = p.ProcessReq("time");
				System.out.println(t);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	currentAction = pa.getActionSinceTime(Integer.parseInt(t.substring(20,t.length()-3))-REFRESH_INTERVAL);
        	System.out.println(Integer.parseInt(t.substring(20,t.length()-3))-REFRESH_INTERVAL);
        
        if(currentAction != null){
        	System.out.println("Have Action");
        for (int i=0;i<currentAction.length;i++){

        	if(currentAction[i].getUID() != uid){
        		switch (currentAction[i].getType()) {
        		case 1:
        			lGame.moveIcetizenTo(currentAction[i]);
        			break;
            	case 2:
            		System.out.print(currentAction[i].getAID()+"\t");
        			System.out.print(currentAction[i].getType()+"\t");
        			System.out.print(currentAction[i].getUID()+"\t");
        			System.out.print(currentAction[i].getTimestamp()+"\t");
        			System.out.print(currentAction[i].getDetail()+"\t");
        			System.out.print("\n");
        			EngineWindow.getInstance().talk(currentAction[i].getDetail());
        			//new Timer2(talktext,lg.icePlayer.getDrawPixelLocation().x,lg.icePlayer.getDrawPixelLocation().y).start();
            		break;
            	case 3:
            		System.out.print(currentAction[i].getAID()+"\t");
        			System.out.print(currentAction[i].getType()+"\t");
        			System.out.print(currentAction[i].getUID()+"\t");
        			System.out.print(currentAction[i].getTimestamp()+"\t");
        			System.out.print(currentAction[i].getDetail()+"\t");
        			System.out.print("\n");
        			
        			
        			EngineWindow.getInstance().getCanvas().startYelling(currentAction[i].getDetail());
            		break;
            	default:
            		break;
        		}
        	}
        }
        } else {
        	System.out.println("No Action");
        }
        newUser = ps.getAllUserData();
        for (int i=0; i<newUser.length;i++){
    		shouldAdd = true;
    		for (int j=0;j<oldUser.length;j++){
    			if(newUser[i].getUID() == oldUser[j].getUID()){
    				shouldAdd = false;
    			}
    		}
    		if(shouldAdd && newUser[i].getUID() != uid && oldUser[i].getUID() != uid){
    			lGame.addUser(newUser[i]);
    		}
    	}
        
        
     // check if this icetizen is logout yet
        
        /*
         * 
         * broken
         * 
         * 
        for (int i=0; i<oldUser.length;i++){
    		shouldStay = false;
    		for (int j=0;j<newUser.length;j++){
    			if(oldUser[i].getUID() == newUser[j].getUID()){
    				shouldStay = true;
    				break;
    			}
    		}
    		if(shouldStay==false && newUser[i].getUID() != uid && oldUser[i].getUID() != uid){
    			lGame.removeUser(i);
    		}
    	}
    	*/
        
        
        
        oldUser = newUser;
        
        
        
        w = nfs.getWeather().getWeather();
        
        if(w.equalsIgnoreCase("Raining")){
        	EngineWindow.getInstance().setBackgroundImage(new Background(weather[0],2));
        	cWeather = w;
        } else if (w.equalsIgnoreCase("Sunny")) {
        	EngineWindow.getInstance().setBackgroundImage(new Background(weather[1],2));
        	cWeather = w;
        } else if (w.equalsIgnoreCase("Snowing")) {
        	EngineWindow.getInstance().setBackgroundImage(new Background(weather[2],2));
        	cWeather = w;
        } else if (w.equalsIgnoreCase("Cloudy")) {
        	EngineWindow.getInstance().setBackgroundImage(new Background(weather[3],2));
        	cWeather = w;
        }
        
        
        
        counter = 0;
        }
        	
        	//Fetch and Update
        	/*
        	newUser = fetchUser();
        	
        	
        	//-------------------------
        	// check if this icetizen is logout yet
        	for (int i=0; i<oldUser.length;i++){
        		shouldStay = false;
        		for (int j=0;j<newUser.length;j++){
        			if(oldUser[i].getUID() == newUser[j].getUID()){
        				shouldStay = true;
        			}
        		}
        		if(shouldStay==false && newUser[i].getUID() != uid && oldUser[i].getUID() != uid){
        			lGame.removeUser(i);
        		}
        	}
        	//-------------------------
        	//check if there's new icetizen
        	
        	for (int i=0; i<newUser.length;i++){
        		shouldAdd = true;
        		for (int j=0;j<oldUser.length;j++){
        			if(newUser[i].getUID() == oldUser[j].getUID()){
        				shouldAdd = false;
        			}
        		}
        		if(shouldAdd && newUser[i].getUID() != uid && oldUser[i].getUID() != uid){
        			lGame.addUser(newUser[i]);
        		}
        	}
        	
        	//-------------------------
        	//get list of all old user and check path then decide to move or not.
        	
        	for (int i=0; i<oldUser.length;i++){
        		shouldMove = false;
        		for (int j=0;j<newUser.length;j++){
        			if(oldUser[i].getUID() == newUser[j].getUID()){
        				if(newUser[i].getLKDPosX()!=oldUser[i].getLKDPosX() && newUser[i].getLKDPosY()!=oldUser[i].getLKDPosY()){
        					shouldMove = true;
        				}
        				if(shouldMove && newUser[i].getUID() != uid && oldUser[i].getUID() != uid){
                			lGame.moveOldUser(newUser[i].getUID(), newUser[i].getLKDPosX(), newUser[i].getLKDPosY());
                		}
        			}
        		}
        		
        	}
        }
        */
        
        if (msToSleep > 0) {
          try {
            Thread.sleep(msToSleep);
          } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
      try {
        Thread.sleep(100);
      } catch (InterruptedException ex) {
        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public abstract void onUpdate();

  public GameUpdateLoop(int ticksPerSecond) {
    this.setTicksPerSecond(ticksPerSecond);
    cWeather = "";
    
    BufferedImage[] weather = new BufferedImage[4];

    	
    	try {
    		weather[0] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "raining.jpg").toURI()));
    		weather[1] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "sunny.jpg").toURI()));
        	weather[2] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "snow.jpg").toURI()));
        	weather[3] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "cloudy.png").toURI()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    //weather = new BufferedImage[4];
    //weather[0] = ImageIO.read(new File(LaunchGame.class.getResource(".." + sep + "images" + sep + "objects" + sep + "alien_right.png").toURI()));
  }
  
  
}
