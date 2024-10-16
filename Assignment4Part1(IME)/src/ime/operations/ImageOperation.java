package ime.operations;

import ime.models.Image;

/**
 * Interface representing an operation that can be applied to an image.
 * Implementations of this interface define specific image processing
 * operations such as flip an image, adjust brightness of an image etc., that can modify or
 * transform an Image object.
 */
public interface ImageOperation {
    /**
     * Applies the specific image operation to the provided image.
     *
     * @param image The image to which the operation will be applied.
     * @return the new Image that is the result of the corresponding operation.
     */
    Image apply(Image image, String parameter);

}
