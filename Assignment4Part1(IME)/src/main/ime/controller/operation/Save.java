package ime.controller.operation;

import java.awt.image.BufferedImage;

import ime.model.image.ImageLibrary;
import ime.imageIO.ImageWriter;
import ime.model.image.Image;
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
  public void execute(String[] args) throws IllegalArgumentException{
    validateArgs(args);
    String imagePath = args[0];
    String[] parts = imagePath.split("\\.");
    String imageFormat = parts[parts.length - 1];
    String imageName = args[1];
    Image simpleImage = getImage(imageName);
    BufferedImage bufferedImage = convertToBufferedImage(simpleImage);
    ImageWriter.writeImage(bufferedImage, imageFormat, imagePath);
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
