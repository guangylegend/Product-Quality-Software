package edu.nyu.pqs.canvas.content;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class CanvasOvalTest {
  CanvasContent oval;

  @Before
  public void setUp() {
    oval = new CanvasOval(0, 0, 15, 20, "Red");
  }

  @Test
  public void getColorTest() {
    assertEquals(oval.getColor(), "Red");
  }

  @Test
  public void getTypeTest() {
    assertEquals(oval.getType(), "Oval");
  }

  @Test
  public void getTopTest() {
    assertEquals(oval.getTop()[0], 0);
    assertEquals(oval.getTop()[1], 0);
  }

  @Test
  public void getWidthTest() {
    CanvasOval Oval = (CanvasOval) oval;
    assertEquals(Oval.getWidth(), 15);
  }

  @Test
  public void getLengthTest() {
    CanvasOval Oval = (CanvasOval) oval;
    assertEquals(Oval.getLength(), 20);
  }
  
  @Test
  public void equalsTest() {
    assertEquals(oval, oval);
    assertEquals(oval, new CanvasOval(0, 0, 15, 20, "Red"));
    assertNotEquals(oval, null);
    assertNotEquals(oval, new Object());
    assertNotEquals(oval, new CanvasOval(0, 1, 15, 20, "Red"));
    assertNotEquals(oval, new CanvasOval(1, 0, 15, 20, "Red"));
    assertNotEquals(oval, new CanvasOval(0, 0, 15, 20, "Yellow"));
    assertNotEquals(oval, new CanvasOval(0, 0, 16, 20, "Red"));
    assertNotEquals(oval, new CanvasOval(0, 0, 15, 15, "Red"));
    assertNotEquals(oval, new CanvasRect(0, 0, 15, 20, "Red"));
  }

  @Test
  public void hashCodeTest() {
    assertEquals(oval.hashCode(), -151092686);
  }

  @Test
  public void toStringTest() {
    String expectedString =
        "Oval: \n" + "Red\n" + "topX = 0\n" + "topY = 0\n" + "length = 20\n" + "width = 15\n";
    assertEquals(oval.toString(), expectedString);
  }
}
