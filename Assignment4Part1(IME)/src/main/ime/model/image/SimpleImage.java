package ime.model.image;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import ime.model.operation.ImageOperation;
import ime.model.operation.MultipleImageOperation;
import ime.model.pixel.Pixel;

/**
 * This class represents a Simple Image compose of pixels. It implements the Image interface,
 * providing methods for pixel manipulation, applying image operations, and retrieving image
 * properties such as height, width, and type.
 */
public class SimpleImage implements Image {

  private final int height;
  private final int width;
  private final ImageType imageType;
  private final Pixel[][] pixels;

  /**
   * Public method to create a new image with all the collection of pixels. Once image is created
   * then pixels cannot be modified.
   *
   * @param height    height of the image.
   * @param width     width of the image.
   * @param imageType the type of image.
   * @param pixels    final pixel arrays to create the image.
   */
  public SimpleImage(int height, int width, ImageType imageType, Pixel[][] pixels) {
    this.height = height;
    this.width = width;
    this.imageType = imageType;
    this.pixels = pixels;
  }

  @Override
  public Pixel getPixel(int row, int column) {
    if (row < 0 || row >= height || column < 0 || column >= width) {
      throw new IllegalArgumentException("Invalid row or column for a pixel.");
    }
    return pixels[row][column];
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  public ImageType getType() {
    return imageType;
  }

  @Override
  public Image applyOperation(ImageOperation operation, String... args)
      throws IllegalArgumentException {
    return operation.apply(this, args);
  }

  @Override
  public Image applyOperation(MultipleImageOperation operation, List<Image> images, String... args)
      throws IllegalArgumentException {
    return operation.apply(images, args);
  }

  /**
   * This method compares two images based on their height, width, image type and pixel attributes.
   *
   * @param obj the other image which has to be compared to be with this image.
   * @return true if both the images are identical false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    SimpleImage that = (SimpleImage) obj;
    return height == that.height
        && width == that.width
        && imageType == that.imageType
        && Arrays.deepEquals(pixels, that.pixels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, width, imageType, Arrays.deepHashCode(pixels));
  }
}
