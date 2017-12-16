package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Core.Board;
import Core.ConnectFour;

public class GUI extends JFrame {
  private ConnectFour main;
  private Board board;
  private BoardPanel boardPanel;
  private ButtonPanel buttonPanel;

  public GUI(ConnectFour main, Board board) {
    this.main = main;
    this.board = board;
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

  public void result(int result) {
    buttonPanel.updateResult(result);
  }

  public void update(Board board) {
    boardPanel.update(board.getBoard());
  }
  
  public void updateTurn(int turn, int player){
    buttonPanel.updateTurn(turn);
    buttonPanel.updatePlayer(player);
  }
  
}
