package ime.operations;

import java.io.IOException;

import ime.models.Image;
import ime.models.ImageType;
import ime.models.PixelFactory;
import ime.models.SimpleImage;

public class ApplyBrightness implements ImageOperation {
  @Override
  public Image apply(Image inputImage, String[] args) throws IllegalArgumentException, IOException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    int alpha = Integer.parseInt(args[0]);
    Image outputImage = new SimpleImage(height, width, ImageType.RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = inputImage.getPixel(i, j).getRed() + alpha;
        int green = inputImage.getPixel(i, j).getGreen() + alpha;
        int blue = inputImage.getPixel(i, j).getBlue() + alpha;
        outputImage.setPixel(i, j, PixelFactory.createPixel(outputImage.getType(), red, green,
                blue));
      }
    }
    return outputImage;
  }
}
