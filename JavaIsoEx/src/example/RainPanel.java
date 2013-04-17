package example;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class RainPanel extends Applet implements Runnable{
  static Point2D.Double[] pts = new Point2D.Double[1200];
  Thread thread;
  public RainPanel() {
    setPreferredSize(new Dimension(1000, 1000));
    setBackground(Color.gray);
    
     thread = new Thread(this);
    thread.start();
  }
  public static void draw1(Graphics g,Dimension d){
	  for (int i = 0; i < pts.length; i++) {
      pts[i] = new Point2D.Double(Math.random(), Math.random());
    }
	  g.setColor(Color.white);
	  for (int i = 0; i < pts.length; i++) {
	      int x = (int)(800*pts[i].x);
	      int y = (int)(800*pts[i].y);
	      int h = (int)(25*Math.random());
	      g.drawLine(x, y, x, y+h);
	    }
		
	}
  
  public void paint(Graphics g) {
	  for (int i = 0; i < pts.length; i++) {
	      pts[i] = new Point2D.Double(Math.random(), Math.random());
	    }
    g.setColor(Color.white);
    for (int i = 0; i < pts.length; i++) {
      int x = (int)(800*pts[i].x);
      int y = (int)(800*pts[i].y);
      int h = (int)(25*Math.random());
      g.drawLine(x, y, x, y+h);
    }
  }
  
  public void run() {
    while(true) {
      for (int i = 0; i < pts.length; i++) {
        double x = pts[i].getX();
        double y = pts[i].getY();
        y += 0.1*Math.random();
        if (y > 1) {
          y = 0.3*Math.random();
          x = Math.random();
        }
        pts[i].setLocation(x, y);
      }
      repaint();
      try {
        Thread.sleep(100);
      } catch (InterruptedException ex) {}
    }
  }
}
