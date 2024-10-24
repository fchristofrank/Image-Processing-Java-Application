import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the functionalities of flip image operation.
 */
public class FlipOperationTest extends ImageOperationTest {

  @Test
  public void testHorizontalFlipPNG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-hflip-actual.png", "manhattan-small-hflip-expected.png");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/hflip.txt", "manhattan-small.png",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipPNG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-actual.png", "manhattan-small-vflip-expected.png");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/vflip.txt", "manhattan-small.png",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipInvalidArgsPNG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-invalid-actual.png", "manhattan-small-vflip-expected.png");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/Fail/vflip-invalid.txt", "manhattan-small.png",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //Exception has been thrown due to invalid arguments.
    }
  }

  @Test
  public void testHorizontalFlipJPG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-hflip-actual.jpg", "manhattan-small-hflip-expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/hflip.txt", "manhattan-small.jpg",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipJPG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-actual.jpg", "manhattan-small-vflip-expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/vflip.txt", "manhattan-small.jpg",
              outputFileMap, "FlipImages", replacements, (expected, actual) ->
                      assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipInvalidArgsJPG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-invalid-actual.jpg", "manhattan-small-vflip-expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/Fail/vflip-invalid.txt", "manhattan-small.jpg",
              outputFileMap, "FlipImages", replacements, (expected, actual) ->
                      assertEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //Exception has been thrown due to invalid arguments.
    }
  }

  @Test
  public void testHorizontalFlipPPM() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-hflip-actual.ppm", "manhattan-small-hflip-expected.ppm");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/hflip.txt", "manhattan-small.ppm",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipPPM() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-actual.ppm", "manhattan-small-vflip-expected.ppm");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/vflip.txt", "manhattan-small.ppm",
              outputFileMap, "FlipImages", replacements, (expected, actual) ->
                      assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipInvalidArgsPPM() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-invalid-actual.ppm", "manhattan-small-vflip-expected.ppm");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/Fail/vflip-invalid.txt", "manhattan-small.ppm",
              outputFileMap, "FlipImages", replacements, (expected, actual) ->
                      assertEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //Exception have been thrown due to invalid number of arguments.
    }
  }

}

