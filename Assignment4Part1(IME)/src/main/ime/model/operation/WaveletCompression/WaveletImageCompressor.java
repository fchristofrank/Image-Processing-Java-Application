package ime.model.operation.WaveletCompression;

import ime.model.image.Image;

/**
 * A generic interface for applying wavelet transformations and compression techniques to images.
 * This interface defines methods for compressing and reconstructing image data through
 * wavelet-based algorithms.
 *
 * <p>The main purpose is to transform an image, represented as a two-dimensional float array of
 * pixels, by applying a wavelet transformation and compressing it based on a specified compression
 * ratio. Each transformation reduces image details, helping achieve data reduction while retaining
 * essential visual information.
 */
public interface WaveletImageCompressor {

  /**
   * Applies wavelet compression to the specified image based on the given compression ratio.
   *
   * @param image the {@link Image} object to be compressed, represented as a two-dimensional float
   *     array of pixels.
   * @param compressionRatio a float value between 0 and 1 that determines the level of compression
   *     to apply. A higher value indicates more compression, potentially sacrificing image quality.
   * @return a new {@link Image} object representing the compressed version of the input image. The
   *     returned image may exhibit reduced detail compared to the original, depending on the
   *     compression ratio.
   */
  Image applyWaveletCompression(Image image, float compressionRatio);
}
