package ime.model.pixel;

import java.util.Objects;

import ime.model.image.ImageType;

/**
 * The PixelFactory class is responsible for creating Pixel objects with specified component
 * values.
 */
public class PixelFactory {

  /**
   * Creates a Pixel object based on the specified image type and the given color components. This
   * method validates the number of components based on the image type.
   *
   * @param imageType  the type of image (e.g., RGB, Grayscale, etc.).
   * @param components the color components of the pixel.
   * @return a Pixel object initialized with the specified color components based on image type.
   * @throws IllegalArgumentException when the image type is invalid or the number of components is
   *                                  incorrect.
   */
  public static Pixel createPixel(ImageType imageType, int... components)
      throws IllegalArgumentException {
    if (Objects.requireNonNull(imageType) == ImageType.RGB) {
      if (components.length != 3) {
        throw new IllegalArgumentException("RGB image must have 3 components");
      }
      return new RGBPixel(components[0], components[1], components[2]);
    } else {
      throw new IllegalArgumentException("Unsupported image type: " + imageType);
    }
  }
}
