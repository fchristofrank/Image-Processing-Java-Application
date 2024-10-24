package ime.imageio;

import java.util.Objects;

/**
 * The ReaderFactory class is responsible for creating appropriate Reader objects
 * based on the given image format.
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
    if (Objects.requireNonNull(imageFormat) == ImageFormat.PNG
            || Objects.requireNonNull(imageFormat) == ImageFormat.JPG) {
      return new StandardImageReader();
    } else if (Objects.requireNonNull(imageFormat) == ImageFormat.PPM) {
      return new PPMImageReader();
    } else {
      throw new IllegalArgumentException("Unsupported image format.");
    }
  }
}
