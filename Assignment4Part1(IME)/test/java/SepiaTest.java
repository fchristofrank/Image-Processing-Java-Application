import org.junit.Test;

public class SepiaTest extends ImageTestUtil{
  @Test
  public void testSepia() {
    runImageTest("TestScripts/sepia.txt", "manhattan-small.png", "manhattan-small-sepia-actual.png", "manhattan-small-sepia-expected.png", "FilterImages");
  }
}
