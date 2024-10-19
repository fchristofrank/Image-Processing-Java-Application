package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.operations.ApplyBrightness;

public abstract class AdjustBrightness extends AbstractOperation {
  public AdjustBrightness(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
    int alpha = Integer.parseInt(args[0]);
    String inputName = args[1];
    String outputName = args[2];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(new ApplyBrightness(), String.valueOf(alpha));
    addImage(outputName, outputImage);
    System.out.println("Adjusting Brightness. New image created: " + outputName);
  }

  @Override
  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
    try {
      Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Invalid brightness adjustment value '%s'." +
              " It must be an integer.", args[0]));
    }
  }
}
