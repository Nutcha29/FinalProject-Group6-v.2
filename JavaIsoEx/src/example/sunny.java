package example;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class sunny extends Applet implements Runnable{
	static BufferedImage sunnycloud=null;
	static BufferedImage sun = null;
	Thread ani;
	static int x = (int)(Math.random()*800);
	public sunny(){
		setSize(800,800);
		try {
			sunnycloud = ImageIO.read(new File ("sunnycloud.png"));
			sun = ImageIO.read(new File ("sunn.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ani = new Thread(this);
		ani.start();	
		
	}
	
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		Dimension d = this.getSize();
		
		/*for(int i = 0;i<10;i++){
			int x = (int)(Math.random()*d.width);
			int y = (int)(Math.random()*d.height);
			
			g.drawImage(sunnycloud, x, y, 205, 125, null);
		}}
		int fixX=100;
		int y=100;
		for(int i =0; i<300;i++){
			y++;
			g.drawImage(sun, fixX, y, 250, 150, null);
		
		}*/
	
		int y =100;
		for(int i = 0;i<10;i++){
			//
			x++;
			if(x>d.width-200){
				x=1;
			}
			g.drawImage(sunnycloud, x-600, 80, 275, 148, null);
			g.drawImage(sunnycloud, x-40, 50, 275, 148, null);
			g.drawImage(sunnycloud, x, 630, 275, 148, null);
			g.drawImage(sunnycloud, x-200, 150, 275, 148, null);
			g.drawImage(sunnycloud, x+300, 30, 275, 148, null);
			g.drawImage(sunnycloud, x-100, 20, 275, 148, null);
			g.drawImage(sunnycloud, x+400, 650, 275, 148, null);
			g.drawImage(sunnycloud, x+25, 600, 275, 148, null);
			g.drawImage(sunnycloud, x-650, 590, 275, 148, null);
			g.drawImage(sun, x, y, 223, 193, null);
		}
	}
	
	public static void draw1(Graphics g,Dimension d){
		 BufferedImage sunnycloud=null;
		BufferedImage sun = null;
		try {
			sunnycloud = ImageIO.read(new File ("sunnycloud.png"));
			sun = ImageIO.read(new File ("sunn.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int y =100;
		for(int i = 0;i<10;i++){
			//
			x++;
			if(x>d.width-10){
				x=1;
			}
			g.drawImage(sunnycloud, x-600, 80, 275, 148, null);
			g.drawImage(sunnycloud, x-40, 50, 275, 148, null);
			g.drawImage(sunnycloud, x, 630, 275, 148, null);
			g.drawImage(sunnycloud, x-200, 150, 275, 148, null);
			g.drawImage(sunnycloud, x+300, 30, 275, 148, null);
			g.drawImage(sunnycloud, x-100, 20, 275, 148, null);
			g.drawImage(sunnycloud, x+400, 650, 275, 148, null);
			g.drawImage(sunnycloud, x+25, 600, 275, 148, null);
			g.drawImage(sunnycloud, x-650, 590, 275, 148, null);
			g.drawImage(sun, x, y, 223, 193, null);
		}
		
	}
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}

