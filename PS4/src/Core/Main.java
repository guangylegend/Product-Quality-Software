package Core;

public class Main {
  public static void main(String[] args) {
    ConnectFour game = ConnectFourFactory.getInstance();
    game.start();
  }
}
