package ime.model.operation;

import ime.model.pixel.Pixel;

/**
 * This class is responsible for visualizing the green component of an image.
 * It extends the AbstractVisualize class, which provides a framework for applying
 * visual operations to images. The primary function of this class is to isolate
 * and retrieve the green color channel from each pixel in the image.
 * The resulting visualized output will consist solely of the green component,
 * while the other color channels (red and blue) will not influence the pixel values
 * in the output image.
 */
public class VisualizeGreen extends AbstractVisualizeWithPreview {

  /**
   * Extracts the green component from the provided pixel.
   * This method overrides the getColorComponent method from the AbstractVisualize class,
   * specifying that the green channel should be used for visualization.
   *
   * @param pixel the pixel from which the green color component is extracted.
   * @return the integer value of the green component of the pixel.
   */
  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getGreen();
  }
}
