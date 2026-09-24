package org.julio.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class CompareUtils {
    private CompareUtils(){}


    public static boolean isSameImage(Image firstImage, Image secondImage){
        if (firstImage  == null || secondImage == null){
            return false;
        }
        try{

            BufferedImage firstBufferedImage = ImageUtils.toBufferedImage(firstImage);
            BufferedImage secondBufferedImage = ImageUtils.toBufferedImage(secondImage);

            DataBuffer dbFirst = firstBufferedImage.getData().getDataBuffer();
            int firstImageSize = dbFirst.getSize();

            DataBuffer dbSecond = secondBufferedImage.getData().getDataBuffer();
            int secondImageSize = dbSecond.getSize();

            // the second image must not match first image pixels to overwrite variable in Monitor
            if (secondImageSize == firstImageSize){
                for (int i = 0; i < secondImageSize; i++){
                    if (dbSecond.getElem(i) != dbFirst.getElem(i)){
                        return false;
                    }
                }
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean isSameText(String firstString, String secondString){
        return firstString.equals(secondString);
    }



}
