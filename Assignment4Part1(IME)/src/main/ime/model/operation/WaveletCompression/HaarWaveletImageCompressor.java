package ime.model.operation.WaveletCompression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;

/**
 * Implements the Haar Wavelet transformation for compressing and transforming image data.
 * <p>
 * This class applies the Haar wavelet transformation to images to compress and
 * reconstruct image data. It is designed to preserve significant features of the
 * image while eliminating less important details, making it suitable for image
 * compression applications.
 * </p>
 */
public class HaarWaveletImageCompressor implements WaveletImageCompressor {
  private final double SQRT2 = Math.sqrt(2);

  @Override
  public Image applyWaveletCompression(Image image, float compressionRatio) {
    int height = image.getHeight();
    int width = image.getWidth();
    float[][] red = new float[height][width];
    float[][] green = new float[height][width];
    float[][] blue = new float[height][width];

    // Extract pixel color values into separate color channel arrays
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        red[i][j] = image.getPixel(i, j).getRed();
        green[i][j] = image.getPixel(i, j).getGreen();
        blue[i][j] = image.getPixel(i, j).getBlue();
      }
    }

    // Apply transformation and compression to each color channel
    float[][] tRed = transformAndCompress(red, compressionRatio);
    float[][] tGreen = transformAndCompress(green, compressionRatio);
    float[][] tBlue = transformAndCompress(blue, compressionRatio);

    // Invert the transformation to reconstruct the image
    invertTransform(tRed);
    invertTransform(tGreen);
    invertTransform(tBlue);
    Pixel[][] pixels = new Pixel[height][width];
    // Set the reconstructed pixel values into the output image
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = PixelFactory.createPixel(image.getType(),
                Math.round(tRed[i][j]),
                Math.round(tGreen[i][j]),
                Math.round(tBlue[i][j]));
      }
    }
    return new SimpleImage(height, width, image.getType(), pixels);
  }

  /**
   * Applies the Haar wavelet transform and compresses the transformed data.
   * <p>
   * This method pads the input 2D array to the nearest power of two, applies the wavelet
   * transform, and then compresses the transformed data based on the specified
   * compression ratio.
   * </p>
   *
   * @param sequence         the 2D float array representing image data.
   * @param compressionRatio the percentage of smallest coefficients to set to zero.
   * @return a 2D float array with wavelet-transformed and compressed data.
   */
  private float[][] transformAndCompress(float[][] sequence, float compressionRatio) {
    int length = nearestPowerOfTwo(Math.max(sequence.length, sequence[0].length));
    float[][] paddedSequence = new float[length][length];
    for (int i = 0; i < sequence.length; i++) {
      System.arraycopy(sequence[i], 0, paddedSequence[i], 0, sequence[i].length);
    }

    int m = length;
    while (m > 1) {
      for (int i = 0; i < m; i++) {
        float[] rowTransformedRes = rowTransform(paddedSequence, i, m);
        System.arraycopy(rowTransformedRes, 0, paddedSequence[i], 0, rowTransformedRes.length);
      }
      for (int i = 0; i < m; i++) {
        float[] colTransformedRes = columnTransform(paddedSequence, i, m);
        for (int j = 0; j < colTransformedRes.length; j++) {
          paddedSequence[j][i] = colTransformedRes[j];
        }
      }
      m = m / 2;
    }
    compressSequence(paddedSequence, compressionRatio);
    return paddedSequence;
  }

  /**
   * Reverses the Haar wavelet transformation to reconstruct the original data.
   *
   * @param sequence the transformed 2D array to be inverted back to its original form.
   */
  private void invertTransform(float[][] sequence) {
    int m = 2;
    while (m <= sequence.length) {
      for (int i = 0; i < m; i++) {
        float[] invertRowTransformedResult = invertRowTransform(sequence, i, m);
        System.arraycopy(invertRowTransformedResult, 0, sequence[i], 0, invertRowTransformedResult.length);
      }
      for (int i = 0; i < m; i++) {
        float[] invertColTransformedResult = invertColTransform(sequence, i, m);
        for (int j = 0; j < invertColTransformedResult.length; j++) {
          sequence[j][i] = invertColTransformedResult[j];
        }
      }
      m = m * 2;
    }
  }

  /**
   * Performs a Haar wavelet transformation on a single row.
   * Calculates and separates averages and differences for recursive reduction.
   *
   * @param sequence the input 2D array
   * @param row      the specific row in the array to transform
   * @param length   the length of the row to be transformed
   * @return an array containing transformed row data with averages and differences
   */
  private float[] rowTransform(float[][] sequence, int row, int length) {
    float[] avg = new float[length / 2];
    float[] diff = new float[length / 2];
    float[] result = new float[length];
    int count = 0;
    for (int i = 0; i < length - 1; i = i + 2) {
      avg[count] = (float) ((sequence[row][i] + sequence[row][i + 1]) / SQRT2);
      diff[count] = (float) ((sequence[row][i] - sequence[row][i + 1]) / SQRT2);
      count++;
    }
    System.arraycopy(avg, 0, result, 0, length / 2);
    System.arraycopy(diff, 0, result, length / 2, length / 2);
    return result;
  }

  /**
   * Performs a Haar wavelet transformation on a single column.
   * <p>
   * Calculates averages and differences for recursive reduction on a column.
   * </p>
   *
   * @param sequence the input 2D array
   * @param col      the specific column in the array to transform
   * @param length   the length of the column to be transformed
   * @return an array containing transformed column data with averages and differences
   */
  private float[] columnTransform(float[][] sequence, int col, int length) {
    float[] avg = new float[length / 2];
    float[] diff = new float[length / 2];
    float[] result = new float[length];
    int count = 0;
    for (int i = 0; i < length - 1; i = i + 2) {
      avg[count] = (float) ((sequence[i][col] + sequence[i + 1][col]) / SQRT2);
      diff[count] = (float) ((sequence[i][col] - sequence[i + 1][col]) / SQRT2);
      count++;
    }
    for (int i = 0; i < length / 2; i++) {
      result[i] = avg[i];
      result[i + length / 2] = diff[i];
    }
    return result;
  }

  /**
   * Inverts a Haar wavelet transformation for a row, restoring original values.
   *
   * @param transform the 2D transformed array
   * @param row       the row to be inverted
   * @param length    the length of the row
   * @return an array with the inverted row data
   */
  private float[] invertRowTransform(float[][] transform, int row, int length) {
    float[] result = new float[length];
    int counter = 0;
    for (int i = 0; i < length / 2; i++) {
      float avg = transform[row][i];
      float diff = transform[row][i + length / 2];
      result[counter++] = (float) ((avg + diff) / SQRT2);
      result[counter++] = (float) ((avg - diff) / SQRT2);
    }
    return result;
  }

  /**
   * Inverts a Haar wavelet transformation for a column.
   *
   * @param transform the 2D transformed array
   * @param col       the column to be inverted
   * @param length    the length of the column
   * @return an array with the inverted column data
   */
  private float[] invertColTransform(float[][] transform, int col, int length) {
    float[] result = new float[length];
    int counter = 0;
    for (int i = 0; i < length / 2; i++) {
      float avg = transform[i][col];
      float diff = transform[i + length / 2][col];
      result[counter++] = (float) ((avg + diff) / SQRT2);
      result[counter++] = (float) ((avg - diff) / SQRT2);
    }
    return result;
  }

  /**
   * Compresses the given sequence by setting the smallest coefficients to zero.
   * <p>
   * This method identifies the number of coefficients to be retained based on the
   * compression ratio and sets the smallest coefficients to zero.
   * </p>
   *
   * @param sequence         the 2D array of transformed coefficients
   * @param compressionRatio the ratio indicating how much of the data should be retained
   */
  private void compressSequence(float[][] sequence, float compressionRatio) {
    List<Float> coefficients = new ArrayList<>();
    for (float[] row : sequence) {
      for (float value : row) {
        if (value != 0) {
          coefficients.add(Math.abs(value));
        }
      }
    }
    coefficients.sort(Collections.reverseOrder());
    int numToRetain = (int) ((1 - (compressionRatio / 100)) * coefficients.size());
    float threshold = numToRetain > 0 ? coefficients.get(numToRetain - 1) : Float.MAX_VALUE;
    for (int i = 0; i < sequence.length; i++) {
      for (int j = 0; j < sequence[i].length; j++) {
        if (Math.abs(sequence[i][j]) < threshold) {
          sequence[i][j] = 0;
        }
      }
    }
  }

  /**
   * Finds the nearest power of two greater than or equal to the given number.
   *
   * @param n the number to round up.
   * @return the nearest power of two.
   */
  private int nearestPowerOfTwo(int n) {
    int power = 1;
    while (power < n) {
      power *= 2;
    }
    return power;
  }
}
