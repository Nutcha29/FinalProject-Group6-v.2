package example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class yelling extends JPanel {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String pian="";
static int height = 386;
static int width = 1132;
public yelling(String a){
pian=a;
}

public void setText(String s){
	pian = s;
}

public void paint(Graphics g) {
//Dimension d = this.getPreferredSize();
int fontSize = 200;
height = 386;
width = 1132;
setPreferredSize(new Dimension(800,600));

//g.drawRect(0, 0, 800, 600);
g.fillRect(0,0,width,height);

g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

g.setColor(Color.red);
//g.drawString("shit", width, height);
g.drawString(pian, (width/2)-550, (height/2)+100);
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