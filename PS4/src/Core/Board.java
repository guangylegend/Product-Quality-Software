package Core;

/**
 * The board designed for the Connect Four game. Supports basic operations including mark, and
 * winning check.
 * 
 * @author Guang Yang
 * @version 1.0
 */
public class Board {
  private static final int width = 6;
  private static final int length = 7;
  private int[][] boardMap;

  /**
   * Constructs the board with its width and length. Generally width will always be 6 and length
   * always be 7.
   * 
   */
  public Board() {
    boardMap = new int[width][length];
  }

  private boolean isValid(int i, int j) {
    if (i < 0 || i >= width || j < 0 || j >= length) {
      return false;
    }
    return true;
  }

  /**
   * Mark the target column with certain player.
   * 
   * @param column the column to be marked
   * @param player the player to be marked
   * @return Whether this mark operation is successful. If this column is full, it will return
   *         false.
   */
  public boolean mark(int column, int player) {
    if (isValid(0, column)) {
      for (int i = width - 1; i >= 0; i--) {
        if (boardMap[i][column] == 0) {
          boardMap[i][column] = player;
          return true;
        }
      }
    }
    return false;
  }

  private boolean validate(int i, int j, int player) {
    if (!isValid(i, j)) {
      return false;
    }
    if (boardMap[i][j] == player) {
      return true;
    }
    return false;
  }

  /**
   * Check whether this player has won the game.
   * 
   * @param player the player to be checked
   * @return Whether this player has won the game. If this player has four consecutive
   *         marks(horizontal, vertical or diagonal) in the board, he will win.
   */
  public boolean check(int player) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < length; j++) {
        if (boardMap[i][j] == player) {
          if (validate(i + 1, j, player) && validate(i + 2, j, player)
              && validate(i + 3, j, player)) {
            return true;
          }
          if (validate(i, j + 1, player) && validate(i, j + 2, player)
              && validate(i, j + 3, player)) {
            return true;
          }
          if (validate(i + 1, j + 1, player) && validate(i + 2, j + 2, player)
              && validate(i + 3, j + 3, player)) {
            return true;
          }
          if (validate(i - 1, j + 1, player) && validate(i - 2, j + 2, player)
              && validate(i - 3, j + 3, player)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * @return The width of the board.
   * 
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return The length of the board.
   * 
   */
  public int getLength() {
    return length;
  }

  /**
   * @return the copy of current board.
   */
  public int[][] getBoard() {
    int[][] newBoard = new int[width][length];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < length; j++) {
        newBoard[i][j] = boardMap[i][j];
      }
    }
    return newBoard;
  }

  /**
   * @return whether the board is full.
   */
  public boolean isFull() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < length; j++) {
        if (boardMap[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * @return the current board, only used for test.
   */
  public int[][] getBoardForTest() {
    return boardMap;
  }

  /**
   * @return the string representation of the board.
   */
  public String toString() {
    String str = "Board:\n";
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < length; j++) {
        str += boardMap[i][j];
        if (j < length - 1) {
          str += " ";
        }
      }
      if (i < width - 1) {
        str += "\n";
      }
    }
    return str;
  }
}
