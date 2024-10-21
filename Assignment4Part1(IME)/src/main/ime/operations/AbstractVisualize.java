package ime.operations;

import ime.models.Image;
import ime.models.ImageType;
import ime.models.Pixel;
import ime.models.PixelFactory;
import ime.models.SimpleImage;


public abstract class AbstractVisualize implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    Image outputImage = new SimpleImage(inputImage.getHeight(), inputImage.getWidth(), inputImage.getType());

    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        int colorValue = getColorComponent(inputImage.getPixel(i, j));
        outputImage.setPixel(i, j, PixelFactory.createPixel(inputImage.getType(), colorValue, colorValue, colorValue));
      }
    }
    return outputImage;
  }

  protected abstract int getColorComponent(Pixel pixel);

}
