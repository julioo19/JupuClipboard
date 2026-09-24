package org.julio.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class ImageUtils {

    private ImageUtils(){}

    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage){
            return (BufferedImage) img;
        }
        // this creates a transparent bufferedimage
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}
