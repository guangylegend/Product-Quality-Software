package edu.nyu.pqs.canvas.gui;

import edu.nyu.pqs.canvas.core.CanvasModel;

/**
 * This is the factory class to create new canvasGUI instances. It generates a unique id and assign
 * to the gui instances to manage them.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class CanvasGUIFactory {
  private static int id = -1;
  private static CanvasModel model = CanvasModel.getInstance();

  /**
   * @return a new canvasGUI instance.
   * 
   */
  public static CanvasGUI getNewCanvas() {
    id++;
    return new CanvasGUI(id, model);
  }
}
