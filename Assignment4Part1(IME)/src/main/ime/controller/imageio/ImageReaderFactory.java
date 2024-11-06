package ime.controller.imageio;

import java.util.Objects;

/**
 * The ReaderFactory class is responsible for creating appropriate Reader objects based on the given
 * image format.
 */
public class ImageReaderFactory {
  /**
   * Creates and returns a Reader object appropriate for the specified image format.
   *
   * @param imageFormat The ImageFormat enum representing the format of the image to be read.
   * @return A Reader object capable of reading the specified image format.
   * @throws IllegalArgumentException If the provided image format is not supported.
   */
  public static ImageReader createReader(ImageFormat imageFormat) throws IllegalArgumentException {
    Objects.requireNonNull(imageFormat, "Image format cannot be null");
    switch (imageFormat) {
      case PNG:
      case JPG:
        return new StandardImageReader();
      case PPM:
        return new PPMImageReader();
      default:
        throw new IllegalArgumentException("Unsupported image format: " + imageFormat);
    }
  }
}
