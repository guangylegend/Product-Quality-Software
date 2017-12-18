package edu.nyu.pqs.canvas.core;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import edu.nyu.pqs.canvas.content.CanvasContent;
import edu.nyu.pqs.canvas.gui.*;

/**
 * This is the model class for the canvas application. This is main class to create and manage all
 * the canvas windows, and add or delete the canvas contents. All the canvas windows are using the
 * same content list thus the modification of any canvas window will reflect to all the windows.
 * 
 * At any point, only one application will be running so singleton pattern is used to ensure this.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class CanvasModel {
  private static final CanvasModel INSTANCE = new CanvasModel();
  private List<CanvasListener> listenerList;
  private List<CanvasContent> contentList;

  // singleton pattern
  private CanvasModel() {
    listenerList = new LinkedList<>();
    contentList = new LinkedList<>();
  }

  /**
   * This is the method used for getting the instance of this class.
   * 
   * @return the single instance of this class.
   * 
   */
  public static CanvasModel getInstance() {
    return INSTANCE;
  }

  /**
   * This method is used to initialize the application and create the main canvas.
   * 
   */
  public void start() {
    CanvasGUI canvas = CanvasGUIFactory.getNewCanvas();
    listenerList.add(canvas);
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas.setVisible(true);
  }

  /**
   * This method is used to create one more canvas window.
   * 
   */
  public void createCanvas() {
    CanvasGUI canvas = CanvasGUIFactory.getNewCanvas();
    listenerList.add(canvas);
    canvas.setVisible(true);
  }

  /**
   * @return the copy of all canvas contents.
   * 
   */
  public List<CanvasContent> getContent() {
    return new LinkedList<CanvasContent>(contentList);
  }

  /**
   * This method is used to add a content to the content list.
   * 
   * @param content the canvas content to be added.
   */
  public void addContent(CanvasContent content) {
    contentList.add(content);
    sendEventRepaint();
  }

  /**
   * This method is used to revert a adding operation on the content list.
   * 
   */
  public void revert() {
    if (!contentList.isEmpty()) {
      contentList.remove(contentList.size() - 1);
      sendEventRepaint();
    }
  }

  /**
   * This method is used to clear all the canvas content.
   * 
   */
  public void clear() {
    contentList.clear();
    sendEventRepaint();
  }

  /**
   * Register a new listener to the model, only used for test.
   * 
   * @param listener the new listener to be added.
   */
  public void registerListener(CanvasListener listener) {
    listenerList.add(listener);
  }

  /**
   * @return all the listeners, only used for test.
   * 
   */
  public List<CanvasListener> getListenersForTest() {
    return listenerList;
  }

  private void sendEventRepaint() {
    for (CanvasListener listener : listenerList) {
      listener.eventRepaint();
    }
  }
}
