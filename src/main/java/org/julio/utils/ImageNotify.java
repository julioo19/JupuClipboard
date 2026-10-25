package org.julio.utils;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ImageNotify implements ImageObserver {
    private CountDownLatch latch;
    public ImageNotify(){
        latch = new CountDownLatch(1);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        if ((infoflags & ALLBITS) != 0){
            latch.countDown();
            System.out.println("Image is fully loaded!");
            return false;
        }
        if ((infoflags & ABORT) != 0){
            latch.countDown();
            System.out.println("Image load aborted...");
            return false;
        }
        return true;
    }
    /*
    * TODO: Implement a CountdownLatch to stop de asynchronous method
    *
    * */

    public void waitForLoad() throws InterruptedException {
        latch.await(5, TimeUnit.SECONDS);
    }
}
