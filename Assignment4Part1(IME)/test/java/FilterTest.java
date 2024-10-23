import org.junit.Test;

import ime.model.image.Image;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

public class FilterTest extends ImageTestUtil {

  @Test
  public void testBlur() {

    // Blur JPG test
    runImageTest(
        "TestScripts/blur.txt",
        "manhattan-small.jpg",
        "manhattan-small-blur-actual.jpg",
        "manhattan-small-blur-expected.jpg",
        "FilterImages");

    // Blur PNG test
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
        "TestScripts/blur.txt",
        "manhattan-small.png",
        "manhattan-small-blur-actual.png",
        "manhattan-small-blur-expected.png",
        "FilterImages");

    // Blur White jpg test
    runImageTest(
        "TestScripts/blur.txt",
        "white_test.jpg",
        "white_test_actual.jpg",
        "white_test_blur_expected.jpg",
        "FilterImages");

    // Blur White png test
    runImageTest(
        "TestScripts/blur.txt",
        "white_test.png",
        "white_test_actual.png",
        "white_test_blur_expected.png",
        "FilterImages");

    // Blur Black png test
    runImageTest(
        "TestScripts/blur.txt",
        "black_test.png",
        "black_test_actual.png",
        "black_test_blur_expected.png",
        "FilterImages");

    // Blur Black jpg test
    runImageTest(
        "TestScripts/blur.txt",
        "black_test.jpg",
        "black_test_actual.jpg",
        "black_test_blur_expected.jpg",
        "FilterImages");

  }

  @Test
  public void testSharpen() {
    runImageTest(
        "TestScripts/sharpen.txt",
        "manhattan-small.jpg",
        "manhattan-small-sharpen-actual.jpg",
        "manhattan-small-sharpen-expected.jpg",
        "FilterImages");
    Map<String, String> outputFileMap1 = new HashMap<>();
    outputFileMap1.put("manhattan-small-sharpen-actual.jpg", "manhattan-small-sharpen-expected.jpg");

    Map<String, String> outputFileMap2 = new HashMap<>();
    outputFileMap2.put("manhattan-small-sharpen-actual.png", "manhattan-small-sharpen-expected.png");

    runImageTest(
        "TestScripts/sharpen.txt",
        "manhattan-small.png",
        "manhattan-small-sharpen-actual.png",
        "manhattan-small-sharpen-expected.png",
        "FilterImages");

    runImageTest(
        "TestScripts/sharpen.txt",
        "white_test.jpg",
        "white_test_sharpen_actual.jpg",
        "white_test_sharpen_expected.jpg",
        "FilterImages");
    Map<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/filter.txt", "manhattan-small.jpg", outputFileMap1, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
    runImageTest("TestScripts/filter.txt", "manhattan-small.png", outputFileMap2, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));

    runImageTest(
        "TestScripts/sharpen.txt",
        "white_test.png",
        "white_test_sharpen_actual.png",
        "white_test_sharpen_expected.png",
        "FilterImages");
  }

}
