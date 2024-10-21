package ime.operations;

import static ime.constants.Constants.SHARPEN_KERNEL;

public class Sharpen extends Filter {

  protected float[][] getKernel() {

    return SHARPEN_KERNEL;
  }

  @Override
  protected int getStartIndexForRow() {
    return SHARPEN_KERNEL.length / 2;
  }

  @Override
  protected int getLastIndexForRow(int width) {
    return width - 1 - SHARPEN_KERNEL.length / 2;
  }

  @Override
  protected int getStartIndexForColumn() {
    return SHARPEN_KERNEL.length / 2;
  }

  @Override
  protected int getLastIndexForColumn(int height) {
    return height - 1 - SHARPEN_KERNEL.length / 2;
  }
}
