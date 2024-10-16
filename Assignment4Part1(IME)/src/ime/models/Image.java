package ime.models;

import java.awt.image.BufferedImage;

import ime.operations.ImageOperation;
import ime.utils.ImageWriter;

public class Image implements ImageProcessor {
  private final int height;
  private final int width;
  private final ImageType imageType;
  private final Pixel[][] pixels;

  public Image(int height, int width, ImageType imageType) {
    this.height = height;
    this.width = width;
    this.imageType = imageType;
    this.pixels = new Pixel[height][width];
  }

  @Override
  public Pixel getPixel(int row, int column) {
    if (row < 0 || row >= height || column < 0 || column >= width) {
      throw new IllegalArgumentException("Invalid row or column for a pixel at (%d,%d)".formatted(row, column));
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

  @Override
  public Image applyOperation(ImageOperation operation, String parameter) {
    return operation.apply(this, parameter);
  }

  private BufferedImage convertToBufferedImage() {
    BufferedImage bufferedImage = new BufferedImage(width, height, imageType.getBufferedImageType());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = getPixel(i, j);
        if (pixel != null) {
          bufferedImage.setRGB(j, i, pixel.getColorComponents());
        }
      }
    }
    return bufferedImage;
  }

  @Override
  public void save(String imagePath, String imageName) {
    BufferedImage bufferedImage = convertToBufferedImage();
    ImageWriter.writeImage(bufferedImage, imageName, imagePath);

  }
}
