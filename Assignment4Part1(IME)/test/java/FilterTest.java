import org.junit.Test;

public class FilterTest extends ImageTestUtil{

  @Test
  public void testBlur() {
    runImageTest("TestScripts/filter.txt", "manhattan-small-blur-actual.png", "manhattan-small-blur-expected.png", "FilterImages");
  }

  @Test
  public void testSharpen() {
    runImageTest("TestScripts/filter.txt", "manhattan-small-sharpen-actual.png", "manhattan-small-sharpen-expected.png", "FilterImages");
  }
}
