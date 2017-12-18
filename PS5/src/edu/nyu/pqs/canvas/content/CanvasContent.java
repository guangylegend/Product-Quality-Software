package edu.nyu.pqs.canvas.content;

/**
 * This is the abstract canvas content interface. It stores the meta data for a canvas content like
 * type, color and the top point.
 * 
 * @author Guang Yang
 * @version 1.0
 */

public abstract class CanvasContent {
  private final String contentType;
  private final String contentColor;
  private final int topX;
  private final int topY;

  public CanvasContent(String type, int x, int y, String color) {
    contentType = type;
    contentColor = color;
    topX = x;
    topY = y;
  }

  /**
   * @return The type of the content.
   * 
   */
  public String getType() {
    return contentType;
  }

  /**
   * @return The color of the content.
   * 
   */
  public String getColor() {
    return contentColor;
  }

  /**
   * @return The top point of the content.
   * 
   */
  public int[] getTop() {
    return new int[] {topX, topY};
  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CanvasContent content = (CanvasContent) o;

    if (getType() != content.getType()) {
      return false;
    }
    if (getColor() != content.getColor()) {
      return false;
    }
    if (getTop()[0] != content.getTop()[0] || getTop()[1] != content.getTop()[1]) {
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
    int result = contentType.hashCode();
    result = 23 * result + contentColor.hashCode();
    result = 17 * result + topX;
    result = 19 * result + topY;
    return result;
  }

  /**
   * @return The string representation of the content.
   * 
   */
  @Override
  public String toString() {
    return contentType + ": \n" + contentColor + "\n" + "topX = " + topX + "\n" + "topY = " + topY
        + "\n";
  }
}
