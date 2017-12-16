package Core;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
  private Board board;

  @Before
  public void setUp() {
    board = new Board();
  }

  @Test
  public void testCheckHorizontal() {
    int[][] boardForTest = board.getBoardForTest();
    boardForTest[0][0] = 1;
    boardForTest[0][1] = 1;
    boardForTest[0][2] = 1;
    assertFalse(board.check(1));
    boardForTest[0][3] = 1;
    assertTrue(board.check(1));
  }

  @Test
  public void testCheckVertical() {
    int[][] boardForTest = board.getBoardForTest();
    boardForTest[0][0] = 1;
    boardForTest[1][0] = 1;
    boardForTest[2][0] = 1;
    assertFalse(board.check(1));
    boardForTest[3][0] = 1;
    assertTrue(board.check(1));
  }

  @Test
  public void testCheckDiagonal() {
    int[][] boardForTest = board.getBoardForTest();
    boardForTest[0][0] = 1;
    boardForTest[1][1] = 1;
    boardForTest[2][2] = 1;
    assertFalse(board.check(1));
    boardForTest[3][3] = 1;
    assertTrue(board.check(1));
  }

  @Test
  public void testCheckAntiDiagonal() {
    int[][] boardForTest = board.getBoardForTest();
    boardForTest[0][3] = 1;
    boardForTest[1][2] = 1;
    boardForTest[2][1] = 1;
    assertFalse(board.check(1));
    boardForTest[3][0] = 1;
    assertTrue(board.check(1));
  }

  @Test
  public void testMark() {
    assertFalse(board.mark(-1, 1));
    assertFalse(board.mark(board.getLength(), 1));
    int[][] boardForTest = board.getBoardForTest();
    for (int i = 1; i < 6; i++) {
      boardForTest[i][0] = 1;
    }
    assertTrue(board.mark(0, 1));
    assertEquals(boardForTest[0][0], 1);
    assertFalse(board.mark(0, 1)); // this column is full
  }

  @Test
  public void testFull() {
    assertFalse(board.isFull());
    int[][] boardForTest = board.getBoardForTest();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        boardForTest[i][j] = 1;
      }
    }
    assertTrue(board.isFull());
  }

  @Test
  public void testGetWidth() {
    assertEquals(board.getWidth(), 6);
  }

  @Test
  public void testGetLength() {
    assertEquals(board.getLength(), 7);
  }

  @Test
  public void testGetBoard() {
    int[][] map = board.getBoard();
    for (int i = 0; i < board.getWidth(); i++) {
      for (int j = 0; j < board.getLength(); j++) {
        assertEquals(map[i][j], 0);
      }
    }
  }

  @Test
  public void testToString() {
    String expectedString = "Board:\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0";
    assertEquals(board.toString(), expectedString);
  }
}
