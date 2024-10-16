package ime.operations;

import java.io.IOException;
import ime.imageIO.ImageFormat;
import ime.imageIO.ImageLibrary;
import ime.imageIO.Reader;
import ime.imageIO.ReaderFactory;
import ime.models.Image;

public class Load extends ImageOperationManager{

  public Load(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  public void apply(String[] args) throws IllegalArgumentException, IOException {
    validateArgs(args);
    String imagePath = args[0];
    String imageName = args[1];
    try {
      String[] parts = imagePath.split("\\.");
      String imageFormat = parts[parts.length - 1];
      Reader reader = ReaderFactory.createrReader(ImageFormat.valueOf(imageFormat.toUpperCase()));
      Image image = reader.read(imagePath);
      addImage(imageName, image);
      System.out.println("Image loaded: " + imageName);
    } catch (IOException e) {
      throw new IOException(e);
    }
  }
}
