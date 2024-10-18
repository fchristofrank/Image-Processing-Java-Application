import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ime.cli.ImageProcessorCLI;

public class TestUtil {

  static void runTest(String commands) {

    ImageProcessorCLI cli = new ImageProcessorCLI();
    System.out.println(commands);
    ByteArrayInputStream inputCommands = new ByteArrayInputStream(commands.getBytes());
    System.setIn(inputCommands);
    cli.run();
  }

  static String getCommandsFromFile(String path) throws IOException {
    List<String> commands = new ArrayList<>();

    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      lines.map(String::trim)
              .filter(line -> !line.startsWith("#") && !line.isEmpty())
              .map(line -> line + "\n")
              .forEach(commands::add);
    }
    return String.join("", commands);
  }
}
