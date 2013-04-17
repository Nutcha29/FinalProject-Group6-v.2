/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ButtonListener;
import controller.game.Game;
import iceworld.given.ICEWorldImmigration;

import java.awt.Color;
import view.menu.MenuGroup;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import example.Customization;
import example.LeftArrowBubble;
import example.Myicetz;
import example.Sound;
import example.Sound2;
import example.araikordai;
import example.araikordai.SliderHanderler;
import example.yelling;

import model.core.Grid;

/**
 *
 * @author Wouter
 */
public class Canvas extends JPanel {

	
	public Myicetz tester;
	public static JSlider slider_1;
	public static ICEWorldImmigration immigration;
	Sound soundBG;
	Sound2 soundSE;
	Timer tt;

	static int refreshInterval=1;
	static int talkVisible=1;
	
	public yelling yl = new yelling("");
	
  public JButton zoomInBtn, zoomOutBtn;
  private JLabel fpsLbl, squareDrawCountLbl;
  private boolean currentlyRepainting = false;
  private Rectangle viewport = new Rectangle();
  private LoadingScreen loadingScreen = null;

  public Toolkit toolkit = Toolkit.getDefaultToolkit(); // get screen size
  public Dimension screensize = toolkit.getScreenSize();
  
  private JTextField textField;
  private JTextField textField_1;
  public static JSlider slider;
  //public static ICEWorldImmigration immigration;
	//Sound soundBG,soundSE;-------
	static int sound;
  /**
   * Gets the loadingscreen of the canvas.
   * @return The loadingscreen.
   */
  public LoadingScreen getLoadingScreen() {
    return loadingScreen;
  }

  /**
   * Sets the loadingscreen of the canvas.
   * @param loadingScreen The new loadingscreen.
   */
  private void setLoadingScreen(LoadingScreen loadingScreen) {
    this.loadingScreen = loadingScreen;
  }

  /**
   * Sets the FPS of this
   * @param fps
   */
  public void setFPS(double fps) {
    this.fpsLbl.setText("FPS: " + fps);
  }

  /**
   * Sets the amount of squares drawn this frame.
   * @param squaresDrawn The amount.
   */
  public void setSquaresDrawn(int squaresDrawn) {
    this.squareDrawCountLbl.setText("Squares drawn: " + squaresDrawn);
  }

  /**
   * Gets the visible rectangle that is only updated once per frame, instead
   * of every call to the function getVisibleRectangle();
   * @return The Rectangle.
   */
  public Rectangle getViewPort() {
    return viewport;
  }

  /**
   * Sets the visible rectangle of this panel. This is used for calculating
   * the visible rectangle once per frame, rather than once per call.
   * @param viewport The new visible rect.
   */
  public void setViewPort(Rectangle viewport) {
    this.viewport = viewport;
  }

  public boolean isCurrentlyRepainting() {
    return currentlyRepainting;
  }

  public void setCurrentlyRepainting(boolean currentlyRepainting) {
    this.currentlyRepainting = currentlyRepainting;
  }

  public JButton getZoomInBtn() {
    return zoomInBtn;
  }
public void startYelling(String yelltext){
	yl.setText(yelltext);
	tt = new Timer(EngineWindow.getInstance().getCanvas());
	tt.start();
}
  public JButton getZoomOutBtn() {
    return zoomOutBtn;
  }

