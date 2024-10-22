package ime.model.image;

import ime.model.operation.MultipleImageOperation;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import ime.model.operation.ImageOperation;
import ime.model.pixel.Pixel;

public class SimpleImage implements Image {
  private final int height;
  private final int width;
  private final ImageType imageType;
  private final Pixel[][] pixels;

  /**
   * This method creates a simple image with the given type, height and width.
   * @param height the height of the image.
   * @param width the width of the image.
   * @param imageType the type of the
   */
  public SimpleImage(int height, int width, ImageType imageType) {
    this.height = height;
    this.width = width;
    this.imageType = imageType;
    this.pixels = new Pixel[height][width];
  }

  @Override
  public Pixel getPixel(int row, int column) {
    if (row < 0 || row >= height || column < 0 || column >= width) {
      throw new IllegalArgumentException("Invalid row or column for a pixel.");
    }
    return pixels[row][column];
  }

  @Override
  public void setPixel(int row, int column, Pixel pixel) throws IllegalArgumentException {
    if (row < 0 || row >= height || column < 0 || column >= width) {
      throw new IllegalArgumentException("Invalid row or column for a pixel.");
    }
    pixels[row][column] = pixel;
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
  public Image applyOperation(ImageOperation operation, String... args) throws IllegalArgumentException {
    return operation.apply(this, args);
  }

  @Override
  public Image applyOperation(MultipleImageOperation operation, List<Image> images, String... args) throws IllegalArgumentException{
    return operation.apply(images,args);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    SimpleImage that = (SimpleImage) obj;
    return height == that.height &&
            width == that.width &&
            imageType == that.imageType &&
            Arrays.deepEquals(pixels, that.pixels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, width, imageType, Arrays.deepHashCode(pixels));
  }
}
