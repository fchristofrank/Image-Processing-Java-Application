package ime.model.operation;

import ime.model.image.Image;

/**
 * This class requires the width and the height of the resulting scaled image as args. If there is
 * no change in either of the width, it wants it to send the original width otherwise a
 * IllegalArgumentException will be raised.
 */
public class Downscale implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    int actualWidth = inputImage.getWidth();
    int actualHeight = inputImage.getHeight();

    int scaledWidth = Integer.parseInt(args[0]);
    int scaledHeight = Integer.parseInt(args[1]);

    for (int x = 0; x < actualWidth; x++) {
      for (int y = 0; y < actualHeight; y++) {

        //Duplications Can be Removed
        int computedAXPosition  = (int) Math.floor(((double) x / actualWidth) * scaledWidth);
        int computedBXPosition = (int) Math.ceil(((double) x / actualWidth) * scaledWidth);
        int computedCXPosition = (int) Math.floor(((double) x / actualWidth) * scaledWidth);
        int computedDXPosition = (int) Math.ceil(((double) x / actualWidth) * scaledWidth);

        int computedAYPosition  = (int) Math.floor(((double) y / actualWidth) * scaledWidth);
        int computedBYPosition = (int) Math.floor(((double) y / actualWidth) * scaledWidth);
        int computedCYPosition = (int) Math.ceil(((double) y / actualWidth) * scaledWidth);
        int computedDYPosition = (int) Math.ceil(((double) y / actualWidth) * scaledWidth);
        //Do For Green and Value or extract as common method.
        int computedCAValue = inputImage.getPixel(computedAYPosition, computedAXPosition).getRed();
        int computedCBValue = inputImage.getPixel(computedBYPosition, computedBXPosition).getRed();
        int computedCCValue = inputImage.getPixel(computedCYPosition, computedCXPosition).getRed();
        int computedCDValue = inputImage.getPixel(computedDYPosition, computedDXPosition).getRed();

        //int computedM =   computedCBValue*(x - )

      }
    }
  }
}
