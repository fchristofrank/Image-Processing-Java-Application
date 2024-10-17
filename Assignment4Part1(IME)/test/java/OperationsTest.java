import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ime.cli.ImageProcessorCLI;

import static org.junit.Assert.fail;

public class OperationsTest {

  private void runTest(List<String> commands) {

    ImageProcessorCLI cli = new ImageProcessorCLI();
    for (String command : commands) {
      System.out.println("Executing command: " + command);
      System.out.flush();
      cli.processCommand(command);
    }
  }

  private List<String> getCommandsFromFile(String path) throws IOException {
    List<String> commands = new ArrayList<>();

    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      lines.map(String::trim)
              .filter(line -> !line.startsWith("#") && !line.isEmpty())
              .forEach(commands::add);
    }
    return commands;
  }

  @Test
  public void testVisualization() {

    String path = "java/TestScripts/VisualizationTestScript";
    try {
      runTest(getCommandsFromFile(path));
    } catch (IOException e) {
      fail("File import failed.");
    }
  }
}