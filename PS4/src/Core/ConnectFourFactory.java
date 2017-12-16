package Core;

public class ConnectFourFactory {
  
  private static class ConnectFourHelper{
    private static final ConnectFour INSTANCE = new ConnectFour();
  }
  
  public static ConnectFour getInstance(){
      return ConnectFourHelper.INSTANCE;
  }
}
