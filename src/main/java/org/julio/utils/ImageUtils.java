package org.julio.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public final class ImageUtils {

    private ImageUtils(){}

    public static BufferedImage toBufferedImage(Image image) throws InterruptedException {
        //System.out.println("Iniciando toBufferedImage, tipo: " + image.getClass().getName());
        if (image instanceof BufferedImage){
            if(((BufferedImage) image).getType() != BufferedImage.TYPE_INT_ARGB){
                BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                Graphics2D bGr = bimage.createGraphics();
                boolean drawn = bGr.drawImage(image, 0, 0, null);
                if (drawn) System.out.println("Image drawn!");
                bGr.dispose();
                System.out.println(bimage.getType());
                return bimage;
            }
            else{
                System.out.println((((BufferedImage) image).getType()));
                return (BufferedImage) image;
            }
        }
        else{
            ImageNotify imageNotify = new ImageNotify();
            image.getWidth(imageNotify);
            image.getHeight(imageNotify);
            imageNotify.waitForLoad();
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            if (width <= 0 || height <= 0){
                throw new IllegalArgumentException("Image dimensions are invalid");
            }

            BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bimage.createGraphics();
            boolean asd = bGr.drawImage(image,0,0,null);
            if (asd){
                System.out.println("Image completely drawed!");
            }
            bGr.dispose();
            //bimage.getGraphics().drawImage(image, 0, 0, imageNotify);
            return bimage;
        }

    }

    public static String toBase64(Image img){
        try{
            //System.out.println("Iniciando la conversion a base64...");
            BufferedImage bimage = toBufferedImage(img);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            boolean successfulWrite = ImageIO.write(bimage, "png", baos);
            if (successfulWrite) System.out.println("Writing successful!");
            byte[] encodedImage = baos.toByteArray();
            System.out.println(baos.size());
            return Base64.getEncoder().encodeToString(encodedImage);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
