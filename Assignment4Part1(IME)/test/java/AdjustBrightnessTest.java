import org.junit.Test;

public class AdjustBrightnessTest extends ImageTestUtil {

  @Test
  public void testFullBrighten() {
    runImageTest("TestScripts/brighten.txt", "manhattan-small-full-brighten-actual.png", "manhattan-small-full-brighten-expected.png", "AdjustBrightnessImages", "<alpha_value>", "300");
  }

  @Test
  public void testFullDarken() {
    runImageTest("TestScripts/darken.txt", "manhattan-small-full-darken-actual.png", "manhattan-small-full-darken-expected.png", "AdjustBrightnessImages", "<alpha_value>", "500");
  }

}
