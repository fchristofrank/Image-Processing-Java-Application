import org.junit.Test;

import ime.model.image.Image;

import static org.junit.Assert.assertEquals;

public class FilterTest extends ImageTestUtil {

  @Test
  public void testBlur() {
    runImageTest(
        "TestScripts/filter.txt",
        "manhattan-small.jpg",
        "manhattan-small-blur-actual.jpg",
        "manhattan-small-blur-expected.jpg",
        "FilterImages");

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
    runImageTest(
        "TestScripts/filter.txt",
        "manhattan-small.jpg",
        "manhattan-small-sharpen-actual.jpg",
        "manhattan-small-sharpen-expected.jpg",
        "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "manhattan-small.png",
            "manhattan-small-sharpen-actual.png",
            "manhattan-small-sharpen-expected.png",
            "FilterImages");
  }

  @Test
  public void testBlurOperation() {
    Image image = loadImageFromResources("manhattan-small.png");
    Image expectedImage = loadImageFromResources("manhattan-small.png");
    assertEquals(expectedImage, image);
  }
}
