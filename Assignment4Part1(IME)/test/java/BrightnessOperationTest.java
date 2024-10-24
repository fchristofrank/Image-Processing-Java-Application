import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the functionalities of adjust brightness image operation.
 */
public class BrightnessOperationTest extends ImageOperationTest {

  @Test
  public void testFullBrightenPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-brighten-actual.png",
            "manhattan-small-full-brighten-expected.png");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.png", outputFileMap,
              "AdjustBrightnessImages",
              replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testFullDarkenPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-darken-actual.png",
            "manhattan-small-full-darken-expected.png");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "500");

    try {
      runImageTest("TestScripts/darken.txt", "manhattan-small.png",
              outputFileMap, "AdjustBrightnessImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testAdjustBrightnessInvalidArgumentPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-fail-brighten-actual.png",
            "manhattan-full-brighten-expected.png");

    HashMap<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.png", outputFileMap,
              "AdjustBrightnessImages", replacements,
              (expected, actual) ->
                      assertNotEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is thrown as the arguments are invalid.
    }
  }

  @Test
  public void testFullBrightenJPG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-brighten-actual.jpg",
            "manhattan-small-full-brighten-expected.jpg");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.jpg", outputFileMap,
              "AdjustBrightnessImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testFullDarkenJPG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-darken-actual.jpg",
            "manhattan-small-full-darken-expected.jpg");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "500");

    try {
      runImageTest("TestScripts/darken.txt", "manhattan-small.jpg",
              outputFileMap, "AdjustBrightnessImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testAdjustBrightnessInvalidArgumentJPG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-fail-brighten-actual.jpg",
            "manhattan-full-brighten-expected.jpg");

    HashMap<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.jpg", outputFileMap,
              "AdjustBrightnessImages", replacements,
              (expected, actual) ->
                      assertNotEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is thrown as the arguments are invalid.
    }
  }

  @Test
  public void testFullBrightenPPM() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-brighten-actual.ppm",
            "manhattan-small-full-brighten-expected.ppm");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.ppm",
              outputFileMap, "AdjustBrightnessImages",
              replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testFullDarkenPPM() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-darken-actual.ppm",
            "manhattan-small-full-darken-expected.ppm");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "500");

    try {
      runImageTest("TestScripts/darken.txt", "manhattan-small.ppm",
              outputFileMap, "AdjustBrightnessImages", replacements,
              (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testAdjustBrightnessInvalidArgumentPPM() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-fail-brighten-actual.ppm",
            "manhattan-full-brighten-expected.ppm");

    HashMap<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.ppm", outputFileMap,
              "AdjustBrightnessImages", replacements,
              (expected, actual) ->
                      assertNotEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is thrown as the arguments are invalid.
    }
  }

}

