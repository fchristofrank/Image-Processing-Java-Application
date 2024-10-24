package ime.model.operation;

import ime.model.pixel.Pixel;

/**
 * This class visualizes the red component of an image by extending the AbstractVisualize class. The
 * visualization focuses on extracting the red channel from each pixel, allowing for a
 * representation that emphasizes the red hues present in the original image. The output will be a
 * grayscale image where pixel values are determined by their red component intensity.
 */
public class VisualizeRed extends AbstractVisualize {

  /**
   * Retrieves the red value of the provided pixel. This method overrides the getColorComponent
   * method from the AbstractVisualize class, specifying that the pixel's red channel should be used
   * for visualization.
   *
   * @param pixel the pixel from which the red value is extracted.
   * @return the integer value of the red component of the pixel.
   */
  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getRed();
  }
}
