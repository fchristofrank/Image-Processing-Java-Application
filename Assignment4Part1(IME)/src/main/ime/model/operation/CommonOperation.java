package ime.model.operation;

import ime.model.image.Image;
import java.util.Map;

/**
 * This is a class that offers
 *
 */
public interface CommonOperation {

  Map<String, Map<Integer, Integer>> calculateFrequencies(Image inputImage);
}
