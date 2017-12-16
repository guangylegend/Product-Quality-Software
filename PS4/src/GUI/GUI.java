package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Core.Board;
import Core.ConnectFourModel;

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
        | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    Container content = this.getContentPane();
    content.setLayout(new BorderLayout());
    content.add(boardPanel, BorderLayout.WEST);
    content.add(buttonPanel, BorderLayout.EAST);

    this.setTitle("Connect Four");
    this.pack();
  }

  public void showResult(int result) {
    buttonPanel.showResult(result);
  }

  public void update() {
    boardPanel.update();
    buttonPanel.update();
  }
  
}
