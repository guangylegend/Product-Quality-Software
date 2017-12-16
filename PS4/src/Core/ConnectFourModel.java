package Core;

import javax.swing.JFrame;
import GUI.GUI;

/**
 * The main game for Connect Four. The board size is 6 * 7. The one who first have four consecutive
 * marks(horizontal, vertical or diagonal) in the board will win. Support for 2-players playing at
 * the same machine and playing with an AI.
 * 
 * @author Guang Yang
 * @version 1.0
 */
public class ConnectFourModel {
  private int turn;
  private int mode;
  private int currentPlayer;
  private GUI ui;
  private Board board;
  private int gameState;
  private int winState;
  public static final int TWIN = 1;
  public static final int COMP = 2;
  public static final int READY = 0;
  public static final int START = 1;
  public static final int RESULT = 2;

  private static final ConnectFourModel INSTANCE = new ConnectFourModel();

  private ConnectFourModel() {
    turn = 1;
    currentPlayer = 1;
    mode = ConnectFourModel.TWIN;
    board = new Board();
    ui = new GUI(this);
    gameState = ConnectFourModel.READY;
    winState = -1;
  }

  public static ConnectFourModel getInstance() {
    return INSTANCE;
  }

  /**
   * Start the game, draw the GUI.
   * 
   */
  public void start() {
    ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ui.setVisible(true);
  }

  /**
   * Current player takes a step. If this step is valid, it will be marked on the board. And if
   * current mode is playing with AI, this AI will take a step too.
   * 
   * @param column the column current player wants to insert the mark.
   */
  public void step(int column) {
    if (gameState != ConnectFourModel.START) {
      return;
    }
    if (board.mark(column, currentPlayer)) {
      ui.update();
      if (board.check(currentPlayer)) {
        gameState = ConnectFourModel.RESULT;
        winState = currentPlayer;
        ui.showResult(currentPlayer);
      } else {
        if (board.isFull()) {
          gameState = ConnectFourModel.RESULT;
          winState = 0;
          ui.showResult(0);
        } else {
          currentPlayer = 3 - currentPlayer;
          turn++;
          ui.update();

          if (mode == ConnectFourModel.COMP) {
            int col = AI.getNextMove(board.getBoard(), currentPlayer);
            board.mark(col, currentPlayer);
            ui.update();
            if (board.check(currentPlayer)) {
              gameState = ConnectFourModel.RESULT;
              winState = currentPlayer;
              ui.showResult(currentPlayer);
            } else {
              if (board.isFull()) {
                gameState = ConnectFourModel.RESULT;
                winState = 0;
                ui.showResult(0);
              } else {
                currentPlayer = 3 - currentPlayer;
                turn++;
                ui.update();
              }
            }

          }
        }
      }
    }
  }

  /**
   * Set the game mode. TWIN for 2-players playing at the same machine. COMP for playing with an AI.
   * 
   * @param selectedMode the game mode
   */
  public void modeSet(int selectedMode) {
    if (this.gameState != ConnectFourModel.READY) {
      return;
    }
    mode = selectedMode;
    gameState = ConnectFourModel.START;
    ui.update();
  }

  /**
   * Reset this game to begin a new game.
   * 
   */
  public void reset() {
    turn = 1;
    currentPlayer = 1;
    board = new Board();
    ui.update();
    gameState = ConnectFourModel.READY;
    winState = -1;
  }

  /**
   * @return current game state.
   * 
   */
  public int getState() {
    return gameState;
  }

  /**
   * @return current turn count.
   * 
   */
  public int getTurn() {
    return turn;
  }

  /**
   * @return current player id.
   * 
   */
  public int getPlayer() {
    return currentPlayer;
  }

  /**
   * @return current winning state if the game has ended, only used for test.
   * 
   */
  public int getWinState() {
    return winState;
  }

  /**
   * @return current game board.
   * 
   */
  public Board getBoard() {
    return board;
  }
}
