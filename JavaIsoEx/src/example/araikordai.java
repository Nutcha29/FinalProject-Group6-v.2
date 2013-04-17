package example;
import iceworld.given.ICEWorldImmigration;


import java.applet.Applet;

import java.awt.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import java.awt.event.ActionListener;




public class araikordai extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {URL a;
			 
				String result="";
					araikordai frame = new araikordai();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Toolkit toolkit = Toolkit.getDefaultToolkit(); // get screen size
	public Dimension screensize = toolkit.getScreenSize();
	private JTextField textField;
	private JTextField textField_1;
	public static JSlider slider;
	public static ICEWorldImmigration immigration;
	//Sound soundBG,soundSE;-------
	static int sound;
	//final Myicetz tester, final ICEWorldImmigration immigration
	public araikordai(final Myicetz tester, final ICEWorldImmigration immigration) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screensize.height=800;
		screensize.width=1440;
		setPreferredSize(screensize);
		
		String result="";
		
	//	tester.setIcePortID(255);
		//tester.setListeningPort(10011);
		
	//immigration = new ICEWorldImmigration((iceworld.given.MyIcetizen) tester);
	//setBounds(100, 100, 450, 300);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1149, 570, 275, 273);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(24, 20, 229, 229);
		panel_1.add(panel_2);
		
		JLabel lblMiniMap = new JLabel("MINI MAP");
		lblMiniMap.setBounds(24, 6, 61, 16);
		panel_1.add(lblMiniMap);
		JPanel bottomtools = new JPanel();
		bottomtools.setBackground(Color.MAGENTA);
		//76 24
		bottomtools.setBounds(0, (int) (screensize.height*0.795), screensize.width, (int) (screensize.height*0.205));
		getContentPane().add(bottomtools);
		bottomtools.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(17, 11, 486, 121);
		bottomtools.add(panel);
		panel.setLayout(null);
		
		JLabel lblChatArea = new JLabel("Background music:");
		lblChatArea.setBounds(26, 22, 128, 16);
		panel.add(lblChatArea);
		
		
		slider = new JSlider();
		slider.setBounds(16, 35, 190, 29);
		SliderHanderler a=new SliderHanderler();
		slider.addChangeListener(a);
		panel.add(slider);
		
		JLabel lblSetting = new JLabel("SETTING:");
		lblSetting.setBounds(6, 6, 61, 16);
		panel.add(lblSetting);
		
		JLabel lblSoundEffect = new JLabel("Sound Effect:");
		lblSoundEffect.setBounds(250, 22, 128, 16);
		panel.add(lblSoundEffect);
		
		JSlider slider_1 = new JSlider();
		slider_1.setBounds(236, 35, 190, 29);
		panel.add(slider_1);
		
		JLabel lblRefreshInterval = new JLabel("Refresh Interval>>");
		lblRefreshInterval.setBounds(26, 69, 118, 16);
		panel.add(lblRefreshInterval);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(144, 65, 52, 27);
		panel.add(comboBox);
		
		JLabel label_1 = new JLabel("Talk Visible Duration>>");
		label_1.setBounds(246, 69, 168, 16);
		panel.add(label_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(401, 65, 52, 27);
		panel.add(comboBox_1);
		
		textField = new JTextField(100);
		textField.setBounds(508, 18, 554, 34);
		bottomtools.add(textField);
		textField.setColumns(10);
		
		
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(508, 64, 554, 34);
		bottomtools.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTalk = new JLabel("TALK:");
		lblTalk.setBounds(508, 0, 43, 25);
		bottomtools.add(lblTalk);
		
		
		JLabel label = new JLabel("YELL:");
		label.setBounds(508, 46, 43, 25);
		bottomtools.add(label);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				immigration.logout();
				dispose();
			}
		});
		btnLogOut.setBounds(686, 98, 133, 34);
		bottomtools.add(btnLogOut);
		
		JButton btnCustomization = new JButton("Customization");
		btnCustomization.setBounds(515, 98, 164, 34);
		bottomtools.add(btnCustomization);
		btnCustomization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customization custom = new Customization();
				//custom.customizedScreen(tester,immigration);-------------
			}
		});
		
		JButton btnTalk = new JButton("TALK");
		btnTalk.setBounds(1055, 24, 76, 25);
		bottomtools.add(btnTalk);
		btnTalk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String talktext = textField.getText();
				if(talktext.length()>100){
					textField.setText("No more than 100 Characters!");
				}else{
				immigration.talk(talktext);
				textField.setText("");}
			}
		});
		
		JButton btnYell = new JButton("YELL");
		btnYell.setBounds(1055, 70, 76, 25);
		bottomtools.add(btnYell);
		btnYell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String yelltext = textField_1.getText();
				if(yelltext.length()>10){
					/*JDialog a = new JDialog();
					a.setVisible(true);
					a.pack();*/
					textField_1.setText("No more than 10 Characters!");
				}else{
				immigration.yell(yelltext);
				textField_1.setText("");}
			}
		});
		setListeners();
		pack();
		//soundBG = new Sound("jasound.wav");---------
	}

	


	private void setListeners()  
    {  
        // Prevents user from typing more charecters then specified in the database  
        textField.addKeyListener(new KeyAdapter() {  
            public void keyTyped(KeyEvent e) {  
                String text = textField.getText();  
                int length = text.length();  
                if (length == 100) {  
                    e.consume();  
                } else if (length > 100) {  
                    System.err.println("You can't talk more than 100 characters!!");  
                }  
            }  
        });  
        
        textField_1.addKeyListener(new KeyAdapter() {  
            public void keyTyped(KeyEvent e) {  
                String text = textField.getText();  
                int length = text.length();  
                if (length == 10) {  
                    e.consume();  
                } else if (length > 10) {  
                    System.err.println("You can't yell more than 10 charracters!!");  
                    textField_1.setText("You can't type more than 10 characters.");
                }  
            }  
        });  
        
    }


	public class SliderHanderler implements ChangeListener {
        public void stateChanged(ChangeEvent e){
        		//System.out.println("Change value");
                sound = (int)(slider.getValue());
                slider.setValue(sound);
                if(sound<50){
                	//soundBG.decrease(sound);------
                }else{
                //soundBG.increase(sound);}-------
                
               
        	}      
                
               
        }
}
}

