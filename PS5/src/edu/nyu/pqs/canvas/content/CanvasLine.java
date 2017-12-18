package edu.nyu.pqs.canvas.content;

public class CanvasLine extends CanvasContent {
  private final int bottomX;
  private final int bottomY;

  public CanvasLine(int x, int y, int bottomX, int bottomY, String color) {
    super("Line", x, y, color);
    this.bottomX = bottomX;
    this.bottomY = bottomY;
  }

  public int[] getBottom() {
    return new int[] {bottomX, bottomY};
  }

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

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 17 * result + bottomX;
    result = 19 * result + bottomY;
    return result;
  }

  @Override
  public String toString() {
    return super.toString() + "bottomX = " + bottomX + "\n" + "bottomY = " + bottomY + "\n";
  }
}
