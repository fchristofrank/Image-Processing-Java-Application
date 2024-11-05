package ime.model.operation;

import ime.model.pixel.Pixel;

/**
 * This class is responsible for visualizing the blue component of an image. It extends the
 * AbstractVisualize class, which provides the framework for applying visual operations to images.
 * The purpose of this class is to isolate and retrieve the blue color channel from each pixel in
 * the image. The visualized output will contain only the blue component, while the other color
 * channels (red and green) will not contribute to the pixel values in the resulting image.
 */
public class VisualizeBlue extends AbstractVisualizeWithPreview {

  /**
   * Extracts the blue component from the given pixel. This method is overridden from the
   * AbstractVisualize class to specify that the blue channel should be used for visualization.
   *
   * @param pixel the pixel from which the blue color component is extracted.
   * @return the integer value of the blue component of the pixel.
   */
  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getBlue();
  }
}
