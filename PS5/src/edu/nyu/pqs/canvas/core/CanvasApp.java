package edu.nyu.pqs.canvas.core;

/**
 * The main class to initiate and start the connect four game.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class CanvasApp {

  private void start() {
    CanvasModel canvas = CanvasModel.getInstance();
    canvas.start();
  }

  public static void main(String args[]) {
    new CanvasApp().start();
  }
}
