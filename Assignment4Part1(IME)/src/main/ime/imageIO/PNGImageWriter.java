package ime.imageIO;

/**
 * The ImageWriter class provides functionality for writing PNG image files to disk.
 */
public class PNGImageWriter extends AbstractWriter{


  @Override
  public ImageFormat getFormat() {
    return ImageFormat.PNG;
  }
}
