package edu.nyu.pqs.canvas.content;

/**
 * This is the implementation of the rectangle content for the canvas. It extends the abstract content class which stores
 * the meta data for the content, and has two more attributes which are length and width.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class CanvasRect extends CanvasContent {
  private final int length;
  private final int width;

  public CanvasRect(int x, int y, int width, int length, String color) {
    super("Rect", x, y, color);
    this.width = width;
    this.length = length;
  }

  /**
   * @return The length of the rectangle.
   * 
   */
  public int getLength() {
    return length;
  }

  /**
   * @return The width of the rectangle.
   * 
   */
  public int getWidth() {
    return width;
  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }

    CanvasRect rect = (CanvasRect) o;

    if (getLength() != rect.getLength() || getWidth() != rect.getWidth()) {
      return false;
    }

    return true;
  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 17 * result + length;
    result = 19 * result + width;
    return result;
  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public String toString() {
    return super.toString() + "length = " + length + "\n" + "width = " + width + "\n";
  }
}
