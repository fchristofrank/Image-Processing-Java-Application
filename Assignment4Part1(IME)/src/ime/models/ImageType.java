package ime.models;

import java.awt.image.BufferedImage;

public enum ImageType {
    RGB(BufferedImage.TYPE_INT_RGB),
    GRAYSCALE(BufferedImage.TYPE_BYTE_GRAY),
    ARGB(BufferedImage.TYPE_INT_ARGB);

    private final int bufferedImageType;

    ImageType(int bufferedImageType) {
        this.bufferedImageType = bufferedImageType;
    }

    public int getBufferedImageType() {
        return this.bufferedImageType;
    }
}