package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.RGBPixel;

import static ime.constants.FilterConstants.PIXEL_LOWER_LIMIT;
import static ime.constants.FilterConstants.PIXEL_UPPER_LIMIT;

/**
 * The Filter class provides a framework for applying a filter to an image using a kernel. It
 * includes methods to apply a filter to each pixel in an image and adjust the pixel values based on
 * the kernel's convolution.
 */
public abstract class Filter implements ImageOperation {

  /**
   * Returns the kernel used for the filtering operation. Subclasses are responsible for providing
   * the specific kernel. The kernel is a 2D array that is used to calculate the new pixel values by
   * convolution.
   *
   * @return the filter kernel as a 2D float array
   */
  protected abstract float[][] getKernel();

  /**
   * Applies the filter operation to the input image by iterating over each pixel and applying the
   * convolution kernel to generate a new image. The resulting image will have its pixel values
   * altered according to the filter's kernel.
   *
   * @param inputImage the image on which the filter is applied
   * @param args       additional arguments, if any (not used in this operation)
   * @return a new filtered image
   * @throws IllegalArgumentException if the input image or arguments are invalid
   */
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    Pixel[][] pixels = new Pixel[inputImage.getHeight()][inputImage.getWidth()];
    processImage(inputImage, pixels, args);
    return new SimpleImage(inputImage.getHeight(), inputImage.getWidth(), ImageType.RGB, pixels);
  }

  protected void processImage(Image inputImage, Pixel[][] pixels, String... args) {
    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        Pixel newPixel = applyFilterToPixel(inputImage, i, j);
        pixels[i][j] = newPixel;
      }
    }
  }

  /**
   * Applies the filter kernel to a specific pixel in the image by performing convolution. It takes
   * the surrounding pixels within the kernel's range and computes the new RGB values. If a pixel is
   * out of bounds, it is skipped in the convolution process.
   *
   * @param image the input image
   * @param x     the x-coordinate of the pixel
   * @param y     the y-coordinate of the pixel
   * @return a new pixel with clamped RGB values based on the convolution results
   */
  protected Pixel applyFilterToPixel(Image image, int x, int y) {

    double redSum = 0;
    double greenSum = 0;
    double blueSum = 0;

    float[][] blurKernel = getKernel();
    int kernelSize = blurKernel.length;
    int kernelOffset = kernelSize / 2;

    for (int i = -kernelOffset; i <= kernelOffset; i++) {
      for (int j = -kernelOffset; j <= kernelOffset; j++) {
        if (isOutOfBounds(image, x + i, y + j)) {
          continue;
        }
        Pixel pixel = image.getPixel(x + i, y + j);
        redSum += pixel.getRed() * blurKernel[i + kernelOffset][j + kernelOffset];
        greenSum += pixel.getGreen() * blurKernel[i + kernelOffset][j + kernelOffset];
        blueSum += pixel.getBlue() * blurKernel[i + kernelOffset][j + kernelOffset];
      }
    }

    return createClampedPixel((int) redSum, (int) greenSum, (int) blueSum);
  }

  /**
   * Checks if a given pixel is out of the bounds of the image. If the pixel coordinates fall
   * outside the image dimensions, it is considered out of bounds.
   *
   * @param image    the input image
   * @param currentX the x-coordinate of the pixel
   * @param currentY the y-coordinate of the pixel
   * @return true if the pixel is out of bounds, false otherwise
   */
  private boolean isOutOfBounds(Image image, int currentX, int currentY) {
    return currentX < 0
            || currentX >= image.getHeight()
            || currentY < 0
            || currentY >= image.getWidth();
  }

  /**
   * Creates a new pixel with clamped RGB values. Each RGB value is clamped between the pixel lower
   * limit and upper limit to prevent overflow or underflow.
   *
   * @param red   the red value to be clamped
   * @param green the green value to be clamped
   * @param blue  the blue value to be clamped
   * @return a new Pixel object with clamped RGB values
   */
  private Pixel createClampedPixel(int red, int green, int blue) {
    int clampedRed = clamp(red);
    int clampedGreen = clamp(green);
    int clampedBlue = clamp(blue);
    return new RGBPixel(clampedRed, clampedGreen, clampedBlue);
  }

  /**
   * Clamps the given pixel value between the defined lower and upper limits. This ensures that the
   * pixel value does not exceed the allowable RGB range.
   *
   * @param value the pixel value to be clamped
   * @return the clamped pixel value
   */
  private int clamp(int value) {
    return Math.max(PIXEL_LOWER_LIMIT, Math.min(PIXEL_UPPER_LIMIT, value));
  }
}
