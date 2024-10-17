import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

import ime.cli.ImageProcessorCLI;
import ime.imageIO.ImageFormat;
import ime.imageIO.Reader;
import ime.imageIO.ReaderFactory;
import ime.models.Image;

import static org.junit.Assert.assertEquals;

public class FlipTest {
  @Test
  public void testHorizontalFlip() {
    URL inputURL = getClass().getClassLoader().getResource("manhattan-small.png");
    URL outputURL = getClass().getClassLoader().getResource("");

    Assert.assertNotNull("Input resource not found", inputURL);
    Assert.assertNotNull("Output directory not found", outputURL);

    String inputPath = inputURL.getPath();
    String outputPath = outputURL.getPath() + "manhattan-small-hflip-actual.png";
    String imageName = "manhattan";

    String simulatedInput = String.format("load %s %s\nhorizontal-flip %s %s-hflip\nsave %s %s-hflip\nexit",
            inputPath, imageName, imageName, imageName, outputPath, imageName);
    ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
    System.setIn(input);
    new ImageProcessorCLI().run();
    try {
      Reader reader = ReaderFactory.createrReader(ImageFormat.PNG);
      Image actualImage = reader.read(outputPath);
      URL expectedURL = getClass().getClassLoader().getResource("manhattan-small-hflip-expected.png");
      Assert.assertNotNull("Expected image resource not found", expectedURL);

      Image expectedImage = reader.read(expectedURL.getPath());
      assertEquals(actualImage, expectedImage);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
