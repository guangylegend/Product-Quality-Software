package edu.nyu.pqs.canvas.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import edu.nyu.pqs.canvas.core.CanvasModel;

/**
 * This is the toolbar class for canvas GUI, which allows user to choose the color and shape of the
 * next paint contents. It also supports reverting and clearing the contents. And user can use the
 * "new" button to create new canvas window.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class ToolPanel extends JPanel implements ActionListener {
  private final CanvasModel model;
  private final Canvas canvas;

  public ToolPanel(CanvasModel main, Canvas bindCanvas) {
    model = main;
    canvas = bindCanvas;
    setPreferredSize(new Dimension(100, 400));
    setBackground(Color.GRAY);

    JButton bAdd = new JButton("New");
    bAdd.setFont(new Font("Arial", Font.PLAIN, 10));
    bAdd.setPreferredSize(new Dimension(80, 30));
    bAdd.setFocusPainted(false);
    bAdd.setActionCommand("add");
    bAdd.addActionListener(this);

    JButton bRed = new JButton();
    bRed.setPreferredSize(new Dimension(40, 30));
    bRed.setBackground(Color.RED);
    bRed.setActionCommand("red");
    bRed.addActionListener(this);

    JButton bBlue = new JButton();
    bBlue.setPreferredSize(new Dimension(40, 30));
    bBlue.setBackground(Color.BLUE);
    bBlue.setActionCommand("blue");
    bBlue.addActionListener(this);

    JButton bYellow = new JButton();
    bYellow.setPreferredSize(new Dimension(40, 30));
    bYellow.setBackground(Color.YELLOW);
    bYellow.setActionCommand("yellow");
    bYellow.addActionListener(this);

    JButton bBlack = new JButton();
    bBlack.setPreferredSize(new Dimension(40, 30));
    bBlack.setBackground(Color.BLACK);
    bBlack.setActionCommand("black");
    bBlack.addActionListener(this);

    JButton bRect = new JButton();
    bRect.setPreferredSize(new Dimension(40, 30));
    bRect.setText("Rect");
    bRect.setFocusPainted(false);
    bRect.setActionCommand("rect");
    bRect.addActionListener(this);

    JButton bOval = new JButton();
    bOval.setPreferredSize(new Dimension(40, 30));
    bOval.setText("Oval");
    bOval.setFocusPainted(false);
    bOval.setActionCommand("oval");
    bOval.addActionListener(this);

    JButton bLine = new JButton();
    bLine.setPreferredSize(new Dimension(40, 30));
    bLine.setText("Line");
    bLine.setFocusPainted(false);
    bLine.setActionCommand("line");
    bLine.addActionListener(this);

    JButton bRevert = new JButton();
    bRevert.setPreferredSize(new Dimension(80, 30));
    bRevert.setText("Revert");
    bRevert.setFocusPainted(false);
    bRevert.setActionCommand("revert");
    bRevert.addActionListener(this);

    JButton bClear = new JButton();
    bClear.setPreferredSize(new Dimension(80, 30));
    bClear.setText("Clear");
    bClear.setFocusPainted(false);
    bClear.setActionCommand("clear");
    bClear.addActionListener(this);

    JPanel card1 = new JPanel();
    card1.setBackground(Color.GRAY);
    card1.add(bAdd);

    JPanel card2 = new JPanel();
    card2.setLayout(new GridLayout(0, 2));
    card2.add(bBlack);
    card2.add(bRed);
    card2.add(bYellow);
    card2.add(bBlue);

    JPanel card3 = new JPanel();
    card3.setLayout(new GridLayout(0, 1));
    card3.add(bRect);
    card3.add(bOval);
    card3.add(bLine);

    JPanel cards = new JPanel(new GridLayout(0, 1));
    cards.add(card1);
    cards.add(card2);
    cards.add(card3);

    JPanel options = new JPanel(new GridLayout(0, 1));
    options.add(bRevert);
    options.add(bClear);

    add(cards, BorderLayout.NORTH);
    add(options, BorderLayout.SOUTH);
  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case "add":
        model.createCanvas();
        break;
      case "red":
        canvas.setColor("Red");
        break;
      case "yellow":
        canvas.setColor("Yellow");
        break;
      case "blue":
        canvas.setColor("Blue");
        break;
      case "black":
        canvas.setColor("Black");
        break;
      case "oval":
        canvas.setShape("Oval");
        break;
      case "rect":
        canvas.setShape("Rect");
        break;
      case "line":
        canvas.setShape("Line");
        break;
      case "revert":
        model.revert();
        break;
      case "clear":
        model.clear();
        break;
      default:
        return;
    }
  }


}
