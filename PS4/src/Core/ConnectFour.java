package Core;

/**
 * The main class to initiate and start the connect four game.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class ConnectFour {

  private void start() {
    ConnectFourModel game = ConnectFourModel.getInstance();
    game.start();
  }

  public static void main(String args[]) {
    new ConnectFour().start();
  }
}
