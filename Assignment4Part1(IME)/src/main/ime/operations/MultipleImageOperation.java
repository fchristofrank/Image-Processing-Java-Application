package ime.operations;

import java.io.IOException;

import ime.models.Image;

public interface MultipleImageOperation {

  Image apply(Image redImage, Image blueImage, Image greenImage, Image... optionalImages)
          throws IllegalArgumentException, IOException;
}
