package Core;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AITest {
  private Board board;

  @Before
  public void setUp() {
    board = new Board();
  }

  private void clearBoard() {
    int[][] boardForTest = board.getBoardForTest();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        boardForTest[i][j] = 0;
      }
    }
  }
  
  private void fillBoard(int player) {
    int[][] boardForTest = board.getBoardForTest();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        boardForTest[i][j] = player;
      }
    }
  }
  

  @Test
  public void testWinning() {
    int[][] boardForTest = board.getBoardForTest();
    
    boardForTest[5][0] = 1;
    boardForTest[5][1] = 1;
    boardForTest[5][2] = 1;
    assertEquals(AI.getNextMove(boardForTest, 1), 3);
    
    clearBoard();
    boardForTest[5][0] = 1;
    boardForTest[4][0] = 1;
    boardForTest[3][0] = 1;
    assertEquals(AI.getNextMove(boardForTest, 1), 0);
    
    clearBoard();
    boardForTest[5][0] = 1;
    boardForTest[4][1] = 1;
    boardForTest[3][2] = 1;
    boardForTest[5][3] = 1;
    boardForTest[4][3] = 2;
    boardForTest[3][3] = 2;
    assertEquals(AI.getNextMove(boardForTest, 1), 3);
    
    clearBoard();
    boardForTest[5][0] = 1;
    boardForTest[4][1] = 1;
    boardForTest[3][2] = 1;
    boardForTest[5][3] = 1;
    boardForTest[4][3] = 1;
    boardForTest[3][3] = 1;
    assertEquals(AI.getNextMove(boardForTest, 1), 3);
    
    clearBoard();
    boardForTest[5][0] = 2;
    boardForTest[4][0] = 2;
    boardForTest[3][0] = 1;
    boardForTest[3][1] = 1;
    boardForTest[4][2] = 1;
    boardForTest[5][3] = 1;
    assertEquals(AI.getNextMove(boardForTest, 1), 0);
    
    clearBoard();
    boardForTest[5][0] = 2;
    boardForTest[4][1] = 2;
    boardForTest[3][2] = 1;
    boardForTest[3][4] = 1;
    boardForTest[4][5] = 1;
    boardForTest[5][6] = 1;
    boardForTest[5][3] = 1;
    boardForTest[4][3] = 1;
    boardForTest[3][3] = 2;
    assertEquals(AI.getNextMove(boardForTest, 1), 3);
  }

  @Test
  public void testPreventWinning() {
    int[][] boardForTest = board.getBoardForTest();
    
    boardForTest[5][0] = 1;
    boardForTest[5][1] = 1;
    boardForTest[5][2] = 1;
    assertEquals(AI.getNextMove(boardForTest, 2), 3);
    
    clearBoard();
    boardForTest[5][0] = 1;
    boardForTest[4][0] = 1;
    boardForTest[3][0] = 1;
    assertEquals(AI.getNextMove(boardForTest, 2), 0);
    
    clearBoard();
    boardForTest[5][0] = 1;
    boardForTest[4][1] = 1;
    boardForTest[3][2] = 1;
    boardForTest[5][3] = 1;
    boardForTest[4][3] = 2;
    boardForTest[3][3] = 2;
    assertEquals(AI.getNextMove(boardForTest, 2), 3);
    
    clearBoard();
    boardForTest[5][0] = 1;
    boardForTest[4][1] = 1;
    boardForTest[3][2] = 1;
    boardForTest[5][3] = 1;
    boardForTest[4][3] = 1;
    boardForTest[3][3] = 1;
    assertEquals(AI.getNextMove(boardForTest, 2), 3);
    
    clearBoard();
    boardForTest[5][0] = 2;
    boardForTest[4][0] = 2;
    boardForTest[3][0] = 1;
    boardForTest[3][1] = 1;
    boardForTest[4][2] = 1;
    boardForTest[5][3] = 1;
    assertEquals(AI.getNextMove(boardForTest, 2), 0);
  }
  
  @Test
  public void testFull() {
    int[][] boardForTest = board.getBoardForTest();
    
    fillBoard(1);
    boardForTest[0][6] = 0;
    assertEquals(AI.getNextMove(boardForTest, 1), 6);
    
    fillBoard(1);
    boardForTest[0][0] = 0;
    assertEquals(AI.getNextMove(boardForTest, 1), 0);
    
    fillBoard(1);
    assertEquals(AI.getNextMove(boardForTest, 1), 0);
  }
  
  @Test
  public void testMove() {
    int[][] boardForTest = board.getBoardForTest();
    
    boardForTest[5][0] = 1;
    boardForTest[5][1] = 1;
    assertEquals(AI.getNextMove(boardForTest, 1), 2);
    
    clearBoard();
    boardForTest[5][0] = 1;
    boardForTest[4][0] = 1;
    assertEquals(AI.getNextMove(boardForTest, 1), 0);
  }
  
}
