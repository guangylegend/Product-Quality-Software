package edu.nyu.pqs.canvas.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import edu.nyu.pqs.canvas.core.CanvasModel;

/**
 * This is the GUI class which wraps the canvas and toolbar to form a whole canvas window. It
 * implements the interface canvaslistener to recieve the events.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class CanvasGUI extends JFrame implements CanvasListener {
  private int id;
  private final CanvasModel model;
  private final Canvas canvas;
  private final ToolPanel tools;

  public CanvasGUI(int Canvasid, CanvasModel main) {
    id = Canvasid;
    model = main;
    canvas = new Canvas(main);
    tools = new ToolPanel(main, canvas);
    setLocation(200 + 20 * id, 100 + 10 * id);

    Container content = this.getContentPane();
    content.setLayout(new BorderLayout());
    content.add(canvas, BorderLayout.WEST);
    content.add(tools, BorderLayout.EAST);

    if (id == 0) {
      setTitle("Main Canvas");
    } else {
      setTitle("Canvas" + " " + id);
    }
    pack();
  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public void eventRepaint() {
    canvas.updateCanvas();
  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public int getId() {
    return id;
  }
}
