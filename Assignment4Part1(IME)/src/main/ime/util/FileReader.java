package ime.util;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
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
