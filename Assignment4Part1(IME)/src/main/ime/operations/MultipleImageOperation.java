package ime.operations;

import java.io.IOException;

import ime.models.Image;
import java.util.List;

public interface MultipleImageOperation {

  Image apply(List<Image> images, String... args) throws IllegalArgumentException, IOException;
}
