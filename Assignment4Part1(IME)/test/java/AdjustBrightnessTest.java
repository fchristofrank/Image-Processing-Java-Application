import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

import ime.cli.ImageProcessorCLI;
import ime.imageIO.ImageFormat;
import ime.imageIO.Reader;
import ime.imageIO.ReaderFactory;
import ime.models.Image;

public class AdjustBrightnessTest {

  @Test
  public void testBrighten() {
    runBrightnessTest("manhattan-small.png", "manhattan-small-full-brighten-actual.png",
            "manhattan-small-full-brighten-expected.png", "brighten 300");
  }

  @Test
  public void testDarken() {
    runBrightnessTest("manhattan-small.png", "manhattan-small-full-darken-actual.png",
            "manhattan-small-full-darken-expected.png", "darken -300");
  }

  private void runBrightnessTest(String inputFileName, String outputFileName,
                                 String expectedFileName, String operation) {
    URL inputURL = getClass().getClassLoader().getResource(inputFileName);
    URL outputURL = getClass().getClassLoader().getResource("");

    Assert.assertNotNull("Input resource not found", inputURL);
    Assert.assertNotNull("Output directory not found", outputURL);

    String inputPath = inputURL.getPath();
    String outputPath = outputURL.getPath() + outputFileName;
    String imageName = "manhattan";

    String simulatedInput = String.format("load %s %s\n%s %s %s-modified\nsave %s %s-modified\nexit",
            inputPath, imageName, operation, imageName, imageName, outputPath, imageName);

    ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
    System.setIn(input);

    new ImageProcessorCLI().run();

    try {
      Reader reader = ReaderFactory.createrReader(ImageFormat.PNG);
      Image actualImage = reader.read(outputPath);

      URL expectedURL = getClass().getClassLoader().getResource(expectedFileName);
      Assert.assertNotNull("Expected image resource not found", expectedURL);

      Image expectedImage = reader.read(expectedURL.getPath());
      Assert.assertEquals(expectedImage, actualImage);
    } catch (IOException e) {
      Assert.fail("IOException occurred: " + e.getMessage());
    }
  }
}