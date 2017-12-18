package edu.nyu.pqs.canvas.content;

/**
 * This is the implementation of the line content for the canvas. It extends the abstract content
 * class which stores the meta data for the content, and has two more attributes which are two
 * coordinates of the second point.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public class CanvasLine extends CanvasContent {
  private final int bottomX;
  private final int bottomY;

  public CanvasLine(int x, int y, int bottomX, int bottomY, String color) {
    super("Line", x, y, color);
    this.bottomX = bottomX;
    this.bottomY = bottomY;
  }

  /**
   * @return The second point of the line.
   * 
   */
  public int[] getBottom() {
    return new int[] {bottomX, bottomY};
  }

  /**
   * @return The length of the oval.
   * 
   */
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }

    CanvasLine line = (CanvasLine) o;

    if (getBottom()[0] != line.getBottom()[0] || getBottom()[1] != line.getBottom()[1]) {
      return false;
    }

    return true;
  }

  /**
   * @return The length of the oval.
   * 
   */
  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 17 * result + bottomX;
    result = 19 * result + bottomY;
    return result;
  }

  /**
   * @return The length of the oval.
   * 
   */
  @Override
  public String toString() {
    return super.toString() + "bottomX = " + bottomX + "\n" + "bottomY = " + bottomY + "\n";
  }
}
