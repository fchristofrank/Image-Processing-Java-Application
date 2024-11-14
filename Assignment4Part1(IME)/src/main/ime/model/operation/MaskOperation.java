package ime.model.operation;

import java.util.List;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;

public class MaskOperation implements MultipleImageOperation {

  @Override
  public Image apply(List<Image> images, String... args) throws IllegalArgumentException {
    Image inputImage = images.get(0);
    Image maskImage = images.get(1);
    Image outputImage = images.get(2);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Pixel[][] pixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel maskPixel = maskImage.getPixel(i, j);

        if (maskPixel.getRed() == 0 && maskPixel.getGreen() == 0 && maskPixel.getBlue() == 0) {
          pixels[i][j] = outputImage.getPixel(i, j);
        } else {
          pixels[i][j] = inputImage.getPixel(i, j);
        }
      }
    }

    return new SimpleImage(height, width, ImageType.RGB, pixels);
  }
}
