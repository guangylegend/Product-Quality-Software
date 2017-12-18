package edu.nyu.pqs.canvas.gui;

import edu.nyu.pqs.canvas.core.CanvasModel;

public class CanvasGUIFactory {
  private static int id = -1;
  private static CanvasModel model = CanvasModel.getInstance();

  public static CanvasGUI getNewCanvas() {
    id++;
    return new CanvasGUI(id, model);
  }
}
