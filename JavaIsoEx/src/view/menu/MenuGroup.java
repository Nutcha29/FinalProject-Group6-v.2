/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

import view.EngineWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Wouter
 */
public class MenuGroup extends JPanel {

  private LinkedList<MenuItem> menuList;
  private int menuItemTopPadding = 20;
  private int menuItemWidth = 300;
  private int menuItemHeight = 50;

  // <editor-fold defaultstate="collapsed" desc="Padding">

  /**
   * Gets the top padding between the menu items
   * @return The padding in pixels
   */
  public int getMenuItemTopPadding() {
    return menuItemTopPadding;
  }

  /**
   * Set the top padding between the menu items
   * @param menuItemTopPadding The new padding, in pixels
   */
  public void setMenuItemTopPadding(int menuItemTopPadding) {
    this.menuItemTopPadding = menuItemTopPadding;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Width and Height">
  /**
   * Gets the menu item height, in pixels
   * @return The height, in pixels
   */
  public int getMenuItemHeight() {
    return menuItemHeight;
  }

  /**
   * Sets the menu item height, in pixels
   * @param menuItemHeight The height, in pixels
   */
  public void setMenuItemHeight(int menuItemHeight) {
    this.menuItemHeight = menuItemHeight;
  }

  /**
   * Gets the menu item width, in pixels
   * @return The width, in pixels
   */
  public int getMenuItemWidth() {
    return menuItemWidth;
  }

  /**
   * Sets the menu item width, in pixels
   * @param menuItemWidth The width, in pixels
   */
  public void setMenuItemWidth(int menuItemWidth) {
    this.menuItemWidth = menuItemWidth;
  }
  //</editor-fold>

  /**
   * Gets the menu list
   * @return The menu list
   */
  public LinkedList<MenuItem> getMenuList() {
    return menuList;
  }

  /**
   * Adds a menu item to this menu group
   */
  public void addMenuItem(MenuItem item) {
    int count = this.getMenuList().size();
    int newY = 0;

    newY += this.getMenuItemTopPadding() * (count + 1);
    newY += this.getMenuItemHeight() * count;

    item.setBounds( ( this.getWidth() / 2 ) - ( this.getMenuItemWidth() / 2 ), newY,
            this.getMenuItemWidth(), this.getMenuItemHeight());
    
    this.getMenuList().add(item);
    count = this.getMenuList().size();
    
    this.setSize( this.getWidth(), this.getMenuItemTopPadding() * ( count + 1 ) + this.getMenuItemHeight() * count );
    
    this.add(item);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < this.getMenuList().size(); i++) {
      MenuItem item = this.getMenuList().get(i);
      item.setSize(this.getMenuItemWidth(), this.getMenuItemHeight());
    }
  }

  public MenuGroup(EngineWindow thisUI) {
    this.setLayout(null);
    this.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
    this.setBackground(new Color(Color.blue.getRed(), Color.blue.getGreen(),
              Color.blue.getBlue(), 127));

    menuList = new LinkedList<MenuItem>();
  }
}
