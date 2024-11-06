package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.RGBPixel;
import java.util.List;

/**
 * The Combine class merges three images by combining their red, green, and blue channels into a
 * single RGB image. The first image supplies the red channel, the second supplies the green, and
 * the third supplies the blue. All input images must have the same dimensions.
 */
public class Combine implements MultipleImageOperation {

  /**
   * Combines three images by using their red, green, and blue channels. The images must have the
   * same width and height. The first image provides the red channel, the second image provides the
   * green channel, and the third image provides the blue channel. If the dimensions of the input
   * images do not match, an IllegalArgumentException is thrown.
   *
   * @param images a list of three images to extract red, green and blue channel in order.
   * @param args additional arguments, if any.
   * @return a new RGB image created from the three input images.
   * @throws IllegalArgumentException if the images have different dimensions.
   */
  public Image apply(List<Image> images, String... args) throws IllegalArgumentException {
    validateImageCount(images);

    Image redImage = images.get(0);
    Image greenImage = images.get(1);
    Image blueImage = images.get(2);

    validateSameDimensions(redImage, greenImage, blueImage);

    int height = redImage.getHeight();
    int width = redImage.getWidth();
    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        validateGrayscaleImages(
            redImage.getPixel(i, j), greenImage.getPixel(i, j), blueImage.getPixel(i, j));

        int redValue = redImage.getPixel(i, j).getRed();
        int greenValue = greenImage.getPixel(i, j).getGreen();
        int blueValue = blueImage.getPixel(i, j).getBlue();

        Pixel rgbPixel = createRgbPixel(redValue, greenValue, blueValue);
        pixels[i][j] = rgbPixel;
      }
    }
    return new SimpleImage(height, width, redImage.getType(), pixels);
  }

  /**
   * Validates that the list contains exactly three images. Throws an IllegalArgumentException if
   * the count is not three.
   *
   * @param images the list of images to validate.
   * @throws IllegalArgumentException if the list size is not three.
   */
  private void validateImageCount(List<Image> images) {
    if (images.size() != 3) {
      throw new IllegalArgumentException("Not Enough images for operation. Expected " + 3);
    }
  }

  /**
   * Ensures all images have the same width and height. Throws an IllegalArgumentException if
   * dimensions do not match.
   *
   * @param images the array of images to check for matching dimensions.
   * @throws IllegalArgumentException if any image has a different width or height.
   */
  private void validateSameDimensions(Image... images) {
    int height = images[0].getHeight();
    int width = images[0].getWidth();
    for (Image image : images) {
      if (image.getHeight() != height || image.getWidth() != width) {
        throw new IllegalArgumentException("Dimensions cannot be different for images");
      }
    }
  }

  /**
   * Checks that each pixel is grayscale by validating the equality of red, green, and blue values.
   * Throws an IllegalArgumentException if any pixel is not grayscale.
   *
   * @param pixels the array of pixels to validate as grayscale.
   * @throws IllegalArgumentException if any pixel is not grayscale.
   */
  private void validateGrayscaleImages(Pixel... pixels) {
    for (Pixel pixel : pixels) {
      if (!isGreyscaleImage(pixel)) {
        throw new IllegalArgumentException("Operation is allowed only on greyscale Images.");
      }
    }
  }

  /**
   * Determines if a pixel is grayscale by checking if its red, green, and blue values are equal.
   *
   * @param pixel the pixel to check.
   * @return true if the pixel is grayscale; otherwise, false.
   */
  private boolean isGreyscaleImage(Pixel pixel) {
    return pixel.getRed() == pixel.getGreen() && pixel.getRed() == pixel.getBlue();
  }

  /**
   * Creates a new RGB pixel from specified red, green, and blue values.
   *
   * @param redValue the red component value.
   * @param greenValue the green component value.
   * @param blueValue the blue component value.
   * @return a new RGBPixel with the specified color values.
   */
  private Pixel createRgbPixel(int redValue, int greenValue, int blueValue) {
    return new RGBPixel(redValue, greenValue, blueValue);
  }
}
