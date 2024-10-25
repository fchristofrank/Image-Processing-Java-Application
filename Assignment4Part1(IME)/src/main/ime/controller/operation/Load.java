package ime.controller.operation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.controller.operation.repository.ImageLibrary;
import ime.model.image.Image;
import ime.model.image.ImageType;

/**
 * Controller class for loading images into the image library.
 * This class is responsible for reading an image file from the specified path
 * and adding it to the image library under a given name.
 */
public class Load extends AbstractOperation {
  private static final Logger LOGGER = Logger.getLogger(Load.class.getName());

  /**
   * Constructs a Load operation controller with the specified image library.
   *
   * @param library the ImageLibrary instance that provides access to the images.
   */
  public Load(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String imagePath = args[0];
    imagePath = imagePath.replace("\"", "");
    String imageName = args[1];
    String[] parts = imagePath.split("\\.");
    String imageFormat = parts[parts.length - 1];
    ImageReader imageReader = ImageReaderFactory.
            createReader(ImageFormat.valueOf(imageFormat.toUpperCase()));
    Image image = null;
    try {
      image = imageReader.read(imagePath, ImageType.RGB);
    } catch (NullPointerException | IOException e) {
      LOGGER.log(Level.SEVERE, "Error reading image file: " + imagePath, e);
      throw new IllegalArgumentException("Error reading image file: " + imagePath
              + ". Please ensure the file exists and is a valid image format.", e);
    }
    addImage(imageName, image);
    System.out.println("Image loaded: " + imageName);
  }
}
