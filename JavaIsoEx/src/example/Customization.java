package example;

import iceworld.given.ICEWorldImmigration;
import iceworld.given.IcetizenLook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
public class Customization {
	static IcetizenLook look2 = new IcetizenLook();
	static IcetizenLoader all;
	static Myicetz cust;
	static ICEWorldImmigration immigration;
	static JFrame a;
	public static JFrame showIcetizen(){
		all=new IcetizenLoader();
		a=new JFrame("Customization");
		a.setContentPane(all);
		a.setPreferredSize(new Dimension(500,500));
		return a;

	}

	public static void customizedScreen( Myicetz cust2,  ICEWorldImmigration immigration2){
		cust=cust2;
		immigration=immigration2;
		try {
			all.getresources();
			all.setIndex(cust);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		final JFrame customization = new JFrame("IceTizen Customization");
		customization.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke) {
				int key = ke.getKeyCode();
				if(key == KeyEvent.VK_ESCAPE){
					//System.out.println("escape pressed on about");
					customization.setVisible(false);
					customization.dispose();
				}
			}
		});

		customization.setLayout(new BorderLayout());
		TitledBorder lookHeader = new TitledBorder("Your look");
		JPanel customPanel = new JPanel();
		JPanel look=(JPanel) showIcetizen().getContentPane();
		a.dispose();
		
		look.setBorder(lookHeader);
		
		customization.add(look, BorderLayout.CENTER);
		JPanel customControl = new JPanel();
		customPanel.setBackground(Color.WHITE);

		customization.add(customControl, BorderLayout.EAST);
		customControl.setLayout(new GridLayout(4,1,2,2));
		customControl.setBackground(Color.GRAY);

		JPanel lowerButtonPanel = new JPanel();
		customization.add(lowerButtonPanel, BorderLayout.SOUTH);
		JButton saveIceTizen = new JButton("Save");
		saveIceTizen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				/*tester = new Myicetz();
				tester.setIcePortID(255);
				tester.setListeningPort(10011);
					ICEWorldImmigration immigration = new ICEWorldImmigration((iceworld.given.MyIcetizen) tester);*/
				look2.gidB = all.bodys[all.bC] ;
				look2.gidS = all.shirts[all.sC];
				look2.gidH = all.heads[all.hC];
				look2.gidW = all.weapons[all.wC];
				System.out.println(look2.gidB);
				System.out.println(look2.gidS);
				System.out.println(look2.gidH);
				System.out.println(look2.gidW);
				cust.setIcetizenLook(look2);
				immigration.customization(look2);
				customization.setVisible(false);
				//customization.dispose();
			}
		});
		JButton cancelCustomization = new JButton("Cancel");
		lowerButtonPanel.add(saveIceTizen);
		lowerButtonPanel.add(cancelCustomization);
		lowerButtonPanel.setBackground(Color.GRAY);

		//saveIceTizen
		cancelCustomization.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				customization.setVisible(false);
				customization.dispose();
			}
		});

		JPanel Body = new JPanel();
		Body.setLayout(new GridLayout(2,1,1,1));
		JPanel Shirt = new JPanel();
		Shirt.setLayout(new GridLayout(2,1,1,1));
		JPanel Headgear = new JPanel();
		Headgear.setLayout(new GridLayout(2,1,1,1));
		JPanel Weapon = new JPanel();
		Weapon.setLayout(new GridLayout(2,1,1,1));
		JPanel BodyTextPanel = new JPanel();
		JPanel ShirtTextPanel = new JPanel();
		JPanel HeadgearTextPanel = new JPanel();
		JPanel WeaponTextPanel = new JPanel();
		JPanel BodyButtonPanel = new JPanel();
		BodyButtonPanel.setLayout(new GridLayout(1,2));
		JPanel ShirtButtonPanel = new JPanel();
		ShirtButtonPanel.setLayout(new GridLayout(1,2));
		JPanel HeadgearButtonPanel = new JPanel();
		HeadgearButtonPanel.setLayout(new GridLayout(1,2));
		JPanel WeaponButtonPanel = new JPanel();
		WeaponButtonPanel.setLayout(new GridLayout(1,2));
		JPanel BodyButtonPanelNext = new JPanel();
		JPanel BodyButtonPanelPrev = new JPanel();
		JPanel ShirtButtonPanelNext = new JPanel();
		JPanel ShirtButtonPanelPrev = new JPanel();
		JPanel HeadgearButtonPanelNext = new JPanel();
		JPanel HeadgearButtonPanelPrev = new JPanel();
		JPanel WeaponButtonPanelNext = new JPanel();
		JPanel WeaponButtonPanelPrev = new JPanel();
		customControl.add(Body);
		customControl.add(Shirt);
		customControl.add(Headgear);
		customControl.add(Weapon);
		Body.add(BodyTextPanel);
		Body.add(BodyButtonPanel);
		Shirt.add(ShirtTextPanel);
		Shirt.add(ShirtButtonPanel);
		Headgear.add(HeadgearTextPanel);
		Headgear.add(HeadgearButtonPanel);
		Weapon.add(WeaponTextPanel);
		Weapon.add(WeaponButtonPanel);

		JLabel BodyLabel = new JLabel("Body Type");
		BodyLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		JLabel ShirtLabel = new JLabel("Shirt Type");
		ShirtLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		JLabel HeadgearLabel = new JLabel("Headgear Type");
		HeadgearLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		JLabel WeaponLabel = new JLabel("Weapon Type");
		WeaponLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		BodyTextPanel.add(BodyLabel);
		ShirtTextPanel.add(ShirtLabel);
		HeadgearTextPanel.add(HeadgearLabel);
		WeaponTextPanel.add(WeaponLabel);

		BodyButtonPanel.add(BodyButtonPanelPrev);
		BodyButtonPanel.add(BodyButtonPanelNext);
		ShirtButtonPanel.add(ShirtButtonPanelPrev);
		ShirtButtonPanel.add(ShirtButtonPanelNext);
		HeadgearButtonPanel.add(HeadgearButtonPanelPrev);
		HeadgearButtonPanel.add(HeadgearButtonPanelNext);
		WeaponButtonPanel.add(WeaponButtonPanelPrev);
		WeaponButtonPanel.add(WeaponButtonPanelNext);

		JButton BodyNext = new JButton(">>");
		BodyNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int a =all.bC;
				if(a==IcetizenLoader.bodys.length-1){
					all.bC=0;
				}else{
				all.bC=(all.bC+1);}
				all.repaint();
				
				
			}
		});
		JButton BodyPrev = new JButton("<<");
		BodyPrev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				all.repaint();
				int a =all.bC;
				if(a==0){
					all.bC=IcetizenLoader.bodys.length;
				}
				all.bC=(all.bC-1);
				
				
			}
		});
		JButton ShirtNext = new JButton(">>");
		ShirtNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int a =all.sC;
				if(a==IcetizenLoader.shirts.length-1){
					all.sC=0;
				}else{
				all.sC=(all.sC+1);}
				all.repaint();
				
			}
		});
		JButton ShirtPrev = new JButton("<<");
		ShirtPrev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				all.repaint();
				int a =all.sC;
				if(a==0){
					all.sC=IcetizenLoader.shirts.length;
				}
				all.sC=(all.sC-1);
				
				
			}
		});
		JButton HeadgearNext = new JButton(">>");
		HeadgearNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int a =all.hC;
				if(a==IcetizenLoader.heads.length-1){
					all.hC=0;
				}else{
				all.hC=(all.hC+1);}
				all.repaint();
				
			}
		});
		JButton HeadgearPrev = new JButton("<<");
		HeadgearPrev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				all.repaint();
				int a =all.hC;
				if(a==0){
					all.hC=IcetizenLoader.heads.length;
				}
				all.hC=(all.hC-1);
				
				
			}
		});
		JButton WeaponNext = new JButton(">>");	
		WeaponNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int a =all.wC;
				System.out.println(a);
				System.out.println(IcetizenLoader.weapons.length-1);
				if(a==IcetizenLoader.weapons.length-1){
					all.wC=0;	
				}else{
				
				all.wC=(all.wC+1);
				System.out.println(all.wC);}
				all.repaint();
			}
		});
		JButton WeaponPrev = new JButton("<<");
		WeaponPrev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				all.repaint();
				int a =all.wC;
				if(a==0){
					all.wC=IcetizenLoader.weapons.length;
				}
				all.wC=(all.wC-1);
				
				
			}
		});

		BodyButtonPanelNext.add(BodyNext);
		BodyButtonPanelPrev.add(BodyPrev);
		ShirtButtonPanelNext.add(ShirtNext);
		ShirtButtonPanelPrev.add(ShirtPrev);
		HeadgearButtonPanelNext.add(HeadgearNext);
		HeadgearButtonPanelPrev.add(HeadgearPrev);
		WeaponButtonPanelNext.add(WeaponNext);
		WeaponButtonPanelPrev.add(WeaponPrev);

		customization.setPreferredSize(new Dimension(600,600));
		customization.pack();
		customization.setVisible(true);
	}



	


	public static void main(String[] args){

		//	customizedScreen();
	
	}
}









