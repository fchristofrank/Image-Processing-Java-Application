import org.junit.Test;

public class FlipTest extends ImageTestUtil {

  @Test
  public void testHorizontalFlipPNG() {
    runImageTest("TestScripts/hflip.txt", "manhattan-small.png", "manhattan-small-hflip-actual.png", "manhattan-small-hflip-expected.png", "FlipImages");
  }

  @Test
  public void testVerticalFlipPNG() {
    runImageTest("TestScripts/vflip.txt", "manhattan-small.png", "manhattan-small-vflip-actual.png", "manhattan-small-vflip-expected.png", "FlipImages");
  }

  @Test
  public void testHorizontalFlipJPG() {
    runImageTest("TestScripts/hflip.txt", "manhattan-small.jpg", "manhattan-small-hflip-actual.jpg", "manhattan-small-hflip-expected.jpg", "FlipImages");
  }

  @Test
  public void testVerticalFlipJPG() {
    runImageTest("TestScripts/vflip.txt", "manhattan-small.jpg", "manhattan-small-vflip-actual.jpg", "manhattan-small-vflip-expected.jpg", "FlipImages");
  }
}
