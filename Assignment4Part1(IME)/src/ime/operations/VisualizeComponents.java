package ime.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

import ime.models.Image;
import ime.models.Pixel;
import ime.models.RGBPixel;

public class VisualizeComponents implements ImageOperation {

  private static final Logger LOGGER = Logger.getLogger(VisualizeComponents.class.getName());

  public Image apply(Image image, String visualizeColor) {

    LOGGER.log(Level.INFO, "Visualizing with color option :: {0}", visualizeColor);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int colorValue = getColorComponent(image.getPixel(i, j), visualizeColor);
        image.setPixel(i, j, new RGBPixel(colorValue, colorValue, colorValue));
      }
    }

    return image;
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
