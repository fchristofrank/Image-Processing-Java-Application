package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.operations.VisualizeBlue;
import ime.operations.VisualizeGreen;
import ime.operations.VisualizeRed;

public class RGBSplit extends AbstractOperation {
  public RGBSplit(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
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
