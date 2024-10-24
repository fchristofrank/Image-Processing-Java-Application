package ime.imageIO;

import java.util.Objects;

/**
 * A factory class for creating instances of {@link ImageWriter} based on the
 * specified {@link ImageFormat}.
 * This class is responsible for determining the appropriate writer implementation for
 * various image formats.
 */
public class WriterFactory {

  /**
   * Creates an instance of {@link ImageWriter} based on the provided {@link ImageFormat}.
   *
   * <p>Supported formats include:
   * <ul>
   *   <li>PNG: returns an instance of {@link PNGImageWriter}</li>
   *   <li>JPG: returns an instance of {@link JPGImageWriter}</li>
   *   <li>PPM: returns an instance of {@link PPMImageWriter}</li>
   * </ul>
   *
   * @param imageFormat the format of the image (e.g., PNG, JPG, PPM)
   * @return an instance of the corresponding {@link ImageWriter} for the given format
   * @throws IllegalArgumentException if the provided image format is unsupported or null
   */
  public static ImageWriter createWriter(ImageFormat imageFormat) throws IllegalArgumentException {
    if (Objects.requireNonNull(imageFormat) == ImageFormat.PNG) {
      return new PNGImageWriter();
    } else if (Objects.requireNonNull(imageFormat) == ImageFormat.JPG) {
      return new JPGImageWriter();
    } else if (Objects.requireNonNull(imageFormat) == ImageFormat.PPM) {
      return new PPMImageWriter();
    } else {
      throw new IllegalArgumentException("Unsupported image format.");
    }
  }
}
