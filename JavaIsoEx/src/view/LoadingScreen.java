/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Wouter
 */
public class LoadingScreen extends JPanel {

  private JProgressBar progressBar = new JProgressBar();
  private JLabel loadingLbl = new JLabel("Loading...");

  public JProgressBar getProgressBar() {
    return progressBar;
  }

  private void setProgressBar(JProgressBar progressBar) {
    this.progressBar = progressBar;
  }

  public LoadingScreen() {
    this.setLayout(null);
    int w = EngineWindow.getInstance().getWidth(), h = EngineWindow.getInstance().getHeight();
    this.setBounds(0, 0, w, h);

    loadingLbl.setFont(new Font("Arial", Font.PLAIN, 18));
    //add(progressBar);
    add(loadingLbl);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int w = EngineWindow.getInstance().getWidth(), h = EngineWindow.getInstance().getHeight();
    int stringWidth = loadingLbl.getFontMetrics(loadingLbl.getFont()).stringWidth(loadingLbl.getText());
    loadingLbl.setBounds((w / 2) - (stringWidth / 2), (int) (h * 0.8) - 40, 400, 40);

    progressBar.setBounds((w / 2) - 200, (int) (h * 0.8), 400, 40);
  }
}