  public void addZoomButtons() {
    String sep = File.separator;
    //".." + sep + "images" + sep + "objects" + sep +
    //String path = System.getProperty("user.home") + sep +  "wofje-images" + sep + "icons" + sep;
    String path = ".." + sep + "images" + sep + "objects" + sep + "icons" + sep;
    ImageIcon zoomIn = null;
    ImageIcon zoomOut = null;
    try {
     zoomIn = new ImageIcon(new File(path + "ic_zoom_in.png").toURI().toURL());
     zoomOut = new ImageIcon(new File(path + "ic_zoom_out.png").toURI().toURL());
    } catch (Exception e) {
      e.printStackTrace();
    }
    //zoomInBtn = new JButton(zoomIn);
    zoomInBtn = new JButton("+");
    zoomInBtn.setBounds(25, 50, 50, 50);
    zoomInBtn.addActionListener(new ButtonListener(2));
    zoomInBtn.setFocusable(false);
    add(zoomInBtn);
    //zoomInBtn.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK),"plus");
    //zoomInBtn.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD,KeyEvent.CTRL_DOWN_MASK), "zoom in");
    
    //zoomOutBtn = new JButton(zoomOut);
    zoomOutBtn = new JButton("-");
    zoomOutBtn.setBounds(25, 110, 50, 50);
    zoomOutBtn.addActionListener(new ButtonListener(1));
    zoomOutBtn.setFocusable(false);
    add(zoomOutBtn);
    zoomOutBtn.setEnabled(false);
    //zoomOutBtn.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,KeyEvent.CTRL_DOWN_MASK),"zoom out");
    //zoomOutBtn.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,KeyEvent.CTRL_DOWN_MASK),"zoom out");
    /*
    JTextArea dummyText = new JTextArea();
    dummyText = new JTextArea();
    dummyText.setBounds(0, (int) (this.getSize().height*0.8), this.getSize().width, (int) (this.getSize().height*0.2));
    //dummyText.addActionListener(new ButtonListener(1));
    //dummyText.setFocusable(false);
    add(dummyText);
     */
    
    Border outline = BorderFactory.createLineBorder(Color.black);
    //araikordai dummyText = new araikordai();
    /*
    dummyText = new JPanel();
    dummyText.setBackground(Color.WHITE);
    dummyText.setBorder(outline);
    dummyText.setBounds(0, (int) (this.getSize().height*0.94), this.getSize().width, (int) (this.getSize().height*0.06));
    */
    //dummyText.setBounds(0, (int) (this.getSize().height*0.8), this.getSize().width, (int) (this.getSize().height*0.2));
    //dummyText.addActionListener(new ButtonListener(1));
    //dummyText.setFocusable(false);
    //------------------------------------
    
    JPanel panel_1 = new JPanel();
	panel_1.setBounds(1148, 487, 275, 273);
	add(panel_1);
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
	bottomtools.setBounds(0, (int) (screensize.height*0.70), screensize.width, (int) (screensize.height*0.30));
	add(bottomtools);
	bottomtools.setLayout(null);
	
	JPanel panel = new JPanel();
	panel.setBounds(17, 11, 486, 121);
	bottomtools.add(panel);
	panel.setLayout(null);
	
	JLabel lblChatArea = new JLabel("Background music:");
	lblChatArea.setBounds(11, 22, 128, 16);
	panel.add(lblChatArea);
	
	
	slider = new JSlider(JSlider.HORIZONTAL, -15, 5, 0);
	slider.setMajorTickSpacing(5);
	slider.setPaintTicks(true);
	slider.setBounds(16, 35, 190, 29);
	SliderHanderler a=new SliderHanderler();
	slider.addChangeListener(a);


	//TODO
	yl.setBounds(0, (int) screensize.getHeight()/4,(int)  screensize.getWidth(),(int)  screensize.getHeight()/2);
	add(yl);
	
	yl.setVisible(false);
	
	panel.add(slider);
	
	JLabel lblSetting = new JLabel("SETTING:");
	lblSetting.setBounds(6, 6, 61, 16);
	panel.add(lblSetting);
	
	JLabel lblSoundEffect = new JLabel("Sound Effect:");
	lblSoundEffect.setBounds(250, 22, 128, 16);
	panel.add(lblSoundEffect);
	
	slider_1 = new JSlider(JSlider.HORIZONTAL, -35, 5, 0);
	slider_1.setMajorTickSpacing(5);
	slider_1.setPaintTicks(true);
	slider_1.setBounds(236, 35, 190, 29);
	panel.add(slider_1);
	//SliderHanderler2 b = new SliderHanderler2();
	//slider_1.addChangeListener(b);
	
	JLabel lblRefreshInterval = new JLabel("Refresh Interval>>");
	lblRefreshInterval.setBounds(26, 69, 118, 16);
	panel.add(lblRefreshInterval);
	
	String range[]={"1","2","3","4","5","6","7","8","9","10"};
	final JComboBox comboBox = new JComboBox(range);
	comboBox.setBounds(144, 65, 65, 27);
	panel.add(comboBox);
	comboBox.addItemListener(new ItemListener(){
		  public void itemStateChanged(ItemEvent ie){
		  String str = (String)comboBox.getSelectedItem();
		  //tew.setRI(Integer.parseInt(str));
		  EngineWindow.getInstance().setRI(Integer.parseInt(str));
		  }
		  });
	
	JLabel label_1 = new JLabel("Talk Visible Duration>>");
	label_1.setBounds(246, 69, 168, 16);
	panel.add(label_1);
	
	final JComboBox comboBox_1 = new JComboBox(range);
	comboBox_1.setBounds(401, 65, 65, 27);
	panel.add(comboBox_1);
	
	/*JButton btnApply = new JButton("-");
	btnApply.setBounds(130, 17, 49, 28);
	panel.add(btnApply);
	
	JButton button = new JButton("+");
	button.setBounds(174, 17, 49, 28);
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			soundBG.increase();
			System.out.println("+");
	}
});
	panel.add(button);
	// int sound = (int)(slider.getValue());
	btnApply.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				soundBG.decrease();
				System.out.println("-");
		}
	});
*/
	comboBox_1.addItemListener(new ItemListener(){
		  public void itemStateChanged(ItemEvent ie){
		  String str = (String)comboBox_1.getSelectedItem();
		  talkVisible=Integer.parseInt(str);
		  }
		  });
	
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
			
			{
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit?", "User Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION)
					immigration.logout();
					EngineWindow.getInstance().dispose();
					System.exit(0);

			}
			/*
			immigration.logout();
			EngineWindow.getInstance().dispose();
			System.exit(0);
			*/
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
			custom.customizedScreen(tester,immigration);
		}
	});
	
	JButton btnTalk = new JButton("TALK");
	btnTalk.setBounds(1055, 24, 76, 25);
	bottomtools.add(btnTalk);
	btnTalk.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int num2 = slider_1.getValue();
			soundSE = new Sound2("sheep.wav");
			soundSE.set(num2);
			
			String talktext = textField.getText();
			if(talktext.length()>100){
				textField.setText("No more than 100 Characters!");
			}else{
			immigration.talk(talktext);
			//LeftArrowBubble a = new LeftArrowBubble(talktext);
			//add(a);
			//a.setBounds(1060, 24, 200, 20);
			EngineWindow.getInstance().talk(talktext);
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
			textField_1.setText("");
			//TODO
			startYelling(yelltext);
			}
		}
	});
	setListeners();
	//pack();
	soundBG = new Sound("jasound.wav");
    
    //------------------------------------
	
	/*
    JPanel bottomtools = new JPanel();
    bottomtools.setBounds(0, (int) (screensize.height*0.795), screensize.width, (int) (screensize.height*0.205));
	add(bottomtools);
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
	//SliderHanderler a=new SliderHanderler();
	//slider.addChangeListener(a);
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
			//immigration.logout();
			//dispose();
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
			//immigration.talk(talktext);
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
				JDialog a = new JDialog();
				a.setVisible(true);
				a.pack();
				textField_1.setText("No more than 10 Characters!");
			}else{
			//immigration.yell(yelltext);
			textField_1.setText("");}
		}
	});
	*/
	//soundBG = new Sound("jasound.wav");---------

	
    
    /*
    JLabel iHaveAnArmy = new JLabel("ALIEN, ALIEN EVERY WHERE!!...");
    iHaveAnArmy.setFont(new Font("TimesRoman",Font.BOLD,25));
    dummyText.add(iHaveAnArmy);
    
    add(dummyText);
    */
    //-------------------------------------
    validate();
    repaint();
  }

  public Canvas() {
    this.setLayout(null);
    // this.setPreferredSize( );	
    this.setPreferredSize(new Dimension(EngineWindow.getInstance().getWidth(), EngineWindow.getInstance().getHeight()));
    this.setViewPort(this.getVisibleRect());
    this.setLoadingScreen(new LoadingScreen());

    fpsLbl = new JLabel();
    fpsLbl.setForeground(Color.RED);
    fpsLbl.setFont(new Font("Arial", Font.PLAIN, 12));
    add(fpsLbl);

    squareDrawCountLbl = new JLabel();
    squareDrawCountLbl.setForeground(Color.RED);
    squareDrawCountLbl.setFont(new Font("Arial", Font.PLAIN, 12));
    add(squareDrawCountLbl);
  }

  @Override
  protected void paintComponent(Graphics g) {
    this.setCurrentlyRepainting(true);
    super.paintComponent(g);

    for (int i = 0; i < this.getComponentCount(); i++) {
      // Center all menus when resizing the canvas
      if (this.getComponent(i) instanceof MenuGroup) {
        MenuGroup menuGroup = (MenuGroup) this.getComponent(i);
        menuGroup.setLocation((this.getWidth() / 2) - (menuGroup.getWidth() / 2),
                (this.getHeight() / 2) - (menuGroup.getHeight() / 2));
      }
    }

    //if (GeneralStatic.getGameState() == GeneralStatic.STATE_RUNNING) {
    Game game = EngineWindow.getInstance().getGame();
    if (game != null) {
      drawBackground(g);
      // if( EngineWindow.getInstance().)
      Grid grid = game.getPlayfieldGrid();
      if (grid != null) {
        //try {
        grid.draw(g);
        /*} catch (Exception e) {
        System.err.println("Something went wrong drawing the grid etc. (" + e.getStackTrace()[0] + ")");
        }*/
      }


      if (game.getSelectBox() != null) {
        SelectBox box = game.getSelectBox().eliminateNegatives();
        int x = (int) box.getX(),
                y = (int) box.getY(),
                w = (int) box.getWidth(),
                h = (int) box.getHeight();
        g.setColor(new Color(255, 0, 0, 66));
        g.fillRect(x, y, w, h);
        g.setColor(Color.RED);
        g.drawRect(x, y, w, h);
      }
    }
    //}

    fpsLbl.setBounds(this.getViewPort().width - 120, 20, 200, 20);
    squareDrawCountLbl.setBounds(this.getViewPort().width - 120, 35, 200, 20);

    this.setCurrentlyRepainting(false);
  }

  /**
   * Draws the background of this canvas
   */
  private void drawBackground(Graphics g) {
    Background background = EngineWindow.getInstance().getBackgroundImage();
    if (background != null) {
      Image img = background.getImage();
      int w = img.getWidth(null), h = img.getHeight(null);
      switch (background.getMode()) {
        case Background.BACKGROUND_REPEAT: {
          for (int i = 0; i < (int) Math.ceil(EngineWindow.getInstance().getWidth() / w) + 1; i++) {
            for (int j = 0; j < (int) Math.ceil(EngineWindow.getInstance().getHeight() / h) + 1; j++) {
              g.drawImage(img, i * w, j * h, null);
            }
          }
          break;
        }
        case Background.BACKGROUND_FILL: {
          g.drawImage(img, 0, 0, EngineWindow.getInstance().getWidth(), EngineWindow.getInstance().getHeight(), null);
          break;
        }
        case Background.BACKGROUND_NO_REPEAT: {
          g.drawImage(img, 0, 0, w, h, null);
          break;
        }
        case Background.BACKGROUND_REPEAT_X: {
          for (int i = 0; i < (int) Math.ceil(EngineWindow.getInstance().getWidth() / w) + 1; i++) {
            g.drawImage(img, i * w, 0, null);
          }
          break;
        }
        case Background.BACKGROUND_REPEAT_Y: {
          for (int j = 0; j < (int) Math.ceil(EngineWindow.getInstance().getHeight() / h) + 1; j++) {
            g.drawImage(img, 0, j * h, null);
          }
          break;
        }
      }
    }
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
		//int sound = (int)(slider.getValue());
		//int num=3;
		public void stateChanged(ChangeEvent e){
			int num = slider.getValue();
			
			
		//System.out.println("Change value");
		//int sound2 = (int)(slider.getValue());
		//System.out.println(sound);
		//System.out.println(sound2);
		//slider.setValue(sound);
		/*if(sound-sound2<0){
			soundBG.increase();
		}else{
			soundBG.decrease();}
*/
			soundBG.set(num);
			
			
		} 
		
		}


	public void setIcetz(Myicetz me) {
		tester = me;
	}
	public void setImmigration(ICEWorldImmigration immi){
		  immigration = immi;
	  }
}