package ime.controller.imageio;

/** The ImageWriter class provides functionality for writing JPG image files to disk. */
public class JPGImageWriter extends AbstractWriter {

  @Override
  protected ImageFormat getFormat() {
    return ImageFormat.JPG;
  }
}
