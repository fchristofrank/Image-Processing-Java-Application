package ime.operations;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.models.ImageType;
import ime.models.PixelFactory;
import ime.models.SimpleImage;

public class AdjustBrightness extends ImageOperationManager {

  public AdjustBrightness(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  public void apply(String[] args) throws IllegalArgumentException, IOException {
    int alpha = Integer.parseInt(args[0]);
    String inputName = args[1];
    String outputName = args[2];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
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
    addImage(outputName, outputImage);
    System.out.println("Adjusting Brightness. New image created: " + outputName);
  }

  @Override
  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
    try {
      Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Invalid brightness adjustment value '%s'." +
              " It must be an integer.", args[0]));
    }
  }
}
