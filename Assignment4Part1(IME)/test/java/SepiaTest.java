import org.junit.Test;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SepiaTest extends ImageTestUtil {

  @Test
  public void testSepiaPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-sepia-actual.png", "manhattan-small-sepia-expected.png");

    HashMap<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/sepia.txt", "manhattan-small.png", outputFileMap, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }

  @Test
  public void testSepiaJPG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-sepia-actual.jpg", "manhattan-small-sepia-expected.jpg");

    HashMap<String, String> replacements = new HashMap<>();

    runImageTest("TestScripts/sepia.txt", "manhattan-small.jpg", outputFileMap, "FilterImages", replacements, (expected, actual) -> assertEquals("Images should be identical", expected, actual));
  }
}
