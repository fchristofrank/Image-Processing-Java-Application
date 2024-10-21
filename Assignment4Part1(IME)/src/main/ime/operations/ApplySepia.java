package ime.operations;

import java.io.IOException;

import ime.models.Image;
import ime.models.ImageType;
import ime.models.PixelFactory;
import ime.models.SimpleImage;

public class ApplySepia implements ImageOperation {

  private static final double[][] SEPIA_MATRIX = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException, IOException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Image outputImage = new SimpleImage(height, width, inputImage.getType());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(i, j).scaleComponents(SEPIA_MATRIX));
      }
    }
    return outputImage;
  }
}
