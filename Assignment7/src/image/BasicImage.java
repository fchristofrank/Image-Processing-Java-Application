package image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

import util.Constants;

/**
 * This class represents a BasicImage object, and offers all operations listed in the CustomImage
 * interface. This class represents the pixel data in a list of CustomPixel list structure.
 */
public class BasicImage implements CustomImage {

  private static final double SQRT_2 = Math.sqrt(2);
  protected List<List<CustomPixel>> pixelList;

  // Returns the first number larger than n that is a power of 2
  private static int nextPowerOfTwo(int n) {
    int power = 1;
    while (power < n) {
      power *= 2;
    }
    return power;
  }

  // Adds padding to images for compression
  private static List<List<CustomPixel>> addPadding(List<List<CustomPixel>> pixelList,
                                                    int paddingRow, int paddingColumn) {
    List<List<CustomPixel>> newPixelList = new ArrayList<>();
    // Deep copy existing rows
    for (List<CustomPixel> pixelRow : pixelList) {
      List<CustomPixel> newPixelRow = new ArrayList<>();
      // Creates copies of existing pixels
      for (CustomPixel pixel : pixelRow) {
        newPixelRow.add(new BasicPixel(pixel.getPixelRGBA()));
      }
      // Creates padding pixels for column padding
      for (int i = 0; i < paddingColumn; i++) {
        newPixelRow.add(new BasicPixel());
      }
      // Adds the row to the newPixelList
      newPixelList.add(newPixelRow);
    }

    // Creates padding rows for row padding
    for (int i = 0; i < paddingRow; i++) {
      newPixelList.add(
              Collections.nCopies(newPixelList.get(0).size(), new BasicPixel(0, 0, 0, 255)));
    }
    return newPixelList;
  }


  // Function used to calculate averaging for Haar wavelet transformation on images, takes in a
  // percentage variable in order to set the percentage % of smallest nums to 0
  private List<Double> haarWaveletAverage(List<Double> num) {
    List<Double> avg = new ArrayList<>();
    List<Double> diff = new ArrayList<>();
    for (int i = 0; i < num.size() - 1; i += 2) {
      avg.add((num.get(i) + num.get(i + 1)) / Math.sqrt(2));
      diff.add((num.get(i) - num.get(i + 1)) / Math.sqrt(2));
    }
    // Combine avg and diff and then return it
    ArrayList<Double> result = new ArrayList<>(avg);
    result.addAll(diff);

    return result;
  }

  // Function used to reverse the operations done by the T function
  private List<Double> reverseHaarWaveletAverage(List<Double> num) {
    List<Double> result = new ArrayList<>();
    int halfSize = num.size() / 2;
    for (int i = 0; i < halfSize; i++) {
      // Retrieve the average and difference parts
      double avg = num.get(i);
      double diff = num.get(i + halfSize);
      // Add the reconstructed values to the result
      result.add((avg + diff) / Math.sqrt(2));
      result.add((avg - diff) / Math.sqrt(2));
    }
    return result;
  }

  // Haar compression function for 2D array doubleList
  private List<List<Double>> haarCompression(List<List<Double>> doubleList, int percentage) {
    int n = doubleList.size();

    // Perform the Haar transform until n becomes 1
    while (n > 1) {
      // Transform each row
      for (int i = 0; i < n; i++) {
        // Transform the current row from 0 to n
        List<Double> transformedRow = haarWaveletAverage(doubleList.get(i).subList(0, n));
        // Update the row with transformed values
        for (int j = 0; j < transformedRow.size(); j++) {
          doubleList.get(i).set(j, transformedRow.get(j));
        }
      }
      // Transform each column
      for (int j = 0; j < n; j++) {
        // Create a column sublist
        List<Double> column = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          column.add(doubleList.get(i).get(j));
        }
        List<Double> transformedColumn = haarWaveletAverage(column);
        // Update the column with transformed values
        for (int i = 0; i < transformedColumn.size(); i++) {
          doubleList.get(i).set(j, transformedColumn.get(i));
        }
      }
      n /= 2;
    }

