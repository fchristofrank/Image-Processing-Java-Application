package ime.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

import ime.models.Image;
import ime.models.ImageType;
import ime.models.Pixel;
import ime.models.RGBPixel;
import ime.models.SimpleImage;

public class Visualize extends ImageOperationManager {

  private static final Logger LOGGER = Logger.getLogger(Visualize.class.getName());

  @Override
  public void apply(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    LOGGER.log(Level.INFO, "Visualizing with color option :: {0}", "red");
    String inputImageName = args[0];
    String outputImageName = args[1];
    Image inputImage = getImage(inputImageName);
    Image outputImage = new SimpleImage(inputImage.getHeight(), inputImage.getWidth(), ImageType.RGB);
    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        int colorValue = getColorComponent(inputImage.getPixel(i, j), "red");
        outputImage.setPixel(i, j, new RGBPixel(colorValue, colorValue, colorValue));
      }
    }
    addImage(outputImageName, outputImage);
  }

  private int getColorComponent(Pixel pixel, String visualizeColor) {
    return switch (visualizeColor.toLowerCase()) {
      case "green" -> pixel.getGreen();
      case "blue" -> pixel.getBlue();
      case "red" -> pixel.getRed();
      default -> throw new IllegalArgumentException("Operation Parameter not supported.");
    };
  }

}
