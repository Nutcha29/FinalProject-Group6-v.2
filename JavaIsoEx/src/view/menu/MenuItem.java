/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author Wouter
 */
public class MenuItem extends JButton implements MouseListener {

  Font font;
  boolean isMouseOver = false;

  public MenuItem() {
    font = new Font("Verdana", Font.PLAIN, 20);
    addMouseListener(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(Color.orange);
    if( isMouseOver ) g.setColor(new Color(200, 100, 0));
    else  g.setColor(new Color(200, 0, 0));
    g.fillRect(0, 0, this.getWidth(), this.getHeight());

    g.setFont(font);
    int stringWidth = (g.getFontMetrics(font).stringWidth(this.getText()));

    g.setColor(Color.black);
    g.drawString(this.getText(), (this.getWidth() / 2) - (stringWidth / 2), (this.getHeight() / 2) + 5);
  }

  public void mouseClicked(MouseEvent e) {
    //
  }

  public void mousePressed(MouseEvent e) {
    //
  }

  public void mouseReleased(MouseEvent e) {
    //
  }

  public void mouseEntered(MouseEvent e) {
    isMouseOver = true;
    repaint();
  }

  public void mouseExited(MouseEvent e) {
    isMouseOver = false;
    repaint();
  }
}
