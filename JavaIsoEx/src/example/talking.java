package example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class talking extends JPanel {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String pian="";
static int height = 50;
static int width = 120;
public talking(String a){
pian=a;
}

public void setText(String s){
	pian = s;
}

public void paint(Graphics g) {
//Dimension d = this.getPreferredSize();
int fontSize = 10;
height = 50;
width = 120;
setPreferredSize(new Dimension(120,50));

//g.drawRect(0, 0, 800, 600);
g.fillRect(0,0,width,height);

g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

g.setColor(Color.red);
//g.drawString("shit", width, height);
g.drawString(pian, 5, 5);
}

/*
public static void main(String[] args) {
JFrame frame = new JFrame();
frame.getContentPane().add(new yelling("Whatthefuc"));

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(1000,1000);
frame.setVisible(true);
}
*/
}