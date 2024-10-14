package ime;

import java.util.Objects;

/**
 * The PixelFactory class is responsible for creating Pixel objects with specified component values.
 */
public class PixelFactory {

  /**
   * Creates a pixel based on the specified image type and color values.
   *
   * @param imageType the type of image (e.g., RGB, Grayscale, etc.).
   * @param r         the red component of the pixel's color.
   * @param g         the green component of the pixel's color.
   * @param b         the blue component of the pixel's color.
   * @return a Pixel object initialized with the specified color components based on image type.
   * @throws IllegalArgumentException when the image type is invalid.
   */

  public static Pixel createPixel(ImageType imageType, int r, int g, int b) throws IllegalArgumentException {
    if (Objects.requireNonNull(imageType) == ImageType.RGB) {
      return new RGBPixel(r, g, b);
    }
    throw new IllegalArgumentException("Unsupported image type: " + imageType);
  }
}
