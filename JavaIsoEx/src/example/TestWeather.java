package example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


public class TestWeather extends JFrame{
	showWeather2 top;
	JLayeredPane layeredPane = new JLayeredPane();
	JPanel bottom= new JPanel();
	showWeather2 bottom1;
	static FetchState fs;
	public TestWeather(){
		setPreferredSize(new Dimension (800,600));
		bottom.setBackground(Color.BLUE);
		setGUI();
		pack();
		setVisible(true);
		
	}
	private void setGUI(){
		//top= new showWeather2("snowing");
		//bottom1 = new showWeather2("snowing");
		
		//top= new showWeather2("raining");
		//bottom1 = new showWeather2("raining");
		
		//top= new showWeather2(fs.getWeather());
		//bottom1 = new showWeather2(fs.getWeather());
		
		
		top= new showWeather2("sunny");
		bottom1 = new showWeather2("sunny");
		
		//top= new showWeather2("cloudy");
		//bottom1 = new showWeather2("cloudy");
		
		layeredPane.add(top, new Integer(2));
		layeredPane.add(bottom1,new Integer(0));
		bottom1.setBounds(0, 0, 2000, 2000);
		top.setOpaque(false);
		//layeredPane.add(getContentPane().add(bottom), new Integer(1));
		this.add(layeredPane,BorderLayout.CENTER );
		top.setBounds(0,0,2000,2000);
		
	}
	public void setTop(String s){
		top.setWeather(s);
	}
	
	public void setBottom(String s){
		bottom1.setWeather(s);
	}
	public static void main(String args[]){
		//fs.start();
		new TestWeather();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	}
}
