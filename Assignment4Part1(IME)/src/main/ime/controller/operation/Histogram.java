package ime.controller.operation;

import ime.controller.operation.repository.ImageLibrary;
import ime.controller.operation.repository.ImageRepo;
import ime.model.image.Image;
import ime.model.operation.CountFrequency;

public class Histogram extends AbstractOperation {

  /**
   * Constructs an AbstractOperation with the specified image library.
   *
   * @param library the ImageLibrary to be used for image operations
   */
  public Histogram(ImageRepo library) {
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
        new ime.model.operation.Histogram(new CountFrequency()), args);
    addImage(outputName, outputImage);
    System.out.println("Generated Histogram. New Image :: " + outputName);
  }
}
