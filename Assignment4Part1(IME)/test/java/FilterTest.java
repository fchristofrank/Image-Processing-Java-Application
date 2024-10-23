import org.junit.Test;

import ime.model.image.Image;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

public class FilterTest extends ImageTestUtil {

  @Test
  public void testBlur() {
    Map<String, String> outputFileMap1 = new HashMap<>();
    outputFileMap1.put("manhattan-small-blur-actual.jpg", "manhattan-small-blur-expected.jpg");

    Map<String, String> outputFileMap2 = new HashMap<>();
    outputFileMap2.put("manhattan-small-blur-actual.png", "manhattan-small-blur-expected.png");

    Map<String, String> outputFileMap3 = new HashMap<>();
    outputFileMap3.put("white_test_actual.jpg", "white_test_expected.jpg");

    Map<String, String> outputFileMap4 = new HashMap<>();
    outputFileMap4.put("white_test_actual.png", "white_test_expected.png");

    Map<String, String> outputFileMap5 = new HashMap<>();
    outputFileMap5.put("black_test_actual.png", "black_test_expected.png");

    Map<String, String> outputFileMap6 = new HashMap<>();
    outputFileMap6.put("black_test_actual.jpg", "black_test_expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/filter.txt", "manhattan-small.jpg", outputFileMap1, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
    runImageTest("TestScripts/filter.txt", "manhattan-small.png", outputFileMap2, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
    runImageTest("TestScripts/filter.txt", "white_test.jpg", outputFileMap3, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
    runImageTest("TestScripts/filter.txt", "white_test.png", outputFileMap4, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
    runImageTest("TestScripts/filter.txt", "black_test.png", outputFileMap5, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
    runImageTest("TestScripts/filter.txt", "black_test.jpg", outputFileMap6, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
    runImageTest(
            "TestScripts/filter.txt",
            "manhattan-small.png",
            "manhattan-small-blur-actual.png",
            "manhattan-small-blur-expected.png",
            "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "white_test.jpg",
            "white_test_actual.jpg",
            "white_test_expected.jpg",
            "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "white_test.png",
            "white_test_actual.png",
            "white_test_expected.png",
            "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "black_test.png",
            "black_test_actual.png",
            "black_test_expected.png",
            "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "black_test.jpg",
            "black_test_actual.jpg",
            "black_test_expected.jpg",
            "FilterImages");
  }

  @Test
  public void testSharpen() {
    Map<String, String> outputFileMap1 = new HashMap<>();
    outputFileMap1.put("manhattan-small-sharpen-actual.jpg", "manhattan-small-sharpen-expected.jpg");

    Map<String, String> outputFileMap2 = new HashMap<>();
    outputFileMap2.put("manhattan-small-sharpen-actual.png", "manhattan-small-sharpen-expected.png");

    Map<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/filter.txt", "manhattan-small.jpg", outputFileMap1, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
    runImageTest("TestScripts/filter.txt", "manhattan-small.png", outputFileMap2, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

  @Test
  public void testBlurOperation() {
    Image image = loadImageFromResources("manhattan-small.png");
    Image expectedImage = loadImageFromResources("manhattan-small.png");
    assertEquals(expectedImage, image);
  }
}

