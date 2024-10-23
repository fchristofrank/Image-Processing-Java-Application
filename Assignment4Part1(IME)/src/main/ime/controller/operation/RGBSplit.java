package ime.controller.operation;

import ime.model.image.Image;
import ime.model.image.ImageLibrary;
import ime.model.operation.VisualizeBlue;
import ime.model.operation.VisualizeGreen;
import ime.model.operation.VisualizeRed;

/**
 * Controller class for performing RGB separation operations on images.
 * This class is responsible for splitting an input image into its
 * red, green, and blue components, creating separate images for each color.
 */
public class RGBSplit extends AbstractOperation {
  public RGBSplit(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    String inputImageName = args[0];
    String redOutputImageName = args[1];
    String greenOutputImageName = args[2];
    String blueOutputImageName = args[3];
    Image inputImage = getImage(inputImageName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image redImage = inputImage.applyOperation(new VisualizeRed());
    Image greenImage = inputImage.applyOperation(new VisualizeGreen());
    Image blueImage = inputImage.applyOperation(new VisualizeBlue());
    addImage(redOutputImageName, redImage);
    addImage(greenOutputImageName, greenImage);
    addImage(blueOutputImageName, blueImage);
    System.out.println("The images have been separated into their red, blue, and green components " +
            "and combined accordingly.");
  }

  @Override
  protected void validateArgs(String[] args) {
    if (args.length != 4) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
