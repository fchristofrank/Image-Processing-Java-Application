package ime.model.operation;

import ime.model.pixel.Pixel;

/**
 * This class visualizes the value component of an image by extending the AbstractVisualize class.
 * The visualization focuses on extracting the value (brightness) from each pixel, allowing for a
 * representation that emphasizes the overall lightness or darkness of the original image. The
 * output will be a grayscale image where pixel values are determined by their brightness levels.
 */
public class VisualizeValue extends AbstractVisualizeWithPreview {

  /**
   * Retrieves the value of the provided pixel. This method overrides the getColorComponent method
   * from the AbstractVisualize class, specifying that the pixel's value component should be used
   * for visualization.
   *
   * @param pixel the pixel from which the value is extracted.
   * @return the integer value of the brightness component of the pixel.
   */
  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getValue();
  }
}
