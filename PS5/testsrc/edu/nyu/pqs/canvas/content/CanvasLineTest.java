package edu.nyu.pqs.canvas.content;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CanvasLineTest {
  CanvasContent line;

  @Before
  public void setUp() {
    line = new CanvasLine(0, 0, 15, 20, "Red");
  }

  @Test
  public void getColorTest() {
    assertEquals(line.getColor(), "Red");
  }

  @Test
  public void getTypeTest() {
    assertEquals(line.getType(), "Line");
  }

  @Test
  public void getTopTest() {
    assertEquals(line.getTop()[0], 0);
    assertEquals(line.getTop()[1], 0);
  }

  @Test
  public void getBottomTest() {
    CanvasLine Line = (CanvasLine)line;
    assertEquals(Line.getBottom()[0], 15);
    assertEquals(Line.getBottom()[1], 20);
  }
  
  @Test
  public void equalsTest() {
    assertEquals(line, line);
    assertEquals(line, new CanvasLine(0, 0, 15, 20, "Red"));
    assertNotEquals(line, null);
    assertNotEquals(line, new Object());
    assertNotEquals(line, new CanvasLine(0, 1, 15, 20, "Red"));
    assertNotEquals(line, new CanvasLine(1, 0, 15, 20, "Red"));
    assertNotEquals(line, new CanvasLine(0, 0, 15, 20, "Yellow"));
    assertNotEquals(line, new CanvasLine(0, 0, 16, 20, "Red"));
    assertNotEquals(line, new CanvasLine(0, 0, 15, 15, "Red"));
    assertNotEquals(line, new CanvasRect(0, 0, 15, 20, "Red"));
  }

  @Test
  public void hashCodeTest() {
    assertEquals(line.hashCode(), 1177979606);
  }

  @Test
  public void toStringTest() {
    String expectedString =
        "Line: \n" + "Red\n" + "topX = 0\n" + "topY = 0\n" + "bottomX = 15\n" + "bottomY = 20\n";
    assertEquals(line.toString(), expectedString);
  }
}
