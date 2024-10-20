import org.junit.Test;

public class FlipTest extends ImageTestUtil {

  @Test
  public void testHorizontalFlip() {
    runImageTest("TestScripts/hflip.txt", "manhattan-small-hflip-actual.png", "manhattan-small-hflip-expected.png", "FlipImages");
  }

  @Test
  public void testVerticalFlip() {
    runImageTest("TestScripts/vflip.txt", "manhattan-small-vflip-actual.png", "manhattan-small-vflip-expected.png", "FlipImages");
  }
}
