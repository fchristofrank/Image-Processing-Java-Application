package ime.controller.imageio;

/** The ImageWriter class provides functionality for writing PNG image files to disk. */
public class PNGImageWriter extends AbstractWriter {

  @Override
  protected ImageFormat getFormat() {
    return ImageFormat.PNG;
  }
}
