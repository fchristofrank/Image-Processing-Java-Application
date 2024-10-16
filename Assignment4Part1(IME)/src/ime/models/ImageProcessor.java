package ime.models;

import ime.operations.ImageOperation;

public interface ImageProcessor {

    int getHeight();

    int getWidth();

    Pixel getPixel(int row, int column) throws IllegalArgumentException;

    void setPixel(int row, int column, Pixel pixel) throws IllegalArgumentException;

    Image applyOperation(ImageOperation operation, String parameter);

    void save(String imagePathName, String imageName);
}
