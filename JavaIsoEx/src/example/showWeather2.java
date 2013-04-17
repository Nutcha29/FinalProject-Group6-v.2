package example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Vector;

import javax.swing.JPanel;


public class showWeather2 extends JPanel implements Runnable{
	String weather="";
	Graphics g;
	Dimension d;
	Thread animationThread;
	int delay=2000;
	public showWeather2(String a){
		weather=a;
		setSize(1000,1000);
		
		setOpaque(false);
		
		
		animationThread = new Thread(this);

		animationThread.start();
		
		
	}
	
	public void setWeather(String s){
		weather=s;
	}

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			repaint();
			try{ 
				
			repaint();
				Thread.sleep(delay); 
				
			}
			catch(InterruptedException e){	e.printStackTrace();
			} 
	}
		
	}
	
	
	public void paintComponent(Graphics g){
		//super.paintComponents(g);
		
		
		//a.draw(g);
		if(weather.equals("snowing")){
			
			snow2.draw1(g,this.getSize());
			
		}
		if(weather.equals("cloudy")){
			
			cloudy.draw1(g,this.getSize());
			
		}
		if(weather.equals("raining")){
			RainPanel.draw1(g,this.getSize());
		}
		if(weather.equals("sunny")){
			sunny.draw1(g, this.getSize());
			
		}
		
		
		/*if(a.equals("rain")){
			Rain.main()
		}*/
	
	}
}

