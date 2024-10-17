package ime.imageIO;

import java.io.IOException;

import ime.models.Image;


public interface Reader {
  Image read(String filename) throws IOException;
}
