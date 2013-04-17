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


public class cloudy extends Applet implements Runnable{
	static BufferedImage cloudy=null;
	Thread ani;
	static int x = (int)(Math.random()*800);
	public cloudy(){
		setSize(800,800);
		try {
			cloudy = ImageIO.read(new File ("cloud3.png"));
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
		
		int y =100;
		for(int i = 0;i<10;i++){
			//
			x++;
			if(x>d.width-300){
				x=1;
			}
		
		/*for(int i = 0;i<10;i++){
			int x = (int)(Math.random()*d.width);
			int y = (int)(Math.random()*d.height);*/
			
			g.drawImage(cloudy, x+80, 120, 205, 125, null);
			g.drawImage(cloudy, x-600, 80, 205, 125, null);
			g.drawImage(cloudy, x-40, 50, 205, 125, null);
			g.drawImage(cloudy, x, 630, 205, 125, null);
			g.drawImage(cloudy, x-200, 150, 205, 125, null);
			g.drawImage(cloudy, x+300, 30, 205, 125, null);
			g.drawImage(cloudy, x-100, 20, 205, 125, null);
			g.drawImage(cloudy, x+400, 650, 205, 125, null);
			g.drawImage(cloudy, x+25, 600, 205, 125, null);
			g.drawImage(cloudy, x-650, 590, 205, 125, null);
			
		}
		
	}
	
	public static void draw1(Graphics g,Dimension d){
		BufferedImage cloudy1=null;
		try {
		cloudy1 = ImageIO.read(new File ("cloud3.png"));
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
		
		/*for(int i = 0;i<10;i++){
			int x = (int)(Math.random()*d.width);
			int y = (int)(Math.random()*d.height);*/
			
			g.drawImage(cloudy1, x+80, 120, 205, 125, null);
			g.drawImage(cloudy1, x-600, 80, 205, 125, null);
			g.drawImage(cloudy1, x-40, 50, 205, 125, null);
			g.drawImage(cloudy1, x, 630, 205, 125, null);
			g.drawImage(cloudy1, x-200, 150, 205, 125, null);
			g.drawImage(cloudy1, x+300, 30, 205, 125, null);
			g.drawImage(cloudy1, x-100, 20, 205, 125, null);
			g.drawImage(cloudy1, x+400, 650, 205, 125, null);
			g.drawImage(cloudy1, x+25, 600, 205, 125, null);
			g.drawImage(cloudy1, x-650, 590, 205, 125, null);
		}
			
		
	}
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}

