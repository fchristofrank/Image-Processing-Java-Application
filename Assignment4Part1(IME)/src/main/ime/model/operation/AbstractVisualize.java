package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;

/**
 * Abstract class that defines the structure for visualizing operations on an image. This class
 * implements the core logic of an image transformation where a specific color component (such as
 * red, green, or blue) is extracted from each pixel in the input image, and a new image is created
 * based on that color component. Each pixel in the output image is generated by using the same
 * value for red, green, and blue channels, resulting in a grayscale representation where the
 * intensity corresponds to the extracted color component.
 */
public abstract class AbstractVisualize implements ImageOperation {

  /**
   * Applies a visualization operation to the input image. The operation processes each pixel in the
   * input image, extracts a specific color component (as determined by the concrete subclass), and
   * creates a grayscale output image where all three color channels (red, green, and blue) are set
   * to the extracted component value. The output image has the same dimensions and type as the
   * input image. The method iterates through each pixel in the input image, extracts the required
   * color component, and assigns this value to the corresponding pixel in the output image,
   * ensuring that all three color channels in the output are the same, giving a grayscale effect.
   *
   * @param inputImage the image to which the visualization operation is applied.
   * @param args       additional arguments for the operation.
   * @return the output image, which is a grayscale version based on the extracted color component.
   * @throws IllegalArgumentException if the input image is null or contains invalid pixel data.
   */
  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    Pixel[][] pixels = new Pixel[inputImage.getHeight()][inputImage.getWidth()];
    processImage(inputImage, pixels, args);
    return new SimpleImage(
        inputImage.getHeight(), inputImage.getWidth(), inputImage.getType(), pixels);
  }

  protected void processImage(Image inputImage, Pixel[][] pixels, String... args) {
    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        int colorValue = getColorComponent(inputImage.getPixel(i, j));
        pixels[i][j] =
            PixelFactory.createPixel(inputImage.getType(), colorValue, colorValue, colorValue);
      }
    }
  }

  /**
   * Abstract method that must be implemented by subclasses to define which color component (e.g.,
   * red, green, or blue) should be extracted from a pixel.
   *
   * @param pixel the pixel from which the color component is to be extracted.
   * @return the integer value of the selected color component for the pixel.
   */
  protected abstract int getColorComponent(Pixel pixel);
}
