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
    this.turn = 1;
    this.currentPlayer = 1;
    this.mode = ConnectFourModel.TWIN;
    this.board = new Board();
    this.ui = new GUI(this, this.board);
    this.gameState = ConnectFourModel.READY;
    this.winState = -1;
  }

  public static ConnectFourModel getInstance() {
    return INSTANCE;
  }

  /**
   * Start the game, draw the GUI.
   * 
   */
  public void start() {
    this.ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.ui.setVisible(true);
  }

  /**
   * Current player takes a step. If this step is valid, it will be marked on the board. And if
   * current mode is playing with AI, this AI will take a step too.
   * 
   * @param column the column current player wants to insert the mark.
   */
  public void step(int column) {
    if (this.gameState != ConnectFourModel.START) {
      return;
    }
    if (board.mark(column, currentPlayer)) {
      ui.update(board);
      if (board.check(currentPlayer)) {
        this.gameState = ConnectFourModel.RESULT;
        this.winState = currentPlayer;
        ui.result(currentPlayer);
      } else {
        if (board.isFull()) {
          this.gameState = ConnectFourModel.RESULT;
          this.winState = 0;
          ui.result(0);
        } else {
          currentPlayer = 3 - currentPlayer;
          turn++;
          this.ui.updateTurn(turn, currentPlayer);

          if (mode == ConnectFourModel.COMP) {
            int col = AI.getNextMove(board.getBoard(), currentPlayer);
            board.mark(col, currentPlayer);
            ui.update(board);
            if (board.check(currentPlayer)) {
              this.gameState = ConnectFourModel.RESULT;
              this.winState = currentPlayer;
              ui.result(currentPlayer);
            } else {
              if (board.isFull()) {
                this.gameState = ConnectFourModel.RESULT;
                this.winState = 0;
                ui.result(0);
              } else {
                currentPlayer = 3 - currentPlayer;
                turn++;
                this.ui.updateTurn(turn, currentPlayer);
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
   * @param mode the game mode
   */
  public void modeSet(int mode) {
    if (this.gameState != ConnectFourModel.READY) {
      return;
    }
    this.mode = mode;
    this.gameState = ConnectFourModel.START;
    this.ui.updateTurn(turn, currentPlayer);
  }

  /**
   * Reset this game to begin a new game.
   * 
   */
  public void reset() {
    this.turn = 1;
    this.currentPlayer = 1;
    this.board = new Board();
    this.ui.update(board);
    this.gameState = ConnectFourModel.READY;
  }

  /**
   * Return current game state.
   * 
   */
  public int getState() {
    return this.gameState;
  }

  /**
   * Return current player id.
   * 
   */
  public int getPlayer() {
    return this.currentPlayer;
  }

  /**
   * Return current winning state if the game has ended.
   * 
   */
  public int getWinState() {
    return this.winState;
  }
}
