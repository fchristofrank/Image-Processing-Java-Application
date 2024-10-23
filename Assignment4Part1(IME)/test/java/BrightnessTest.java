import org.junit.Test;

public class BrightnessTest extends ImageTestUtil {

  @Test
  public void testFullBrightenPNG() {
    runImageTest("TestScripts/brighten.txt", "manhattan-small.png", "manhattan-small-full-brighten-actual.png", "manhattan-small-full-brighten-expected.png", "AdjustBrightnessImages", "<alpha_value>", "300");
  }

  @Test
  public void testFullDarkenPNG() {
    runImageTest("TestScripts/darken.txt", "manhattan-small.png", "manhattan-small-full-darken-actual.png", "manhattan-small-full-darken-expected.png", "AdjustBrightnessImages", "<alpha_value>", "500");
  }

  @Test
  public void testFullBrightenJPG() {
    runImageTest("TestScripts/brighten.txt", "manhattan-small.jpg", "manhattan-small-full-brighten-actual.jpg", "manhattan-small-full-brighten-expected.jpg", "AdjustBrightnessImages", "<alpha_value>", "300");
  }

  @Test
  public void testFullDarkenJPG() {
    runImageTest("TestScripts/darken.txt", "manhattan-small.jpg", "manhattan-small-full-darken-actual.jpg", "manhattan-small-full-darken-expected.jpg", "AdjustBrightnessImages", "<alpha_value>", "500");
  }

}
