package edu.nyu.pqs.canvas.content;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CanvasRectTest {
  CanvasContent Rect;

  @Before
  public void setUp() {
    Rect = new CanvasRect(0, 0, 15, 20, "Red");
  }

  @Test
  public void getColorTest() {
    assertEquals(Rect.getColor(), "Red");
  }

  @Test
  public void getTypeTest() {
    assertEquals(Rect.getType(), "Rect");
  }

  @Test
  public void getTopTest() {
    assertEquals(Rect.getTop()[0], 0);
    assertEquals(Rect.getTop()[1], 0);
  }

  @Test
  public void getWidthTest() {
    CanvasRect Rectangle = (CanvasRect) Rect;
    assertEquals(Rectangle.getWidth(), 15);
  }

  @Test
  public void getLengthTest() {
    CanvasRect Rectangle = (CanvasRect) Rect;
    assertEquals(Rectangle.getLength(), 20);
  }

  @Test
  public void equalsTest() {
    assertEquals(Rect, Rect);
    assertEquals(Rect, new CanvasRect(0, 0, 15, 20, "Red"));
    assertNotEquals(Rect, null);
    assertNotEquals(Rect, new Object());
    assertNotEquals(Rect, new CanvasRect(0, 1, 15, 20, "Red"));
    assertNotEquals(Rect, new CanvasRect(1, 0, 15, 20, "Red"));
    assertNotEquals(Rect, new CanvasRect(0, 0, 15, 20, "Yellow"));
    assertNotEquals(Rect, new CanvasRect(0, 0, 16, 20, "Red"));
    assertNotEquals(Rect, new CanvasRect(0, 0, 15, 15, "Red"));
    assertNotEquals(Rect, new CanvasOval(0, 0, 15, 20, "Red"));
  }

  @Test
  public void hashCodeTest() {
    assertEquals(Rect.hashCode(), -822006720);
  }

  @Test
  public void toStringTest() {
    String expectedString =
        "Rect: \n" + "Red\n" + "topX = 0\n" + "topY = 0\n" + "length = 20\n" + "width = 15\n";
    assertEquals(Rect.toString(), expectedString);
  }
}
