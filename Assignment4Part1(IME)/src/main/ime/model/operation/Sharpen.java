package ime.model.operation;

import static ime.constants.FilterConstants.SHARPEN_KERNEL;

/**
 * This class represents the sharpen filter operation that enhances image details. It extends the
 * Filter class and provides the specific kernel used for sharpening. The sharpening process
 * emphasizes edges by applying the sharpen kernel to each pixel maintained in the constants file.
 */
public class Sharpen extends FilterWithPreview {

  /**
   * Returns the sharpen kernel used to apply the sharpening filter. The kernel defines how pixel
   * values are adjusted based on surrounding pixels to enhance sharpness in the image.
   *
   * @return a 2D float array representing the sharpen kernel.
   */
  protected float[][] getKernel() {
    return SHARPEN_KERNEL;
  }
}
