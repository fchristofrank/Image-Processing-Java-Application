import org.junit.Test;

public class SepiaTest extends ImageTestUtil{
  @Test
  public void testSepiaPNG() {
    runImageTest("TestScripts/sepia.txt", "manhattan-small.png", "manhattan-small-sepia-actual.png", "manhattan-small-sepia-expected.png", "FilterImages");
  }

  @Test
  public void testSepiaJPG() {
    runImageTest("TestScripts/sepia.txt", "manhattan-small.jpg", "manhattan-small-sepia-actual.jpg", "manhattan-small-sepia-expected.jpg", "FilterImages");
  }
}
