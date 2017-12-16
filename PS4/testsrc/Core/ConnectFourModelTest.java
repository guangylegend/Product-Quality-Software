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
    Board board;
    int[][] boardMap;

    model.step(0);
    assertEquals(model.getTurn(), 1);

    model.reset();
    model.modeSet(ConnectFourModel.TWIN);
    for (int i = 0; i < 7; i++) {
      model.step(0);
    }
    assertEquals(model.getTurn(), 7);

    model.reset();
    model.modeSet(ConnectFourModel.TWIN);
    board = model.getBoard();
    boardMap = board.getBoardForTest();
    boardMap[5][0] = 1;
    boardMap[4][0] = 1;
    boardMap[3][0] = 1;
    model.step(0);
    assertEquals(model.getPlayer(), 1);
    assertEquals(model.getState(), ConnectFourModel.RESULT);
    assertEquals(model.getWinState(), 1);
    assertEquals(model.getTurn(), 1);

    model.reset();
    model.modeSet(ConnectFourModel.TWIN);
    board = model.getBoard();
    boardMap = board.getBoardForTest();
    for (int i = 0; i < board.getWidth(); i++) {
      for (int j = 0; j < board.getLength(); j++) {
        boardMap[i][j] = 2;
      }
    }
    boardMap[0][0] = 0;
    model.step(0);
    assertEquals(model.getPlayer(), 1);
    assertEquals(model.getState(), ConnectFourModel.RESULT);
    assertEquals(model.getWinState(), 0);
    assertEquals(model.getTurn(), 1);

    model.reset();
    model.modeSet(ConnectFourModel.COMP);
    board = model.getBoard();
    boardMap = board.getBoardForTest();
    boardMap[5][0] = 2;
    boardMap[4][0] = 2;
    boardMap[3][0] = 2;
    model.step(1);
    assertEquals(model.getPlayer(), 2);
    assertEquals(model.getState(), ConnectFourModel.RESULT);
    assertEquals(model.getWinState(), 2);
    assertEquals(model.getTurn(), 2);

    model.reset();
    model.modeSet(ConnectFourModel.COMP);
    board = model.getBoard();
    boardMap = board.getBoardForTest();
    boardMap[5][0] = 2;
    boardMap[4][0] = 2;
    model.step(1);
    assertEquals(model.getPlayer(), 1);
    assertEquals(model.getState(), ConnectFourModel.START);
    assertEquals(model.getWinState(), -1);
    assertEquals(model.getTurn(), 3);

    model.reset();
    model.modeSet(ConnectFourModel.COMP);
    board = model.getBoard();
    boardMap = board.getBoardForTest();
    for (int j = 0; j < 14; j++) {
      int index = j >= 7 ? 3 : 0;
      boardMap[index][j % 7] = j % 2 + 1;
      boardMap[index + 1][j % 7] = j % 2 + 1;
      boardMap[index + 2][j % 7] = j % 2 + 1;
    }
    boardMap[0][0] = 0;
    boardMap[0][1] = 0;

    model.step(0);
    assertEquals(model.getPlayer(), 2);
    assertEquals(model.getState(), ConnectFourModel.RESULT);
    assertEquals(model.getWinState(), 0);
    assertEquals(model.getTurn(), 2);
  }

  @Test
  public void testStart() {
    model.start(); // a window should show
  }

  @Test
  public void testReset() {
    model.reset();
    assertEquals(model.getTurn(), 1);
    assertEquals(model.getPlayer(), 1);
    assertEquals(model.getState(), ConnectFourModel.READY);
    assertEquals(model.getWinState(), -1);
    String expectedString = "Board:\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0";
    assertEquals(model.getBoard().toString(), expectedString);
  }

  @Test
  public void testModeSet() {
    model.modeSet(ConnectFourModel.TWIN);
    model.reset();
    model.modeSet(ConnectFourModel.TWIN);
    assertEquals(model.getState(), ConnectFourModel.START);
    assertEquals(model.getWinState(), -1);
  }
}
