package ime.util;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * This class contains utility methods for reading a file.
 */
public class FileReader {
  /**
   * Reads the contents of a file, ignoring comments and empty lines.
   * <p>
   * This method reads from a file specified by the given file path and returns
   * the contents as a string. It ignores lines that are empty or start with '#'
   * (considered as comments).
   * </p>
   *
   * @param filePath the path to the file to be read.
   * @return a string containing the contents of the file, with comments and empty lines removed.
   * @throws IOException if an I/O error occurs while reading the file.
   */
  public static String readFromFile(String filePath) throws IOException {
    StringBuilder content = new StringBuilder();
    BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));
    String line;
    while ((line = reader.readLine()) != null) {
      line = line.trim();
      if (line.isEmpty() || line.startsWith("#")) {
        continue;
      }
      content.append(line).append("\n");
    }
    reader.close();
    return content.toString();
  }
}
