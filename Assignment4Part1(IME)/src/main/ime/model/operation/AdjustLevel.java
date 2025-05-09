package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;

/**
 * This operation involves providing the black, mid and white values. It generates a quadratic
 * function which is applied to each of the pixels, and it generates the corrected image.
 */
public class AdjustLevel implements ImageOperation {

  private double coefficient_a;
  private double coefficient_b;
  private double coefficient_c;

  /**
   * Sets the coefficients for the levels adjustment based on black, mid, and white values. This
   * method should be called once before applying the operation to an image.
   */
  public void setCoefficients(int blackValue, int midValue, int whiteValue) {
    double calculatedA =
        Math.pow(blackValue, 2) * (midValue - whiteValue)
            - blackValue * (Math.pow(midValue, 2) - Math.pow(whiteValue, 2))
            + whiteValue * Math.pow(midValue, 2)
            - midValue * Math.pow(whiteValue, 2);
    double calculatedAa = -blackValue * (128 - 255) + 128 * whiteValue - 255 * midValue;
    double calculatedAb =
        Math.pow(blackValue, 2) * (128 - 255)
            + 255 * Math.pow(midValue, 2)
            - 128 * Math.pow(whiteValue, 2);
    double calculatedAc =
        Math.pow(blackValue, 2) * (255 * midValue - 128 * whiteValue)
            - blackValue * (255 * Math.pow(midValue, 2) - 128 * Math.pow(whiteValue, 2));

    this.coefficient_a = calculatedAa / calculatedA;
    this.coefficient_b = calculatedAb / calculatedA;
    this.coefficient_c = calculatedAc / calculatedA;
  }

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    int blackValue = Integer.parseInt(args[0]);
    int midValue = Integer.parseInt(args[1]);
    int whiteValue = Integer.parseInt(args[2]);

    setCoefficients(blackValue, midValue, whiteValue);

    int previewWidthPercentage = 100;
    if (args.length == 7) {
      previewWidthPercentage = Integer.parseInt(args[6]);
    }

    Pixel[][] pixels = new Pixel[inputImage.getHeight()][inputImage.getWidth()];

    for (int x = 0; x < inputImage.getHeight(); x++) {
      for (int y = 0; y < inputImage.getWidth(); y++) {

        int redComponent = inputImage.getPixel(x, y).getRed();
        int greenComponent = inputImage.getPixel(x, y).getGreen();
        int blueComponent = inputImage.getPixel(x, y).getBlue();

        if (y < (previewWidthPercentage * inputImage.getWidth()) / 100) {
          redComponent = evaluateQuadraticEquation(inputImage.getPixel(x, y).getRed());
          greenComponent = evaluateQuadraticEquation(inputImage.getPixel(x, y).getGreen());
          blueComponent = evaluateQuadraticEquation(inputImage.getPixel(x, y).getBlue());
        }
        pixels[x][y] =
            PixelFactory.createPixel(
                inputImage.getType(), redComponent, greenComponent, blueComponent);
      }
    }
    return new SimpleImage(
        inputImage.getHeight(), inputImage.getWidth(), inputImage.getType(), pixels);
  }

  private int evaluateQuadraticEquation(int pixelValue) {
    return (int)
        (coefficient_a * Math.pow(pixelValue, 2) + coefficient_b * pixelValue + coefficient_c);
  }
}
