package ime.model.image;

import ime.model.operation.MultipleImageOperation;

import ime.model.operation.ImageOperation;
import ime.model.pixel.Pixel;

import java.util.List;

public interface Image {

  int getHeight();

  int getWidth();

  Pixel getPixel(int row, int column) throws IllegalArgumentException;

  void setPixel(int row, int column, Pixel pixel) throws IllegalArgumentException;

  ImageType getType();

  Image applyOperation(ImageOperation operation, String... args) throws IllegalArgumentException;

  Image applyOperation(MultipleImageOperation operation, List<Image> images, String... args) throws IllegalArgumentException;

}
