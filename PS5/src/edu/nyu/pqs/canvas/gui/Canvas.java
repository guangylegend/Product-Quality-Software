package edu.nyu.pqs.canvas.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import edu.nyu.pqs.canvas.content.*;
import edu.nyu.pqs.canvas.core.CanvasModel;

public class Canvas extends JPanel {
  private final CanvasModel model;
  private List<CanvasContent> contentList;
  private boolean isDragged;
  private int dragX;
  private int dragY;
  private int dragStartX;
  private int dragStartY;
  private String currentShape;
  private String currentColor;

  public Canvas(CanvasModel main) {
    model = main;
    contentList = main.getContent();
    setPreferredSize(new Dimension(500, 400));
    currentColor = "Black";
    currentShape = "Rect";

    addMouseListener(new MouseAdapter() {

      public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (isDragged) {
          isDragged = false;
          CanvasContent content;
          switch (currentShape) {
            case "Oval":
              content = new CanvasOval(Math.min(x, dragStartX), Math.min(y, dragStartY),
                  Math.abs(x - dragStartX), Math.abs(y - dragStartY), currentColor);
              main.addContent(content);
              break;
            case "Rect":
              content = new CanvasRect(Math.min(x, dragStartX), Math.min(y, dragStartY),
                  Math.abs(x - dragStartX), Math.abs(y - dragStartY), currentColor);
              main.addContent(content);
              break;
            case "Line":
              content = new CanvasLine(dragStartX, dragStartY, x, y, currentColor);
              main.addContent(content);
              break;
            default:
              break;
          }
        }
      }
    });

    addMouseMotionListener(new MouseAdapter() {
      public void mouseDragged(MouseEvent e) {
        if (!isDragged) {
          dragStartX = e.getX();
          dragStartY = e.getY();
          dragX = dragStartX;
          dragY = dragStartY;
          isDragged = true;
        } else {
          dragX = e.getX();
          dragY = e.getY();
        }
        repaint();
      }
    });
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (isDragged) {
      switch (currentColor) {
        case "Red":
          g.setColor(Color.RED);
          break;
        case "Yellow":
          g.setColor(Color.YELLOW);
          break;
        case "Blue":
          g.setColor(Color.BLUE);
          break;
        case "Black":
          g.setColor(Color.BLACK);
          break;
        default:
          return;
      }
      switch (currentShape) {
        case "Oval":
          g.drawOval(Math.min(dragStartX, dragX), Math.min(dragStartY, dragY),
              Math.abs(dragStartX - dragX), Math.abs(dragStartY - dragY));
          break;
        case "Rect":
          g.drawRect(Math.min(dragStartX, dragX), Math.min(dragStartY, dragY),
              Math.abs(dragStartX - dragX), Math.abs(dragStartY - dragY));
          break;
        case "Line":
          g.drawLine(dragStartX, dragStartY, dragX, dragY);
          break;
        default:
          break;
      }
    }
    for (CanvasContent content : contentList) {
      switch (content.getColor()) {
        case "Red":
          g.setColor(Color.RED);
          break;
        case "Yellow":
          g.setColor(Color.YELLOW);
          break;
        case "Blue":
          g.setColor(Color.BLUE);
          break;
        case "Black":
          g.setColor(Color.BLACK);
          break;
        default:
          return;
      }
      switch (content.getType()) {
        case "Oval":
          CanvasOval oval = (CanvasOval) content;
          g.drawOval(oval.getTop()[0], oval.getTop()[1], oval.getWidth(), oval.getLength());
          break;
        case "Rect":
          CanvasRect rect = (CanvasRect) content;
          g.drawRect(rect.getTop()[0], rect.getTop()[1], rect.getWidth(), rect.getLength());
          break;
        case "Line":
          CanvasLine line = (CanvasLine) content;
          g.drawLine(line.getTop()[0], line.getTop()[1], line.getBottom()[0], line.getBottom()[1]);
          break;
        default:
          continue;
      }
    }
  }

  public void setColor(String color) {
    switch (color) {
      case "Red":
        currentColor = "Red";
        break;
      case "Yellow":
        currentColor = "Yellow";
        break;
      case "Blue":
        currentColor = "Blue";
        break;
      case "Black":
        currentColor = "Black";
        break;
      default:
        return;
    }
  }

  public void setShape(String shape) {
    switch (shape) {
      case "Oval":
        currentShape = "Oval";
        break;
      case "Rect":
        currentShape = "Rect";
        break;
      case "Line":
        currentShape = "Line";
        break;
      default:
        return;
    }
  }

  public void updateCanvas() {
    contentList = model.getContent();
    repaint();
  }
}
