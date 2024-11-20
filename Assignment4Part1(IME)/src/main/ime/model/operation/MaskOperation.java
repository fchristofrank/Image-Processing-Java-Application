package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import java.util.List;

/**
 * This class represents an operation that applies a mask to an image. It takes an input image, a
 * mask image, and an output image, and applies the mask to modify the input image. The mask is used
 * to selectively retain the original image's pixels or replace them with pixels from the output
 * image based on the mask's pixel values.
 */
public class MaskOperation implements MultipleImageOperation {

  //Mask operation has-a greyscale operation.
  private final ImageOperation greyscale;

  /**
   * MaskOperation that initializes the greyscale dependency to convert mask Image to Black and
   * white.
   *
   * @param greyscale operation object that converts to black and white.
   */
  public MaskOperation(ImageOperation greyscale) {
    this.greyscale = greyscale;
  }

  /**
   * Applies the mask operation to the provided images. It uses the mask image to determine which
   * pixels from the input image should be retained and which should be replaced by the output
   * image's pixels.
   *
   * @param images A list of three images: the input image, the mask image, and the output image.
   * @param args   Optional arguments for the operation (not used in this implementation).
   * @return A new image that is the result of applying the mask operation.
   * @throws IllegalArgumentException if the number of provided images is not exactly three.
   */
  @Override
  public Image apply(List<Image> images, String... args) throws IllegalArgumentException {
    if (images.size() != 3) {
      throw new IllegalArgumentException(
          "Exactly three images are required: input, mask, and output.");
    }

    Image inputImage = images.get(0);
    Image maskImage = images.get(1);
    Image outputImage = images.get(2);

    //Convert maskImage into greyscale
    maskImage = greyscale.apply(maskImage);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Pixel[][] pixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel maskPixel = maskImage.getPixel(i, j);

        // If the mask pixel is black (0, 0, 0), use the output image's pixel, otherwise use the input image's pixel
        if (maskPixel.getRed() == 0 && maskPixel.getGreen() == 0 && maskPixel.getBlue() == 0) {
          pixels[i][j] = outputImage.getPixel(i, j);
        } else {
          pixels[i][j] = inputImage.getPixel(i, j);
        }
      }
    }

    return new SimpleImage(height, width, ImageType.RGB, pixels);
  }
}