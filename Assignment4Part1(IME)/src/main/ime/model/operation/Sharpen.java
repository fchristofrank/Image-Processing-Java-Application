package ime.model.operation;

import static ime.constants.FilterConstants.SHARPEN_KERNEL;

public class Sharpen extends Filter {

  protected float[][] getKernel() {

    return SHARPEN_KERNEL;
  }
}
