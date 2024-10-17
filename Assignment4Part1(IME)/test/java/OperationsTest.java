//import org.junit.Test;
//
//import ime.models.Image;
//import ime.operations.Visualize;
//import ime.utils.ImageReader;
//
//import static org.junit.Assert.assertEquals;
//
//public class OperationsTest {
//
//  String testBasePath = "C:\\Users\\frank\\OneDrive\\Desktop\\PDP\\AssignmentProject\\ImageManipulationAndEnhancement";
//
//  @Test
//  public void testVisualization() {
//
//    String actualImagePath = testBasePath + "\\Resources\\Operations\\VisualizationTest1.jpg";
//    String expectedImagePath = testBasePath + "\\Resources\\Operations\\VisualizationTest1Expected.png";
//
//    Image actualImage = ImageReader.readImage(actualImagePath);
//    Image expectedImage = ImageReader.readImage(expectedImagePath);
//
//    assert actualImage != null;
//    System.out.println(actualImage.getHeight());
//    System.out.println(actualImage.getWidth());
//    System.out.println(actualImage.applyOperation(new Visualize(), "green"));
//
//    for (int i = 0; i < actualImage.getHeight(); i++) {
//      for (int j = 0; j < actualImage.getWidth(); j++) {
//        assertEquals(expectedImage.getPixel(i, j).getGreen(), actualImage.getPixel(i, j).getGreen(), 2);
//      }
//    }
//
//  }
//}
