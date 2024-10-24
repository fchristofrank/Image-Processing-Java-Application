package ime.imageIO;

import java.awt.image.BufferedImage;

public interface ImageWriter {
  void writeImage(BufferedImage image, String outputFileName);
}
