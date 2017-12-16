package Core;

/**
 * The board designed for the Connect Four game. Supports basic operations including mark, and
 * winning check.
 * 
 * @author Guang Yang
 * @version 1.0
 */
public class Board {
  private final int width = 6;
  private final int length = 7;
  private Integer[][] boardMap;

  /**
   * Constructs the board with its width and length. Generally width will always be 6 and length
   * always be 7.
   * 
   */
  public Board() {
    boardMap = new Integer[width][length];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < length; j++) {
        boardMap[i][j] = 0;
      }
    }
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
    for (int i = width - 1; i >= 0; i--) {
      if (boardMap[i][column] == 0) {
        boardMap[i][column] = player;
        return true;
      }
    }
    return false;
  }

  private boolean validate(int i, int j, int player) {
    if (i < 0 || i >= width || j < 0 || j >= length) {
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
              && validate(i + 3, j, player))
            return true;
          if (validate(i, j + 1, player) && validate(i, j + 2, player)
              && validate(i, j + 3, player))
            return true;
          if (validate(i + 1, j + 1, player) && validate(i + 2, j + 2, player)
              && validate(i + 3, j + 3, player))
            return true;
          if (validate(i - 1, j + 1, player) && validate(i - 2, j + 2, player)
              && validate(i - 3, j + 3, player))
            return true;
        }
      }
    }
    return false;
  }

  /**
   * Return a copy of current board.
   * 
   * @return the board to be returned.
   */
  public Integer[][] getBoard() {
    Integer[][] newBoard = new Integer[width][length];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < length; j++) {
        newBoard[i][j] = new Integer(boardMap[i][j]);
      }
    }
    return newBoard;
  }
}
