package edu.nyu.pqs.canvas.content;

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

  public String getType() {
    return contentType;
  }

  public String getColor() {
    return contentColor;
  }

  public int[] getTop() {
    return new int[] {topX, topY};
  }

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

  public int hashCode() {
    int result = contentType.hashCode();
    result = 23 * result + contentColor.hashCode();
    result = 17 * result + topX;
    result = 19 * result + topY;
    return result;
  }

  public String toString() {
    return contentType + ": \n" + contentColor + "\n" + "topX = " + topX + "\n" + "topY = " + topY
        + "\n";
  }
}
