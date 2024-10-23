import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class RGBSplitTest extends ImageTestUtil{
  @Test
  public void testRGBSplitPNG() {
    Map<String, String> outputFileMap = new LinkedHashMap<>();
    outputFileMap.put("manhattan-small-red-actual.png", "manhattan-small-red-expected.png");
    outputFileMap.put("manhattan-small-green-actual.png", "manhattan-small-green-expected.png");
    outputFileMap.put("manhattan-small-blue-actual.png", "manhattan-small-blue-expected.png");
    runImageTest("TestScripts/rgb-split.txt", "manhattan-small.png", outputFileMap, "RGBSplitImages", null);
  }

}
