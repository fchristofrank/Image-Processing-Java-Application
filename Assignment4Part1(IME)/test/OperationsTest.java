import ime.models.ImageProcessor;
import ime.operations.VisualizeComponents;
import ime.utils.ImageReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperationsTest {

    String testBasePath = "C:\\Users\\frank\\OneDrive\\Desktop\\PDP\\AssignmentProject\\ImageManipulationAndEnhancement";

    @Test
    public void testVisualization() {

        String actualImagePath = testBasePath + "\\Resources\\Operations\\VisualizationTest1.jpg";
        String expectedImagePath = testBasePath + "\\Resources\\Operations\\VisualizationTest1Expected.png";

        ImageProcessor actualImage = ImageReader.readImage(actualImagePath);
        ImageProcessor expectedImage = ImageReader.readImage(expectedImagePath);

        assert actualImage != null;
        System.out.println(actualImage.getHeight());
        System.out.println(actualImage.getWidth());
        System.out.println(actualImage.applyOperation(new VisualizeComponents(),"red"));

        for (int i = 0; i < actualImage.getWidth(); i++) {
            for (int j = 0; j < actualImage.getHeight(); j++) {
                assertEquals(expectedImage.getPixel(i,j).getGreen(),actualImage.getPixel(i,j).getGreen(),2);
            }
        }

    }
}
