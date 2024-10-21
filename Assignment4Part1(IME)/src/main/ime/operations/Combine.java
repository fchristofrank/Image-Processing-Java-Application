package ime.operations;

import java.io.IOException;

import ime.controller.Load;
import ime.models.Image;
import ime.models.ImageType;
import ime.models.Pixel;
import ime.models.PixelFactory;
import ime.models.SimpleImage;

import static java.lang.System.load;

public class Combine implements MultipleImageOperation {

  public Image apply(Image redImage, Image blueImage, Image greenImage, Image... optional)
          throws IllegalArgumentException {

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
