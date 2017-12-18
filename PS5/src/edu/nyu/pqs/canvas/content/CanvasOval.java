package edu.nyu.pqs.canvas.content;

public class CanvasOval extends CanvasContent {
  private final int length;
  private final int width;

  public CanvasOval(int x, int y, int width, int length, String color) {
    super("Oval", x, y, color);
    this.width = width;
    this.length = length;
  }

  public int getLength() {
    return length;
  }

  public int getWidth() {
    return width;
  }

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

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 17 * result + length;
    result = 19 * result + width;
    return result;
  }

  @Override
  public String toString() {
    return super.toString() + "length = " + length + "\n" + "width = " + width + "\n";
  }
}
