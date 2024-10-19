package ime.imageIO;

import java.util.Objects;

/**
 * The ReaderFactory class is responsible for creating appropriate Reader objects
 * based on the given image format.
 */
public class ReaderFactory {
  /**
   * Creates and returns a Reader object appropriate for the specified image format.
   *
   * @param imageFormat The ImageFormat enum representing the format of the image to be read.
   * @return A Reader object capable of reading the specified image format.
   * @throws IllegalArgumentException If the provided image format is not supported.
   */
  public static Reader createrReader(ImageFormat imageFormat) throws IllegalArgumentException {
    if (Objects.requireNonNull(imageFormat) == ImageFormat.PNG || Objects.requireNonNull(imageFormat) == ImageFormat.JPG) {
      return new JpgPngReader();
    } else if (Objects.requireNonNull(imageFormat) == ImageFormat.PPM) {
      return new PPMReader();
    } else {
      throw new IllegalArgumentException("Unsupported image format.");
    }
  }
}
