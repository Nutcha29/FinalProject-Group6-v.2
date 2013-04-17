/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import variables.ZoomStatic;
import view.EngineWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Wouter
 */
public class ButtonListener implements ActionListener {

  private int btnNr = 0;

  public ButtonListener(int btnNr) {
    this.btnNr = btnNr;
  }

  public void actionPerformed(ActionEvent e) {
    switch (btnNr) {
      // Zoom out
      case 1: {
        int zoomLevel = ZoomStatic.CURRENT_ZOOM_LEVEL;

        if (zoomLevel != ZoomStatic.ZOOM_SMALL) {
          zoomLevel--;
          EngineWindow.getInstance().getGame().getPlayfieldGrid().setCurrentZoomLevel(zoomLevel);
        }
        // If the zoom is now the smallest
        if (zoomLevel == ZoomStatic.ZOOM_SMALL) {
          JButton btn = (JButton) e.getSource();
          // You cant zoom in any further
          btn.setEnabled(false);
        }
        // System.out.println("Zoomlevel: " + zoomLevel);
        EngineWindow.getInstance().getCanvas().getZoomInBtn().setEnabled(true);

      }
      break;
      // Zoom in
      case 2: {
        int zoomLevel = ZoomStatic.CURRENT_ZOOM_LEVEL;

        if (zoomLevel != ZoomStatic.ZOOM_LARGE) {
          zoomLevel++;
          EngineWindow.getInstance().getGame().getPlayfieldGrid().setCurrentZoomLevel(zoomLevel);
        }
        // If the zoom is now the smallest
        if (zoomLevel == ZoomStatic.ZOOM_LARGE) {
          JButton btn = (JButton) e.getSource();
          // You cant zoom in any further
          btn.setEnabled(false);
        }
        // System.out.println("Zoomlevel: " + zoomLevel);
        EngineWindow.getInstance().getCanvas().getZoomOutBtn().setEnabled(true);

      }
      break;


    }
  }
}
