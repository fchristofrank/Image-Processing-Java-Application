package ime.operations;

import java.io.IOException;

import ime.models.Image;

/**
 * Interface representing an operation that can be applied to an image.
 * Implementations of this interface define specific image processing
 * operations such as flip an image, adjust brightness of an image etc., that can modify or
 * transform an Image object.
 */
public interface ImageOperation {

  Image apply(Image inputImage, String... args) throws IllegalArgumentException, IOException;

}
