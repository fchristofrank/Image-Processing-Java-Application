import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionalities of flip image operation.
 */
public class FlipOperationTest extends ImageOperationTest {

  @Test
  public void testHorizontalFlipPNG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-hflip-actual.png", "manhattan-small-hflip-expected.png");

    Map<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/hflip.txt", "manhattan-small.png", outputFileMap, "FlipImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

  @Test
  public void testVerticalFlipPNG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-actual.png", "manhattan-small-vflip-expected.png");

    Map<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/vflip.txt", "manhattan-small.png", outputFileMap, "FlipImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

  @Test
  public void testHorizontalFlipJPG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-hflip-actual.jpg", "manhattan-small-hflip-expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/hflip.txt", "manhattan-small.jpg", outputFileMap, "FlipImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

  @Test
  public void testVerticalFlipJPG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-actual.jpg", "manhattan-small-vflip-expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/vflip.txt", "manhattan-small.jpg", outputFileMap, "FlipImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }
}

