import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class OperationsTest {

  @Test(timeout=5000)
  public void testVisualization() {

    String path = "java/TestScripts/VisualizationTestScript";
    try {
      TestUtil.runTest(TestUtil.getCommandsFromFile(path));
    } catch (IOException e) {
      fail("File import failed.");
    }

  }
}