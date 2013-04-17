package example;

import java.awt.Graphics;

import view.Canvas;
import view.EngineWindow;


public class Timer2 extends Thread{
	String cv;
	int x;
	int y;
	public Timer2(String c,int xx,int yy){
		cv = c;
		x=xx;
		y=yy;
	}
	public void run(){
		Graphics g;
		g = EngineWindow.getInstance().getCanvas().getGraphics();
		g.drawString(cv, x, y);
	}
}
