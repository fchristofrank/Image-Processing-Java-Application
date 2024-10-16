package ime.imageIO;

import java.util.Objects;

public class ReaderFactory {
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
