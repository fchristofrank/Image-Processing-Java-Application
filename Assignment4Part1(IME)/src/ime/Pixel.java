package ime;

/**
 * This class represents a pixel of an image which encapsulates the position and channel values.
 */
public class Pixel {
  private final int row;
  private final int col;
  private final Channel channel;

  /**
   * This method creates a pixel of an image with the given position and channel values.
   *
   * @param row     the row of the pixel.
   * @param col     the column of the pixel.
   * @param channel the channel of the pixel.
   */
  public Pixel(int row, int col, Channel channel) {
    this.row = row;
    this.col = col;
    this.channel = channel;
  }

  /**
   * This method gets the row of the pixel.
   *
   * @return the row of the pixel.
   */
  public int getRow() {
    return row;
  }

  /**
   * This method gets the column of the pixel.
   *
   * @return the column of the pixel.
   */
  public int getCol() {
    return col;
  }

  /**
   * This method gets the channel of the pixel.
   *
   * @return the channel of the pixel.
   */
  public Channel getChannel() {
    return channel;
  }
}
