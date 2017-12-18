package edu.nyu.pqs.canvas.gui;

/**
 * This is the interface for the listeners of the canvas application. This interface contains all
 * the events which may be invoked by the main canvas model.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public interface CanvasListener {

  /**
   * This event occurs when a repaint request is issued.
   * 
   */
  public void eventRepaint();

  /**
   * @return the unique id for the listener.
   * 
   */
  public int getId();
}
