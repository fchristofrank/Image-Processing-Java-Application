package ime.model.operation;

import ime.model.image.Image;
import java.util.List;

public interface MultipleImageOperation {

  Image apply(List<Image> images, String... args) throws IllegalArgumentException;
}
