package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import Core.ConnectFourModel;

/**
 * The board panel for the connect four GUI. It shows the game board, in which the player 1 has red
 * color while the player 2 has yellow color. Human player can choose one column to insert his disc,
 * the expected insert place will be marked as his color.
 * 
 * @author Guang Yang
 * @version 1.0
 */
public class BoardPanel extends JPanel implements MouseMotionListener {
  private ConnectFourModel main;
  private int[][] board;
  private final int circleRadius = 20;
  private final int circleBound = 5;
  private final int xBound = 75;
  private final int yBound = 50;
  private int boardWidth;
  private int boardLength;
  private int mouseColumn;
  private int clickColumn;

  public BoardPanel(ConnectFourModel main, int[][] board) {
    this.main = main;
    this.board = board;
    this.setPreferredSize(new Dimension(500, 400));
    boardWidth = (circleBound + circleRadius) * 2 * board.length;
    boardLength = (circleBound + circleRadius) * 2 * board[0].length;
    this.setMinimumSize(new Dimension(boardLength, boardWidth));
    // setBackground(Color.red);
    this.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int x = e.getX() - xBound;
        int y = e.getY() - yBound;
        if (x < boardLength && y < boardWidth) {
          clickColumn = x / ((circleRadius + circleBound) * 2);
          main.step(clickColumn);
        }
      }
    });
    this.addMouseMotionListener(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    int x = e.getX() - xBound;
    int y = e.getY() - yBound;
    if (x < boardLength && y < boardWidth) {
      mouseColumn = x / ((circleRadius + circleBound) * 2);
    } else {
      mouseColumn = -1;
    }
    repaint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mouseDragged(MouseEvent e) {};

  private void drawCircle(Graphics g, int xCenter, int yCenter, int r, int color) {
    if (color <= 0) {
      if (color == 0) {
        g.setColor(Color.BLACK);
      }
      if (color == -1) {
        g.setColor(Color.RED);
      }
      if (color == -2) {
        g.setColor(Color.YELLOW);
      }
      g.drawOval(xBound + xCenter - r, yBound + yCenter - r, 2 * r, 2 * r);
    } else {
      if (color == 1) {
        g.setColor(Color.RED);
      }
      if (color == 2) {
        g.setColor(Color.YELLOW);
      }
      g.fillOval(xBound + xCenter - r, yBound + yCenter - r, 2 * r, 2 * r);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int state = main.getState();
    int player = main.getPlayer();
    if (state == ConnectFourModel.READY) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          drawCircle(g, (j * 2 + 1) * (circleBound + circleRadius),
              (i * 2 + 1) * (circleBound + circleRadius), circleRadius, 0);
        }
      }
    } else if (state == ConnectFourModel.START) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          int color = board[i][j];
          if (j == mouseColumn && color == 0) {
            if ((i + 1 < board.length && board[i + 1][j] != 0) || i + 1 == board.length) {
              drawCircle(g, (j * 2 + 1) * (circleBound + circleRadius),
                  (i * 2 + 1) * (circleBound + circleRadius), circleRadius, player);
            } else {
              drawCircle(g, (j * 2 + 1) * (circleBound + circleRadius),
                  (i * 2 + 1) * (circleBound + circleRadius), circleRadius, -player);
            }
          } else {
            drawCircle(g, (j * 2 + 1) * (circleBound + circleRadius),
                (i * 2 + 1) * (circleBound + circleRadius), circleRadius, color);
          }
        }
      }
    } else if (state == ConnectFourModel.RESULT) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          int color = board[i][j];
          drawCircle(g, (j * 2 + 1) * (circleBound + circleRadius),
              (i * 2 + 1) * (circleBound + circleRadius), circleRadius, color);
        }
      }
    }
  }

  /**
   * Update the board.
   */
  public void update() {
    this.board = main.getBoard().getBoard();
    repaint();
  }
}
