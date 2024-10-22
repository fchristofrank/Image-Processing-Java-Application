package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;
import ime.model.image.SimpleImage;
import java.util.List;

public class Combine implements MultipleImageOperation {

  public Image apply(List<Image> images, String... args)
          throws IllegalArgumentException {

    Image redImage = images.get(0);
    Image greenImage = images.get(1);
    Image blueImage = images.get(2);

    if (redImage.getHeight() != greenImage.getHeight() ||
            greenImage.getHeight() != blueImage.getHeight() ||
            redImage.getWidth() != greenImage.getWidth() ||
            greenImage.getWidth() != blueImage.getWidth()) {
      throw new IllegalArgumentException("Dimensions cannot be different for images");
    }

    int height = redImage.getHeight();
    int width = redImage.getWidth();
    Image outputImage = new SimpleImage(height, width, ImageType.RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int redValue = redImage.getPixel(i, j).getRed();
        int greenValue = greenImage.getPixel(i, j).getGreen();
        int blueValue = blueImage.getPixel(i, j).getBlue();

        Pixel rgbPixel = PixelFactory.createPixel(ImageType.RGB, redValue, greenValue, blueValue);

        outputImage.setPixel(i, j, rgbPixel);
      }
    }

    return outputImage;
  }
}
