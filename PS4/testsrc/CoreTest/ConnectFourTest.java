package CoreTest;

import static org.junit.Assert.*;
import org.junit.Test;
import Core.ConnectFour;
import Core.ConnectFourFactory;
import java.util.Random;

public class ConnectFourTest {
  @Test
  public void ComprehensiveTest() {
    ConnectFour game = ConnectFourFactory.getInstance();
    game.start();

    game.reset();
    game.modeSet(ConnectFour.TWIN);
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        game.step(j);
      }
    }
    assertEquals(game.getWinState(), 2);
    assertEquals(game.getState(), ConnectFour.RESULT);

    game.reset();
    assertEquals(game.getState(), ConnectFour.READY);
    game.modeSet(ConnectFour.TWIN);
    assertEquals(game.getState(), ConnectFour.START);
    for (int j = 0; j < 7; j++) {
      for (int i = 0; i < 6; i++) {
        game.step(j);
      }
    }
    assertEquals(game.getWinState(), 1);

    game.reset();
    game.modeSet(ConnectFour.TWIN);
    for (int j = 0; j < 7; j++) {
      for (int i = 0; i < 10; i++) {
        game.step(j);
      }
    }
    game.modeSet(ConnectFour.TWIN);

    game.reset();
    game.modeSet(ConnectFour.TWIN);
    for (int j = 0; j < 14; j += 2) {
      assertEquals(game.getPlayer(), 1);
      game.step(j % 7);
      assertEquals(game.getPlayer(), 2);
      game.step((j + 1) % 7);
      assertEquals(game.getPlayer(), 1);
      game.step(j % 7);
      assertEquals(game.getPlayer(), 2);
      game.step((j + 1) % 7);
      assertEquals(game.getPlayer(), 1);
      game.step(j % 7);
      assertEquals(game.getPlayer(), 2);
      game.step((j + 1) % 7);
    }
    assertEquals(game.getWinState(), 0);

    // random test on AI
    Random rand = new Random();

    int testRound = 10000;
    for (int i = 0; i < testRound; i++) {
      game.reset();
      game.modeSet(ConnectFour.COMP);
      while (game.getState() != ConnectFour.RESULT) {
        int col = rand.nextInt(7);
        game.step(col);
      }
    }

  }
}
