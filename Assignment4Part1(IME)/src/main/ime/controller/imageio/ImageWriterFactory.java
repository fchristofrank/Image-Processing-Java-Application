package ime.controller.imageio;

import java.util.Objects;

/**
 * A factory class for creating instances of {@link ImageWriter} based on the specified {@link
 * ImageFormat}. This class is responsible for determining the appropriate writer implementation for
 * various image formats.
 */
public class ImageWriterFactory {

  /**
   * Creates an instance of {@link ImageWriter} based on the provided {@link ImageFormat}.
   *
   * <p>Supported formats include:
   *
   * <ul>
   *   <li>PNG: returns an instance of {@link PNGImageWriter}
   *   <li>JPG: returns an instance of {@link JPGImageWriter}
   *   <li>PPM: returns an instance of {@link PPMImageWriter}
   * </ul>
   *
   * @param imageFormat the format of the image (e.g., PNG, JPG, PPM)
   * @return an instance of the corresponding {@link ImageWriter} for the given format
   * @throws IllegalArgumentException if the provided image format is unsupported or null
   */
  public static ImageWriter createWriter(ImageFormat imageFormat) throws IllegalArgumentException {
    Objects.requireNonNull(imageFormat, "Image format cannot be null");
    switch (imageFormat) {
      case PNG:
        return new PNGImageWriter();
      case JPG:
        return new JPGImageWriter();
      case PPM:
        return new PPMImageWriter();
      default:
        throw new IllegalArgumentException("Unsupported image format.");
    }
  }
}
