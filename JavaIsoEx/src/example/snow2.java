package example;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class snow2 extends Applet implements Runnable{

	Thread ani;
	
	public snow2(){
		setSize(500,500);
		ani = new Thread(this);
		ani.start();
	}
	
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		Dimension d = this.getSize();
		
		for(int i = 0;i<200;i++){
			int x = (int)(Math.random()*d.width);
			int y = (int)(Math.random()*d.height);
			
			g.setColor(Color.WHITE);
			g.fillOval(x,y,10,10);
		}
		
	}
	
	public static void draw1(Graphics g,Dimension d){
		for(int i = 0;i<200;i++){
			int x = (int)(Math.random()*d.width);
			int y = (int)(Math.random()*d.height);
			
			g.setColor(Color.WHITE);
			g.fillOval(x,y,10,10);
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
