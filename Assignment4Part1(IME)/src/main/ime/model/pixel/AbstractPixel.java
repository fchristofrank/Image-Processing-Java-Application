package ime.model.pixel;


/**
 * This abstract class represents a pixel, holding the values for the red, green, and blue
 * components of a pixel. It serves as a base class for specific pixel implementations (such as
 * RGBPixel, RGBAPixel etc.,) that define how color pixels are represented and accessed.
 */
public abstract class AbstractPixel implements Pixel {

  private static final int PIXEL_LOWER_LIMIT = 0;
  private static final int PIXEL_UPPER_LIMIT = 255;
  private final int red;
  private final int green;
  private final int blue;

  /**
   * a public constructor to generate a pixel with red, green, blue values.
   *
   * @param red   value of the red pixel.
   * @param green value of the green pixel.
   * @param blue  value of the blue pixel.
   */
  public AbstractPixel(int red, int green, int blue) {
    this.red = clamp(red);
    this.green = clamp(green);
    this.blue = clamp(blue);
  }

  public int getRed() {
    return this.red;
  }

  public int getGreen() {
    return this.green;
  }

  public int getBlue() {
    return this.blue;
  }

  /**
   * This method normalizes pixel channel values to ensure they remain within the valid range.
   *
   * @param value the value of the channel which has to be clamped.
   * @return the clamped value of the channel.
   */
  public int clamp(int value) {
    return Math.max(
        PIXEL_LOWER_LIMIT, Math.min(PIXEL_UPPER_LIMIT, value));
  }
}
