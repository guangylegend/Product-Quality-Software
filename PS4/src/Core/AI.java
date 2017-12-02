package Core;

/**
 * An AI designed for the Connect Four game. It will give the next 
 * move based on the current situation.
 * 
 * @author Guang Yang
 * @version 1.0
 */
public class AI {
  private static int twoConnect = 5;
  private static int threeConnect = 25;

  private static boolean validate(Integer[][] board, int i, int j, int player) {
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
      return false;
    }
    if (board[i][j] == player) {
      return true;
    }
    return false;
  }

  private static int calculate(Integer[][] board, int player) {
    int points = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == player) {

          if (validate(board, i + 1, j, player) && validate(board, i + 2, j, player)
              && validate(board, i + 3, j, player)) {
            return Integer.MAX_VALUE;
          }
          if (validate(board, i + 1, j, player) && validate(board, i + 2, j, player)) {
            points += threeConnect;
          }
          if (validate(board, i + 1, j, player)) {
            points += twoConnect;
          }

          if (validate(board, i, j + 1, player) && validate(board, i, j + 2, player)
              && validate(board, i, j + 3, player)) {
            return Integer.MAX_VALUE;
          }
          if (validate(board, i, j + 1, player) && validate(board, i, j + 2, player)) {
            points += threeConnect;
          } else if (validate(board, i, j + 1, player)) {
            points += twoConnect;
          }

          if (validate(board, i + 1, j + 1, player) && validate(board, i + 2, j + 2, player)
              && validate(board, i + 3, j + 3, player)) {
            return Integer.MAX_VALUE;
          }
          if (validate(board, i + 1, j + 1, player) && validate(board, i + 2, j + 2, player)) {
            points += threeConnect;
          } else if (validate(board, i + 1, j + 1, player)) {
            points += twoConnect;
          }

          if (validate(board, i + 1, j - 1, player) && validate(board, i + 2, j - 2, player)
              && validate(board, i + 3, j - 3, player)) {
            return Integer.MAX_VALUE;
          }
          if (validate(board, i + 1, j - 1, player) && validate(board, i + 2, j - 2, player)) {
            points += threeConnect;
          } else if (validate(board, i + 1, j - 1, player)) {
            points += twoConnect;
          }
        }
      }
    }
    return points;
  }

  /** 
   * Return the next move the ai predicts according to current board.
   * 
   * @param board current board
   * @param player the player which ai plays
   * @return the column this ai selected
   */
  public static int getNextMove(Integer[][] board, int player) {
    int[] points = new int[board[0].length];
    for (int j = 0; j < board[0].length; j++) {
      if (board[0][j] != 0) {
        points[j] = 0;
      } else {
        for (int i = 0;; i++) {
          if ((i + 1 < board.length && board[i + 1][j] != 0) || i + 1 == board.length) {
            board[i][j] = player;
            int p = calculate(board, player);
            if (p == Integer.MAX_VALUE) {
              return j;
            }
            board[i][j] = 3 - player;
            int q = calculate(board, 3 - player);
            if (q == Integer.MAX_VALUE) {
              return j;
            }
            board[i][j] = 0;
            points[j] = p + q;
            break;
          }
        }
      }
    }
    int smax = 0;
    int index = 0;
    for (int j = 0; j < board[0].length; j++) {
      if (points[j] > smax) {
        smax = points[j];
        index = j;
      }
    }
    return index;
  }
}
