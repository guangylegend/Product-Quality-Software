package edu.nyu.pqs.canvas.core;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import edu.nyu.pqs.canvas.content.CanvasContent;
import edu.nyu.pqs.canvas.gui.*;

public class CanvasModel {
  private static final CanvasModel INSTANCE = new CanvasModel();
  private List<CanvasListener> listenerList;
  private List<CanvasContent> contentList;

  private CanvasModel() {
    listenerList = new LinkedList<>();
    contentList = new LinkedList<>();
  }

  public static CanvasModel getInstance() {
    return INSTANCE;
  }

  public void start() {
    CanvasGUI canvas = CanvasGUIFactory.getNewCanvas();
    listenerList.add(canvas);
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas.setVisible(true);
  }

  public void createCanvas() {
    CanvasGUI canvas = CanvasGUIFactory.getNewCanvas();
    listenerList.add(canvas);
    canvas.setVisible(true);
  }

  public List<CanvasContent> getContent() {
    return new LinkedList<CanvasContent>(contentList);
  }

  public void addContent(CanvasContent content) {
    contentList.add(content);
    sendEventRepaint();
  }

  public void revert() {
    if (!contentList.isEmpty()) {
      contentList.remove(contentList.size() - 1);
      sendEventRepaint();
    }
  }

  public void clear() {
    contentList.clear();
    sendEventRepaint();
  }

  public void registerListener(CanvasListener listener) {
    listenerList.add(listener);
  }

  public List<CanvasListener> getListenersForTest() {
    return listenerList;
  }

  private void sendEventRepaint() {
    for (CanvasListener listener : listenerList) {
      listener.eventRepaint();
    }
  }
}
