package fileutils;

import image.CustomImage;
import java.io.File;
import java.io.IOException;

/**
 * This interface encapsulates all methods that will later ingest / write images to different file
 * formats.
 */
public interface FileUtil {

  /**
   * Converts a File object to a CustomImage object.
   *
   * @param filename File object passed as an argument
   * @return a CustomImage object
   */
  public CustomImage fileToCustomImage(String filename);

  /**
   * Writes a CustomImage object to a File object.
   *
   * @param customImage CustomImage object passed to the method as a parameter
   * @param file        File object passed as an argument
   */
  public boolean writeCustomImageToFile(CustomImage customImage, File file) throws IOException;

}
