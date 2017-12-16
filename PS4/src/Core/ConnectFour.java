package Core;

public class ConnectFour {

  private void start() {
    ConnectFourModel game = ConnectFourModel.getInstance();
    game.start();
  }

  public static void main(String args[]) {
    new ConnectFour().start();
  }
}
