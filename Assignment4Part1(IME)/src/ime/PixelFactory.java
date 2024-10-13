package ime;

/**
 * The PixelFactory class is responsible for creating Pixel objects
 * with specified color channels.
 */
public class PixelFactory {

  /**
   * Creates an RGB pixel with the specified row, column, and RGB color
   * values.
   *
   * @param row the row index of the pixel.
   * @param col the column index of the pixel.
   * @param r   the red component of the pixel's color.
   * @param g   the green component of the pixel's color.
   * @param b   the blue component of the pixel's color.
   * @return a Pixel object initialized with the specified location and RGB channel.
   */
  public static Pixel createRGBPixel(int row, int col, int r, int g, int b) {
    Channel rgbChannel = new RGBColorChannel(r, g, b);
    return new Pixel(row, col, rgbChannel);
  }
}
