package ime.operations;


import static ime.constants.Constants.GAUSSIAN_BLUR_KERNEL;

public class Blur extends Filter {

  protected float[][] getKernel() {

    return GAUSSIAN_BLUR_KERNEL;
  }

  @Override
  protected int getStartIndexForRow() {
    return GAUSSIAN_BLUR_KERNEL.length / 2;
  }

  @Override
  protected int getLastIndexForRow(int width) {
    return width - GAUSSIAN_BLUR_KERNEL.length / 2 - 1;
  }

  @Override
  protected int getStartIndexForColumn() {
    return GAUSSIAN_BLUR_KERNEL.length / 2;
  }

  @Override
  protected int getLastIndexForColumn(int height) {
    return height - GAUSSIAN_BLUR_KERNEL.length / 2 - 1;
  }
}
