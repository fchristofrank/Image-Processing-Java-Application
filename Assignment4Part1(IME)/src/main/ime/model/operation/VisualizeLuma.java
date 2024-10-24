package ime.model.operation;

import ime.model.pixel.Pixel;

/**
 * This class visualizes the luma of an image by extending the AbstractVisualize class. The luma
 * value represents the perceived brightness of a pixel, taking into account human sensitivity to
 * different colors. This visualization focuses on providing a grayscale representation where the
 * output pixel values correspond to the luma values of the original image.
 */
public class VisualizeLuma extends AbstractVisualize {

  /**
   * Retrieves the luma of the provided pixel. This method overrides the getColorComponent method
   * from the AbstractVisualize class, specifying that the pixel's luma should be used for
   * visualization.
   *
   * @param pixel the pixel from which the luma value is extracted.
   * @return the integer value of the luma of the pixel.
   */
  @Override
  protected int getColorComponent(Pixel pixel) {
    return (int) pixel.getLuma();
  }
}
