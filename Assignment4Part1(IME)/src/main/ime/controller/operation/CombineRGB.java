package ime.controller.operation;

import java.util.Arrays;

import ime.model.image.Image;
import ime.model.image.ImageLibrary;
import ime.model.operation.Combine;

/**
 * Controller for combining three images into one using their RGB channels.
 * The first image supplies the red channel, the second the green, and the third the blue.
 * The operation is strictly ordered and requires exactly three images.
 */
public class CombineRGB extends AbstractOperation {

  /**
   * Constructs a CombineRGB operation that will use the provided image library for its
   * input/output.
   *
   * @param library the library containing images available for operations.
   */
  public CombineRGB(ImageLibrary library) {
    super(library);
  }

  /**
   * Executes the combine operation by extracting red, green, and blue channels from three images
   * and merging them into one image. The first argument specifies the name of the output image.
   * The next three arguments specify the names of the images used for red, green,
   * and blue channels.
   *
   * @param args an array containing the output image name and three input image names.
   * @throws IllegalArgumentException if exactly three input images are not provided or any
   *                                  image is missing.
   */
  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String inputName = args[0];
    Image redImage = getImage(args[1]);
    Image greenImage = getImage(args[2]);
    Image blueImage = getImage(args[3]);
    if (redImage == null || greenImage == null || blueImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage =
            redImage.applyOperation(
                    new Combine(), Arrays.asList(redImage, greenImage, blueImage), args);
    addImage(inputName, outputImage);
  }

  @Override
  protected void validateArgs(String[] args) {
    if (args.length != 4) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
