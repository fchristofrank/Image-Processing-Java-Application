import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the functionalities of chaining image operations.
 */
public class ChainOperationTest extends ImageOperationTest {
  @Test
  public void testMultipleOperation() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-chain-expected.png", "manhattan-small-chain-expected.png");

    Map<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");
    try {
      runImageTest("TestScripts/chain-operation.txt",
              "manhattan-small.png", outputFileMap,
              "ChainOperationImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }
}
