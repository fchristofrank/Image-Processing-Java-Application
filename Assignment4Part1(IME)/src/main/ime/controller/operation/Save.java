package ime.controller.operation;

import java.awt.image.BufferedImage;
import java.io.IOException;

import ime.imageio.ImageFormat;
import ime.imageio.ImageWriter;
import ime.imageio.ImageWriterFactory;
import ime.model.image.Image;
import ime.model.image.ImageLibrary;
import ime.model.pixel.Pixel;

/**
 * Controller class for saving images from the image library to the file system.
 * This class is responsible for converting an image to a buffered image and writing it
 * to the specified file path in the desired format.
 */
public class Save extends AbstractOperation {
  /**
   * Constructs a Save operation controller with the specified image library.
   *
   * @param library the ImageLibrary instance that provides access to the images.
   */
  public Save(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String imagePath = args[0];
    imagePath = imagePath.replace("\"", "");
    String[] parts = imagePath.split("\\.");
    ImageFormat imageFormat = ImageFormat.valueOf(parts[parts.length - 1].toUpperCase());
    String imageName = args[1];
    Image simpleImage = getImage(imageName);
    BufferedImage bufferedImage = convertToBufferedImage(simpleImage);
    try {
      ImageWriter writer = ImageWriterFactory.createWriter(imageFormat);
      writer.writeImage(bufferedImage, imagePath);
    } catch (IllegalArgumentException | IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    System.out.println("Saved " + imageName + " in " + imagePath);
  }

  /**
   * This method converts image to buffered image representation.
   *
   * @param image the image which has to be converted to buffered image representation.
   * @return the converted image.
   */
  private BufferedImage convertToBufferedImage(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int imageType = image.getType().getImageType();
    BufferedImage bufferedImage = new BufferedImage(width, height, imageType);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = image.getPixel(i, j);
        if (pixel != null) {
          bufferedImage.setRGB(j, i, pixel.getColorComponents());
        }
      }
    }
    return bufferedImage;
  }
}
