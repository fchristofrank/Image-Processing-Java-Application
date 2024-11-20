package ime.model.operation;

/**
 * The Blur class is a specific type of filter operation that applies a Gaussian blur to an image.
 * This class extends the general `Filter` class and provides the specific kernel (a matrix of
 * weights) used to perform the blurring effect.
 *
 * <p>Gaussian blur is a smoothing technique that reduces noise and detail in an image. The kernel
 * matrix defines the degree of blurring by averaging the pixel values in a weighted manner. The
 * larger the kernel, the stronger the blur effect.
 */
public class Blur extends FilterWithPreview {

  /**
   * Retrieves the Gaussian blur kernel, which is a pre-defined matrix used for applying the blur
   * effect to an image. The kernel is applied to each pixel and its neighboring pixels to compute a
   * new value that creates the blur effect. The kernel returned by this method is specific to
   * Gaussian blur and determines how the blur is applied during the filtering process.
   *
   * @return a 2D array representing the Gaussian blur kernel used for this filter.
   */
  protected float[][] getKernel() {
    return new float[][]{
        {1f / 16f, 1f / 8f, 1f / 16f},
        {1f / 8f, 1f / 4f, 1f / 8f},
        {1f / 16f, 1f / 8f, 1f / 16f}
    };
  }
}
