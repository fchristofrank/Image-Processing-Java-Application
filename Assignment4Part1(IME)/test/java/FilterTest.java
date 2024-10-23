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
  }

  @Test
  public void testSharpen() {
    runImageTest(
        "TestScripts/filter.txt",
        "manhattan-small.jpg",
        "manhattan-small-sharpen-actual.jpg",
        "manhattan-small-sharpen-expected.jpg",
        "FilterImages");
  }

  @Test
  public void testBlurOperation() {
    Image image = loadImageFromResources("manhattan-small.png");
    Image expectedImage = loadImageFromResources("manhattan-small.png");
    assertEquals(expectedImage, image);
  }
}
