package ime.models;

import java.io.IOException;

import ime.operations.ImageOperation;

public interface Image {

  int getHeight();

  int getWidth();

  Pixel getPixel(int row, int column) throws IllegalArgumentException;

  void setPixel(int row, int column, Pixel pixel) throws IllegalArgumentException;

  ImageType getType();

  Image applyOperation(ImageOperation operation, String... args) throws IOException;

}
