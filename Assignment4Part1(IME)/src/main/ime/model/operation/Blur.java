package ime.model.operation;


import static ime.constants.FilterConstants.GAUSSIAN_BLUR_KERNEL;

public class Blur extends Filter {

  protected float[][] getKernel() {

    return GAUSSIAN_BLUR_KERNEL;
  }
}
