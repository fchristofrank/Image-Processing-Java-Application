package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageFormat;
import ime.imageIO.ImageLibrary;
import ime.imageIO.Reader;
import ime.imageIO.ReaderFactory;
import ime.models.Image;

public class Load extends AbstractOperation {
  public Load(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
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
