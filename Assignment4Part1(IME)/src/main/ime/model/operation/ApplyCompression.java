package ime.model.operation;

import ime.model.image.Image;

/**
 * Implements image compression using wavelet transformation through a combination of Haar wavelet
 * transforms and threshold-based coefficient compression. This approach reduces the image size by
 * removing less significant details, resulting in a compressed version of the original image.
 */
public class ApplyCompression implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    float compressionRatio = Float.parseFloat(args[0]);
    if(compressionRatio < 0 || compressionRatio > 100) {
      throw new IllegalArgumentException("Compression ratio must be between 0 and 100");
    }
    WaveletImageCompressor haarWaveletImageCompressor = new HaarWaveletImageCompressor();
    return haarWaveletImageCompressor.applyWaveletCompression(inputImage, compressionRatio);
  }
}
