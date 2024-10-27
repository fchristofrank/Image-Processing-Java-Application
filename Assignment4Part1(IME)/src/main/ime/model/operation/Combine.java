package ime.model.operation;

import java.util.List;
import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.RGBPixel;

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
    Image outputImage = new SimpleImage(height, width, ImageType.RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        validateGrayscaleImages(redImage.getPixel(i, j), greenImage.getPixel(i, j), blueImage
                .getPixel(i, j));

        int redValue = redImage.getPixel(i, j).getRed();
        int greenValue = greenImage.getPixel(i, j).getGreen();
        int blueValue = blueImage.getPixel(i, j).getBlue();

        Pixel rgbPixel = createRgbPixel(redValue, greenValue, blueValue);
        outputImage.setPixel(i, j, rgbPixel);
      }
    }

    return outputImage;
  }

  private void validateImageCount(List<Image> images) {
    if (images.size() != 3) {
      throw new IllegalArgumentException("Not Enough images for operation. Expected " + 3);
    }
  }

  private void validateSameDimensions(Image... images) {
    int height = images[0].getHeight();
    int width = images[0].getWidth();
    for (Image image : images) {
      if (image.getHeight() != height || image.getWidth() != width) {
        throw new IllegalArgumentException("Dimensions cannot be different for images");
      }
    }
  }

  private void validateGrayscaleImages(Pixel... pixels) {
    for (Pixel pixel : pixels) {
      if (!isGreyscaleImage(pixel)) {
        throw new IllegalArgumentException("Operation is allowed only on greyscale Images.");
      }
    }
  }

  private boolean isGreyscaleImage(Pixel pixel) {
    return pixel.getRed() == pixel.getGreen() && pixel.getRed() == pixel.getBlue();
  }

  private Pixel createRgbPixel(int redValue, int greenValue, int blueValue) {
    return new RGBPixel(redValue, greenValue, blueValue);
  }
}