    // Iterate through the matrix, add all values into a priority queue based on lowest value,
    // and keep track of their indices as well
    PriorityQueue<List<Double>> pq = new PriorityQueue<>(
            Comparator.comparingDouble(p -> Math.abs(p.get(0))));
    for (int i = 0; i < doubleList.size(); i++) {
      for (int j = 0; j < doubleList.get(i).size(); j++) {
        double value = doubleList.get(i).get(j);
        if (Math.abs(value) > 0.0) {
          List<Double> entry = new ArrayList<>();
          entry.add(value);
          entry.add((double) i);
          entry.add((double) j);
          pq.add(entry);
        }
      }
    }

    // Calculate the number of values to retain and reduce the priority queue until it is that size
    int numValuesToZero = (int) Math.ceil(pq.size() * (percentage / 100.0));
    while (!pq.isEmpty() && numValuesToZero > 0) {
      List<Double> entry = pq.poll();
      int i = entry.get(1).intValue();
      int j = entry.get(2).intValue();
      doubleList.get(i).set(j, 0.0);
      numValuesToZero--;
    }
    return doubleList;
  }

  // Reverses the Haar compression function for 2D array doubleList
  private List<List<Double>> haarInvCompression(List<List<Double>> doubleList) {
    int n = 2;

    while (n <= doubleList.size()) {
      // Transform each column
      for (int j = 0; j < n; j++) {
        // Create a column sublist
        List<Double> column = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          column.add(doubleList.get(i).get(j));
        }
        List<Double> transformedColumn = reverseHaarWaveletAverage(column);
        // Update the column with transformed values
        for (int i = 0; i < transformedColumn.size(); i++) {
          doubleList.get(i).set(j, transformedColumn.get(i));
        }
      }

      // Transform each row
      for (int i = 0; i < n; i++) {
        // Transform the current row from 0 to n
        List<Double> transformedRow = reverseHaarWaveletAverage(doubleList.get(i).subList(0, n));
        // Update the row with transformed values
        for (int j = 0; j < transformedRow.size(); j++) {
          doubleList.get(i).set(j, transformedRow.get(j));
        }
      }
      n *= 2;
    }
    return doubleList;
  }

  // Helper function to calculate the new channel value
  private int levelsAdjustEquation(double a, double b, double c, int x) {
    return (int) Math.max(0, Math.min(255, a * Math.pow(x, 2) + (b * x) + c));
  }

  // Helper function for matrix multiplication
  private List<List<CustomPixel>> conductMatrixMultiplicationPixelConversion(
          List<List<Double>> matrix) {

    List<List<CustomPixel>> resultList = new ArrayList<>();

    for (int i = 0; i < this.pixelList.size(); i++) {
      List<CustomPixel> newPixelRow = addPixelRow(matrix, i);
      resultList.add(newPixelRow);
    }

    return resultList;
  }

  // Helper function to add a pixel row
  private List<CustomPixel> addPixelRow(List<List<Double>> matrix, int rowIndex) {
    List<CustomPixel> resultList = new ArrayList<>();

    for (int i = 0; i < this.pixelList.get(rowIndex).size(); i++) {
      List<Integer> rgba = this.pixelList.get(rowIndex).get(i).getPixelRGBA();
      List<Integer> newRGBA = convertRGBAValues(matrix, rgba.get(0), rgba.get(1), rgba.get(2),
              rgba.get(3));
      CustomPixel newPixel = new BasicPixel(newRGBA);
      resultList.add(newPixel);
    }

    return resultList;
  }

  // Helper function to convert rgb values
  private List<Integer> convertRGBAValues(List<List<Double>> matrix, int red, int green,
                                          int blue,
                                          int alpha) {
    List<Integer> resultList = new ArrayList<>();
    for (int row = 0; row < 3; row++) {
      double sum = 0;
      List<Double> newRow = matrix.get(row);
      sum += newRow.get(0) * red;
      sum += newRow.get(1) * green;
      sum += newRow.get(2) * blue;
      resultList.add((int) sum);
    }
    resultList.add(alpha);
    return resultList;
  }

  // Main function to perform matrix transformations on the entire image
  private List<List<CustomPixel>> applyMatrixPixelTransformation(List<List<Double>> matrix) {
    List<List<CustomPixel>> resultList = new ArrayList<>();
    for (int row = 0; row < this.pixelList.size(); row++) {
      List<CustomPixel> newPixelRow = new ArrayList<>();
      for (int col = 0; col < this.pixelList.get(row).size(); col++) {
        CustomPixel newPixel = applyMatrixToPixel(row, col, matrix);
        newPixelRow.add(newPixel);
      }
      resultList.add(newPixelRow);
    }
    return resultList;
  }

  // Function to apply the matrix transformation to a single pixel
  private CustomPixel applyMatrixToPixel(int row, int col, List<List<Double>> matrix) {
    int[] colorSums = computeColorSums(row, col, matrix);
    int alpha = this.pixelList.get(row).get(col).getPixelRGBA().get(3);
    List<Integer> newRGBA = List.of(
            Math.min(255, Math.max(0, colorSums[0])),
            Math.min(255, Math.max(0, colorSums[1])),
            Math.min(255, Math.max(0, colorSums[2])),
            alpha
    );
    return new BasicPixel(newRGBA);
  }

  // Helper function to compute color sums for a single pixel
  private int[] computeColorSums(int row, int col, List<List<Double>> matrix) {
    int redSum = 0;
    int greenSum = 0;
    int blueSum = 0;
    int imageHeight = this.pixelList.size();
    int imageWidth = this.pixelList.get(0).size();

    for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
      for (int colOffset = -1; colOffset <= 1; colOffset++) {
        int currentRow = row + rowOffset;
        int currentCol = col + colOffset;

        if (currentRow >= 0
                && currentRow < imageHeight
                && currentCol >= 0
                && currentCol < imageWidth) {

          List<Integer> rgba = this.pixelList.get(currentRow).get(currentCol).getPixelRGBA();
          double matrixValue = matrix.get(rowOffset + 1).get(colOffset + 1);

          redSum += (int) (rgba.get(0) * matrixValue);
          greenSum += (int) (rgba.get(1) * matrixValue);
          blueSum += (int) (rgba.get(2) * matrixValue);
        }
      }
    }
    return new int[]{redSum, greenSum, blueSum};
  }

  // Helper function to calculate luma, intensity, and value components
  private CustomImage greyscaleImage(int flag, int percentage) {
    int verticalLine = (int) (this.getWidth() * (percentage / 100.0));
    List<List<CustomPixel>> newPixels = new ArrayList<>();
    // Iterate through each row and column
    for (List<CustomPixel> pixelRow : pixelList) {
      List<CustomPixel> newPixelRow = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) {
        CustomPixel newPixel = new BasicPixel(pixelRow.get(j).getPixelRGBA());
        // Check if the pixel should be modified, and adds it to the new pixelRow
        if (j < verticalLine) {
          // Check which greyscale component to get based on flag
          if (flag == 0) {
            newPixel = pixelRow.get(j).getIntensityPixel();
          } else if (flag == 1) {
            newPixel = pixelRow.get(j).getLumaPixel();
          } else if (flag == 2) {
            newPixel = pixelRow.get(j).getValuePixel();
          }
        }
        newPixelRow.add(newPixel);
      }
      newPixels.add(newPixelRow);
    }
    // Use flag to determine which greyscale to return

    return new BasicImage(newPixels);
  }

  /**
   * Constructor that initializes a BasicImage object and a list of CustomPixel pixel lists.
   *
   * @param pixels a list of CustomPixel pixel lists
   * @throws IllegalArgumentException in the event the length of the original image is not the
   *                                  length of the new image
   */
  public BasicImage(List<List<CustomPixel>> pixels) throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels cannot be null");
    }
    int pixelLength = pixels.get(0).size();
    for (List<CustomPixel> pixel : pixels) {
      if (pixel.size() != pixelLength) {
        throw new IllegalArgumentException("Pixels must have the same length");
      }
    }
    this.pixelList = pixels;
  }

  @Override
  public CustomPixel get(int xCoordinate, int yCoordinate) {
    return pixelList.get(xCoordinate).get(yCoordinate);
  }

  @Override
  public int getHeight() {
    return pixelList.size();
  }

  @Override
  public int getWidth() {
    return pixelList.get(0).size();
  }

  @Override
  public CustomImage horizontalFlip() {
    List<List<CustomPixel>> resultPixelList = new ArrayList<>();
    // Creates a copy of each row, reverses it, and then add it to our new Arraylist
    for (List<CustomPixel> pixel : pixelList) {
      List<CustomPixel> reversedRow = new ArrayList<>(pixel);
      Collections.reverse(reversedRow);
      resultPixelList.add(reversedRow);
    }

    // Saves it to newImage and return it
    return new BasicImage(resultPixelList);
  }

  @Override
  public CustomImage verticalFlip() {
    List<List<CustomPixel>> newPixels = new ArrayList<>();

    // Flip the rows (reverse the outer list)
    for (int i = this.pixelList.size() - 1; i >= 0; i--) {
      // Add the rows in reversed order to the new ArrayList
      newPixels.add(new ArrayList<>(this.pixelList.get(i))); // Keep row elements intact
    }

    return new BasicImage(newPixels);
  }

  @Override
  public CustomImage increaseBrightness(int brightness) {
    List<List<CustomPixel>> newPixels = new ArrayList<>();
    // Iterate through each row and column
    for (List<CustomPixel> pixelRow : pixelList) {
      List<CustomPixel> newPixelRow = new ArrayList<>();
      for (CustomPixel pixel : pixelRow) {
        // Grab the pixel data, grab each RGB value, add the desired brightness to it, bound it
        // within 0 and 255, then add it back to the new arraylist
        List<Integer> pixelCopy = new ArrayList<>(pixel.getPixelRGBA());
        for (int i = 0; i < 3; i++) {
          pixelCopy.set(i, Math.max(0, Math.min(pixelCopy.get(i) + brightness, 255)));
        }
        // Create the newPixel, add it to newPixelRow
        CustomPixel newPixel = new BasicPixel(pixelCopy);
        newPixelRow.add(newPixel);
      }
      newPixels.add(newPixelRow);
    }
    return new BasicImage(newPixels);
  }

  @Override
  public List<CustomImage> splitRGB(int percentage) {
    int verticalLine = (int) (this.getWidth() * (percentage / 100.0));
    List<List<CustomPixel>> newPixelsRed = new ArrayList<>();
    List<List<CustomPixel>> newPixelsGreen = new ArrayList<>();
    List<List<CustomPixel>> newPixelsBlue = new ArrayList<>();
    for (List<CustomPixel> pixelRow : pixelList) {
      List<CustomPixel> newPixelRedRow = new ArrayList<>();
      List<CustomPixel> newPixelGreenRow = new ArrayList<>();
      List<CustomPixel> newPixelBlueRow = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) {
        if (j < verticalLine) {
          newPixelRedRow.add(pixelRow.get(j).getRedPixel());
          newPixelGreenRow.add(pixelRow.get(j).getGreenPixel());
          newPixelBlueRow.add(pixelRow.get(j).getBluePixel());
        } else {
          newPixelRedRow.add(new BasicPixel(pixelRow.get(j).getPixelRGBA()));
          newPixelGreenRow.add(new BasicPixel(pixelRow.get(j).getPixelRGBA()));
          newPixelBlueRow.add(new BasicPixel(pixelRow.get(j).getPixelRGBA()));
        }
      }
      newPixelsRed.add(newPixelRedRow);
      newPixelsGreen.add(newPixelGreenRow);
      newPixelsBlue.add(newPixelBlueRow);
    }
    return List.of(new BasicImage(newPixelsRed), new BasicImage(newPixelsGreen),
            new BasicImage(newPixelsBlue));
  }

  @Override
  public CustomImage combineRGB(CustomImage redImage, CustomImage greenImage,
                                CustomImage blueImage) {
    if (redImage.getHeight() != greenImage.getHeight()
            || redImage.getWidth() != blueImage.getWidth()) {
      return null;
    }
    if (redImage.getWidth() != greenImage.getWidth()
            || redImage.getHeight() != greenImage.getHeight()) {
      return null;
    }
    List<List<CustomPixel>> newPixelList = new ArrayList<>();
    for (int i = 0; i < redImage.getHeight(); i++) {
      List<CustomPixel> pixelRow = new ArrayList<>();
      for (int j = 0; j < redImage.getWidth(); j++) {
        List<Integer> pixelRGBA = new ArrayList<>();
        pixelRGBA.add(redImage.get(i, j).getPixelRGBA().get(0));
        pixelRGBA.add(greenImage.get(i, j).getPixelRGBA().get(1));
        pixelRGBA.add(blueImage.get(i, j).getPixelRGBA().get(2));
        CustomPixel newPixel = new BasicPixel(pixelRGBA);
        pixelRow.add(newPixel);
      }
      newPixelList.add(pixelRow);
    }
    this.pixelList = newPixelList;
    return this;
  }

  @Override
  public CustomImage compressConversion(int percentage) {
    int maxPadding = nextPowerOfTwo(Math.max(this.getWidth(), this.getHeight()));
    List<List<CustomPixel>> newPixelList = addPadding(pixelList,
            maxPadding - this.getHeight(), maxPadding - this.getWidth());

    // Separate the pixels by rgb
    List<List<Double>> redPixelList = new ArrayList<>();
    List<List<Double>> greenPixelList = new ArrayList<>();
    List<List<Double>> bluePixelList = new ArrayList<>();
    for (List<CustomPixel> pixelRow : newPixelList) {
      List<Double> redRow = new ArrayList<>();
      List<Double> greenRow = new ArrayList<>();
      List<Double> blueRow = new ArrayList<>();
      for (CustomPixel pixel : pixelRow) {
        redRow.add(Double.valueOf(pixel.getPixelRGBA().get(0)));
        greenRow.add(Double.valueOf(pixel.getPixelRGBA().get(1)));
        blueRow.add(Double.valueOf(pixel.getPixelRGBA().get(2)));
      }
      redPixelList.add(redRow);
      greenPixelList.add(greenRow);
      bluePixelList.add(blueRow);
    }

    // Compress and Invert each pixelList separately, and the reverse the compression
    List<List<Double>> redInverse = haarInvCompression(haarCompression(redPixelList, percentage));
    List<List<Double>> greenInverse = haarInvCompression(
            haarCompression(greenPixelList, percentage));
    List<List<Double>> blueInverse = haarInvCompression(haarCompression(bluePixelList, percentage));

    // Recombine the pixelLists into CustomImage
    List<List<CustomPixel>> result = new ArrayList<>();
    for (int i = 0; i < this.getHeight(); i++) {
      List<CustomPixel> pixelRow = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) {
        List<Integer> pixelRGBA = new ArrayList<>();
        pixelRGBA.add((int) Math.floor(redInverse.get(i).get(j)));
        pixelRGBA.add((int) Math.floor(greenInverse.get(i).get(j)));
        pixelRGBA.add((int) Math.floor(blueInverse.get(i).get(j)));
        pixelRGBA.add(255);
        pixelRow.add(new BasicPixel(pixelRGBA));
      }
      result.add(pixelRow);
    }

    return new BasicImage(result);
  }

  @Override
  public List<List<Integer>> getValues() {
    List<List<Integer>> values = new ArrayList<>();

    // Initialize `values` with independent [0, 0, 0] lists
    for (int i = 0; i < 3; i++) {
      List<Integer> row = new ArrayList<>(256);
      for (int j = 0; j < 256; j++) {
        row.add(0); // Initialize each element to 0
      }
      values.add(row);
    }

    for (List<CustomPixel> row : this.pixelList) {
      for (CustomPixel pixel : row) {
        int redValue = Math.min(255, Math.max(0, pixel.getPixelRGBA().get(0)));
        int greenValue = Math.min(255, Math.max(0, pixel.getPixelRGBA().get(1)));
        int blueValue = Math.min(255, Math.max(0, pixel.getPixelRGBA().get(2)));
        values.get(0).set(redValue, values.get(0).get(redValue) + 1);
        values.get(1).set(greenValue, values.get(1).get(greenValue) + 1);
        values.get(2).set(blueValue, values.get(2).get(blueValue) + 1);
      }
    }
    return values;
  }

  @Override
  public CustomImage intensityConversion(int percentage) {
    return greyscaleImage(0, percentage);
  }

  @Override
  public CustomImage lumaConversion(int percentage) {
    return greyscaleImage(1, percentage);
  }

  @Override
  public CustomImage valueConversion(int percentage) {
    return greyscaleImage(2, percentage);
  }

  @Override
  public CustomImage sepiaConversion(int percentage) {
    int verticalLine = (int) (this.getWidth() * (percentage / 100.0));
    List<List<CustomPixel>> newPixelList = conductMatrixMultiplicationPixelConversion(
            Constants.SEPIA_MATRIX);
    return matrixVerticalLine(verticalLine, newPixelList);
  }

  @Override
  public CustomImage blurConversion(int percentage) {
    int verticalLine = (int) (this.getWidth() * (percentage / 100.0));
    List<List<CustomPixel>> newPixelList = applyMatrixPixelTransformation(
            Constants.GAUSSIAN_BLUR_MATRIX);
    return matrixVerticalLine(verticalLine, newPixelList);
  }

  @Override
  public CustomImage sharpenConversion(int percentage) {
    int verticalLine = (int) (this.getWidth() * (percentage / 100.0));
    List<List<CustomPixel>> newPixelList = applyMatrixPixelTransformation(
            Constants.SHARPEN_MATRIX);
    return matrixVerticalLine(verticalLine, newPixelList);
  }

  // Modifies the image if there is a vertical preview line
  private CustomImage matrixVerticalLine(int verticalLine, List<List<CustomPixel>> newPixelList) {
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        if (j >= verticalLine) {
          newPixelList.get(i).set(j, new BasicPixel(this.get(i, j).getPixelRGBA()));
        }
      }
    }
    return new BasicImage(newPixelList);
  }


  @Override
  public CustomImage colorCorrection(int percentage) {
    int verticalLine = (int) (this.getWidth() * (percentage / 100.0));
    // Grab the max occurrences for each component
    int redMax = 0;
    int greenMax = 0;
    int blueMax = 0;
    List<List<Integer>> values = this.getValues();
    for (int i = 0; i < 256; i++) {
      redMax = values.get(0).get(redMax) < values.get(0).get(i) ? i : redMax;
      greenMax = values.get(1).get(greenMax) < values.get(1).get(i) ? i : greenMax;
      blueMax = values.get(2).get(blueMax) < values.get(2).get(i) ? i : blueMax;
    }
    // Calculate the average to use to find the offset
    int avg = (redMax + greenMax + blueMax) / 3;
    int redOffset = redMax - avg;
    int greenOffset = greenMax - avg;
    int blueOffset = blueMax - avg;

    // Use the offset to create a new image
    List<List<CustomPixel>> newPixelList = new ArrayList<>();
    for (List<CustomPixel> row : this.pixelList) {
      List<CustomPixel> newRow = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) {
        CustomPixel pixel = row.get(j);
        CustomPixel newPixel;
        if (j < verticalLine) {
          int newRed = Math.min(255, Math.max(0, pixel.getPixelRGBA().get(0) - redOffset));
          int newGreen = Math.min(255, Math.max(0, pixel.getPixelRGBA().get(1) - greenOffset));
          int newBlue = Math.min(255, Math.max(0, pixel.getPixelRGBA().get(2) - blueOffset));
          newPixel = new BasicPixel(newRed, newGreen, newBlue);
        } else {
          newPixel = new BasicPixel(pixel.getPixelRGBA().get(0),
                  pixel.getPixelRGBA().get(1), pixel.getPixelRGBA().get(2));
        }
        newRow.add(newPixel);
      }
      newPixelList.add(newRow);
    }
    return new BasicImage(newPixelList);
  }

  @Override
  public CustomImage levelsAdjust(int b, int m, int w, int percentage) {
    int verticalLine = (int) (this.getWidth() * (percentage / 100.0));
    // Create the quadratic equation from dark, mid, and bright
    double valueA =
            (Math.pow(b, 2) * (m - w)) - (b * (Math.pow(m, 2) - Math.pow(w, 2))) + (w * Math.pow(m, 2))
                    - (m * Math.pow(w, 2));
    double valueAa = (-b * (128 - 255)) + (128 * w) - (255 * m);
    double valueAb =
            (Math.pow(b, 2) * (128 - 255)) + (255 * Math.pow(m, 2)) - (128 * Math.pow(w, 2));
    double valueAc =
            (Math.pow(b, 2) * (255 * m - 128 * w)) - b * (255 * Math.pow(m, 2)) - (128 * Math.pow(w,
                    2));
    double aVal = valueAa / valueA;
    double bVal = valueAb / valueA;
    double cVal = valueAc / valueA;

    // Iterate through CustomImage pixel by pixel
    List<List<CustomPixel>> newPixelList = new ArrayList<>();
    for (List<CustomPixel> pixelRow : this.pixelList) {
      List<CustomPixel> newPixelRow = new ArrayList<>();
      CustomPixel newPixel;
      for (int j = 0; j < this.getWidth(); j++) {
        CustomPixel pixel = pixelRow.get(j);
        if (j < verticalLine) {
          // Plug each rgb component separately into the new quadratic eq, get the new value
          int newRed = levelsAdjustEquation(aVal, bVal, cVal, pixel.getPixelRGBA().get(0));
          int newGreen = levelsAdjustEquation(aVal, bVal, cVal, pixel.getPixelRGBA().get(1));
          int newBlue = levelsAdjustEquation(aVal, bVal, cVal, pixel.getPixelRGBA().get(2));
          newPixel = new BasicPixel(newRed, newGreen, newBlue);
        } else {
          newPixel = new BasicPixel(pixel.getPixelRGBA().get(0),
                  pixel.getPixelRGBA().get(1), pixel.getPixelRGBA().get(2));
        }
        newPixelRow.add(newPixel);
      }
      newPixelList.add(newPixelRow);
    }
    return new BasicImage(newPixelList);
  }

  @Override
  public List<List<CustomPixel>> getPixelList() {
    List<List<CustomPixel>> newPixelList = new ArrayList<>();
    for (List<CustomPixel> row : this.pixelList) {
      List<CustomPixel> newRow = new ArrayList<>();
      for (CustomPixel pixel : row) {
        newRow.add(new BasicPixel(pixel.getPixelRGBA().get(0), pixel.getPixelRGBA().get(1),
                pixel.getPixelRGBA().get(2)));
      }
      newPixelList.add(newRow);
    }
    return newPixelList;
  }

  @Override
  public CustomImage maskApply(CustomImage original, CustomImage mask) {
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; i < getWidth(); j++) {
        if (Objects.equals(mask.get(i, j).getPixelRGB(), List.of(0, 0, 0))) {
          this.get(i, j).setPixelRGBA(original.get(i, j).getPixelRGBA());
        }
      }
    }
    return this;
  }

  @Override
  public CustomImage ditherImage(int percentage) {
    // First create greyscale image
    CustomImage greyScaleImage = greyscaleImage(0, percentage);
    int height = greyScaleImage.getHeight();
    int width = (int) (greyScaleImage.getWidth() * (percentage / 100.0));


    // Create error array to store quantization errors
    float[][] errorArray = new float[height][width];

    // Initialize error array with original pixel values
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        errorArray[i][j] = greyScaleImage.get(i, j).getPixelRGBA().get(0);
      }
    }

    // Process each pixel
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // Get old color (with accumulated error)
        float oldColor = errorArray[i][j];

        // Find nearest black or white value (0 or 255)
        int newColor = (oldColor < 128) ? 0 : 255;

        // Calculate quantization error
        float error = oldColor - newColor;

        // Set the new color in result image
        this.pixelList.get(i).set(j, new BasicPixel(newColor, newColor, newColor));

        // Distribute error to neighboring pixels using Floyd-Steinberg coefficients
        if (j < width - 1)
          errorArray[i][j + 1] += error * 7 / 16.0f;
        if (i < height - 1 && j > 0)
          errorArray[i + 1][j - 1] += error * 3 / 16.0f;
        if (i < height - 1)
          errorArray[i + 1][j] += error * 5 / 16.0f;
        if (i < height - 1 && j < width - 1)
          errorArray[i + 1][j + 1] += error * 1 / 16.0f;
      }
    }

    return this;
  }


}
