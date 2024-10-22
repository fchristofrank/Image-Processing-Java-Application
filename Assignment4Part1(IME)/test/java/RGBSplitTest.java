import org.junit.Test;

public class RGBSplitTest extends ImageTestUtil{
  @Test
  public void testSepia() {
    runImageTest("TestScripts/rgb-split.txt", "manhattan-small.png", "manhattan-small-red-actual.png", "manhattan-small-red-expected.png", "RGBSplitImages");
  }
}
