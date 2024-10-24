package ime.imageIO;

/**
 * The ImageWriter class provides functionality for writing JPG image files to disk.
 */
public class JPGImageWriter extends AbstractWriter{


  @Override
  public ImageFormat getFormat() {
    return ImageFormat.JPG;
  }
}
