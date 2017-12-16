package Core;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Core.ConnectFourModel;

public class ConnectFourModelTest {
  private ConnectFourModel model;
  
  @Before
  public void setUp() {
    model = ConnectFourModel.getInstance();
  }

  @Test
  public void testStep() {
    
  }

  @Test
  public void testModeSet() {
    
  }

  @Test
  public void testReset() {
    model.reset();
    assertEquals(model.getTurn(), 1);
    assertEquals(model.getPlayer(), 1);
    assertEquals(model.getState(), model.READY);
    assertEquals(model.getWinState(), -1);
    String expectedString = "Board:\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0";
    assertEquals(model.getBoard().toString(), expectedString);
  }
}
