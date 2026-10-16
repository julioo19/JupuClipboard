package org.julio.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public final class ImageUtils {

    private ImageUtils(){}

    public static BufferedImage toBufferedImage(Image image){
        if (image instanceof BufferedImage){
            return (BufferedImage) image;
        }
        /*// this creates a transparent bufferedimage
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;*/
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Image dimensions are invalid");
        }

        BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bimage.getGraphics().drawImage(image, 0, 0, null);
        return bimage;

    }

    public static String toBase64(Image img){
        try{
            BufferedImage bimage = toBufferedImage(img);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "png", baos);
            byte[] encodedImage = baos.toByteArray();
            return Base64.getEncoder().encodeToString(encodedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isImageFullyLoaded(){
        /*
        TODO: notify if image is fully loaded
        *
        *
        *
        * */
        return false;
    }
}
