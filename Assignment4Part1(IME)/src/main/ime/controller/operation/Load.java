package ime.controller.operation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ime.imageIO.ImageFormat;
import ime.model.image.ImageLibrary;
import ime.imageIO.Reader;
import ime.imageIO.ReaderFactory;
import ime.model.image.Image;

public class Load extends AbstractOperation {
  private static final Logger LOGGER = Logger.getLogger(Load.class.getName());

  public Load(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String imagePath = args[0];
    String imageName = args[1];
    String[] parts = imagePath.split("\\.");
    String imageFormat = parts[parts.length - 1];
    Reader reader = ReaderFactory.createrReader(ImageFormat.valueOf(imageFormat.toUpperCase()));
    Image image = null;
    try {
      image = reader.read(imagePath);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error reading image file: " + imagePath, e);
    }
    addImage(imageName, image);
    System.out.println("Image loaded: " + imageName);
  }
}
