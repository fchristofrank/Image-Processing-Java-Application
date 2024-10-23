import org.junit.Test;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionalities of adjust brightness image operation.
 */
public class BrightnessOperationTest extends ImageOperationTest {

  @Test
  public void testFullBrightenPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-brighten-actual.png", "manhattan-small-full-brighten-expected.png");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");

    runImageTest("TestScripts/brighten.txt", "manhattan-small.png", outputFileMap, "AdjustBrightnessImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

  @Test
  public void testFullDarkenPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-darken-actual.png", "manhattan-small-full-darken-expected.png");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "500");

    runImageTest("TestScripts/darken.txt", "manhattan-small.png", outputFileMap, "AdjustBrightnessImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

  @Test
  public void testFullBrightenJPG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-brighten-actual.jpg", "manhattan-small-full-brighten-expected.jpg");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");

    runImageTest("TestScripts/brighten.txt", "manhattan-small.jpg", outputFileMap, "AdjustBrightnessImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

  @Test
  public void testFullDarkenJPG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-darken-actual.jpg", "manhattan-small-full-darken-expected.jpg");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "500");

    runImageTest("TestScripts/darken.txt", "manhattan-small.jpg", outputFileMap, "AdjustBrightnessImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

}

