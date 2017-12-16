package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Core.Board;
import Core.ConnectFourModel;

/**
 * The GUI designed for the Connect Four game. There are two part of the UI. The first one is a
 * panel showing the current board, you can choose one column to insert your disc. The second one is
 * a information panel, which shows the turn, currentplayer and result information. There's also a
 * button to start and restart the game.
 * 
 * There are two modes in the game, one for two people, and the other for versusing computer AI. You
 * can choose either of the mode in the beginning of the game.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class GUI extends JFrame {
  private ConnectFourModel main;
  private Board board;
  private BoardPanel boardPanel;
  private ButtonPanel buttonPanel;

  public GUI(ConnectFourModel main) {
    this.main = main;
    this.board = main.getBoard();
    boardPanel = new BoardPanel(main, board.getBoard());
    buttonPanel = new ButtonPanel(main);
    this.setLocation(200, 100);

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException exception) {
      exception.printStackTrace();
    }

    Container content = this.getContentPane();
    content.setLayout(new BorderLayout());
    content.add(boardPanel, BorderLayout.WEST);
    content.add(buttonPanel, BorderLayout.EAST);

    this.setTitle("Connect Four");
    this.pack();
  }

  /**
   * Update the panel to show the result of the game.
   * 
   * @param result the winning state of the game.
   */
  public void showResult(int result) {
    buttonPanel.showResult(result);
  }

  /**
   * Update the whole GUI.
   */
  public void update() {
    boardPanel.update();
    buttonPanel.update();
  }

}
