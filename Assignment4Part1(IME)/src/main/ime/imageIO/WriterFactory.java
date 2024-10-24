package ime.imageIO;

import java.util.Objects;

public class WriterFactory {
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
