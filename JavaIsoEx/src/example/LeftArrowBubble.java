package example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
/**
 * @author harsh
 */
public class LeftArrowBubble extends JPanel {
	private static final long serialVersionUID = -5389178141802153305L;
	private static int radius = 10;
	private static int arrowSize = 12;
	private static int strokeThickness = 3;
	private int padding = strokeThickness / 2;
	private static String j;
	Font font = new Font("Arial", Font.PLAIN, 12);

	public LeftArrowBubble(String s){
		j=s;

	}


	protected void paintComponent(Graphics g) {

		final Graphics2D g2d = (Graphics2D) g;
		
		int x = padding + strokeThickness + arrowSize;
		int width = getWidth() - arrowSize - (strokeThickness * 2);
		int bottomLineY = getHeight() - strokeThickness;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		
		//g2d.drawString(j,0,0);
		g2d.setColor(new Color(0.5f, 0.8f, 1f));
		g2d.fillRect(x, padding, width, bottomLineY);
		g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2d.setStroke(new BasicStroke(strokeThickness));
		RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, padding, width, bottomLineY, radius, radius);
		Polygon arrow = new Polygon();
		arrow.addPoint(20, 8);
		arrow.addPoint(0, 10);
		arrow.addPoint(20, 12);
		Area area = new Area(rect);
		area.add(new Area(arrow));

		g2d.draw(area);	
		
	
		
		Graphics2D yo = (Graphics2D) g;  
		  
		//String words = "Hello!!!";  
		  
		yo.setColor(Color.black);  
		  
		Font textFont2 = new Font("Arial", Font.BOLD, 16);  
		FontMetrics textMetrics = yo.getFontMetrics(textFont2);  
		yo.setFont(textFont2);  
		  
		int centeredX = (this.getWidth()/2) - (textMetrics.stringWidth(j)/2);  
		int centeredY = (this.getHeight()/2) + (textMetrics.getHeight()/2);  
		  
		yo.drawString(j, centeredX, centeredY); 

	}

	//public static void main(String s[]) {
	public void run(){
		JFrame frame1 = new JFrame("2D Text");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame1.getContentPane().add("Center", new J2D());
		myPnl pn = new myPnl(radius,arrowSize,strokeThickness,j);
		frame1.setLayout(null);
		pn.setBackground(Color.GREEN);
		//getContentPane().add(pn);

		frame1.pack();
		frame1.setSize(new Dimension(500, 300));
		frame1.setVisible(true);
	}

}
class myPnl extends JPanel{
	private int r;
	private int a;
	private int s;
	private int p;
	String st;
	public myPnl(int rr,int aa,int ss, String str){
		r=rr;
		a=aa;
		s=ss;
		p=s/2;
		st=str;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		Font font = new Font("TimesRoman", Font.BOLD,15);
		String str1 = new String("Pian");
		TextLayout tl = new TextLayout(str1, font, frc);
		g2d.setFont(font);
		g2d.setColor(new Color(0.5f, 0.8f, 1f));
		tl.draw(g2d, 70, 150);
		int x = p + s + a;
		int width = getWidth() - a - (s * 2);
		int bottomLineY = getHeight() - s;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		g2d.drawString(st,0,0);
		g2d.setColor(new Color(0.5f, 0.8f, 1f));
		g2d.fillRect(x, p, width, bottomLineY);
		g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2d.setStroke(new BasicStroke(s));
		RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, p, width, bottomLineY, r, r);
		Polygon arrow = new Polygon();
		arrow.addPoint(20, 8);
		arrow.addPoint(0, 10);
		arrow.addPoint(20, 12);
		Area area = new Area(rect);
		area.add(new Area(arrow));

		g2d.draw(area);
		
		Graphics2D g2D;
		g2D = (Graphics2D) g;
		FontRenderContext frc1 = g2D.getFontRenderContext();
		Font font1 = new Font("Courier", Font.BOLD, 24);
		String str2 = new String("Java");
		TextLayout tle = new TextLayout(str2, font1, frc1);
		g2D.setColor(Color.gray);
		tle.draw(g2D, 70, 150);

	}}
