package ime.controller.operation;


import ime.controller.operation.repository.ImageLibrary;
import ime.controller.operation.repository.ImageRepo;
import ime.model.image.Image;
import ime.model.operation.CountFrequency;

public class ColorCorrection extends AbstractOperation {

  public ColorCorrection(ImageRepo library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String inputName = args[0];
    String outputName = args[1];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(
        new ime.model.operation.ColorCorrection(new CountFrequency()), args);
    addImage(outputName, outputImage);
    System.out.println("Generated Color Corrected Image. New Image :: " + outputName);
  }
}
