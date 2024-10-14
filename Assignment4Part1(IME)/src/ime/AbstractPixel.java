package ime;

/**
 * This abstract class represents a pixel, holding the values for the red, green, and blue
 * components of a pixel. It serves as a base class for specific pixel implementations
 * (such as RGBPixel and RGBAPixel) that define how color pixels are represented and accessed.
 */
public abstract class AbstractPixel implements Pixel {
  private final int red;
  private final int green;
  private final int blue;

  /**
   * This method creates an Abstract Pixel with the given values for the RGB components.
   *
   * @param red   the value of the red component.
   * @param green the value of the green component.
   * @param blue  the value of the blue component.
   */
  public AbstractPixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

}
