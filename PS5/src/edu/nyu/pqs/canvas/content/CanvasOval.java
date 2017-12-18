package edu.nyu.pqs.canvas.content;

/**
 * This is the implementation of the oval content for the canvas. It extends the abstract content class which stores
 * the meta data for the content, and has two more attributes which are length and width.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class CanvasOval extends CanvasContent {
  private final int length;
  private final int width;

  public CanvasOval(int x, int y, int width, int length, String color) {
    super("Oval", x, y, color);
    this.width = width;
    this.length = length;
  }

  /**
   * @return The length of the oval.
   * 
   */
  public int getLength() {
    return length;
  }

  /**
   * @return The length of the oval.
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

    CanvasOval oval = (CanvasOval) o;

    if (getLength() != oval.getLength() || getWidth() != oval.getWidth()) {
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
