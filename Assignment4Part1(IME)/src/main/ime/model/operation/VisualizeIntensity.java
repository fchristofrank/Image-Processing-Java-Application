package ime.model.operation;

import ime.model.pixel.Pixel;

/**
 * This class visualizes the intensity of an image by extending the AbstractVisualize class. It
 * focuses on converting each pixel's intensity value into a grayscale representation, where the
 * output image will reflect the brightness of the original pixels based on their intensity levels.
 * The intensity is calculated as a weighted average of the red, green, and blue components,
 * representing the perceived brightness of each pixel.
 */
public class VisualizeIntensity extends AbstractVisualize {

  /**
   * Retrieves the intensity of the provided pixel. This method overrides the getColorComponent
   * method from the AbstractVisualize class, specifying that the pixel's intensity should be used
   * for visualization.
   *
   * @param pixel the pixel from which the intensity value is extracted.
   * @return the integer value of the intensity of the pixel.
   */
  @Override
  protected int getColorComponent(Pixel pixel) {
    return Math.round(pixel.getIntensity());
  }
}
