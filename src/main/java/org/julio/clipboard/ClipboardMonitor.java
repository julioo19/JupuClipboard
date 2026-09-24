package org.julio.clipboard;

import org.julio.FileMetadata;
import org.julio.utils.CompareUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * This class will observe the System Clipboard
 *
 * @author Jupu
 * @version 1.0
 */
public class ClipboardMonitor implements Runnable{
    private volatile boolean running = true;
    private ClipboardManager clipboardManager;
    private volatile boolean contentStatus = false;

    public ClipboardMonitor(ClipboardManager clipboardManager){
        this.clipboardManager = new ClipboardManager();
    }


    @Override
    public void run() {
        listenToClipboard();
    }

    private void listenToClipboard(){
        System.out.println("Listening to clientClipboard...");
        String clipboardContent = "";
        Image imageContent = null;
        Set<FileMetadata> fileSet = Collections.synchronizedSet(new HashSet<>());
        while (running){
            try{
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted while waiting for the Clipboard, cancelling operation...");
                Thread.currentThread().interrupt();
                killMonitor();
                throw new RuntimeException(e);
            }
            List<DataFlavor> dataFlavors = Arrays.asList(clipboardManager.clientClipboard.getAvailableDataFlavors());
            if (dataFlavors.contains(DataFlavor.stringFlavor)){
                String textFromClipboard = clipboardManager.getClipboard();
                if (!CompareUtils.isSameText(clipboardContent, textFromClipboard)){
                    //contentStatus = true;
                    clipboardContent = textFromClipboard;
                    //clipboardManager.setClipboard(clipboardContent);
                    System.out.println("Clipboard data was changed!!!");
                }
            }
            else if (dataFlavors.contains(DataFlavor.imageFlavor)){
                Image imageFromClipboard = clipboardManager.getImageClipboard();
                if (!CompareUtils.isSameImage(imageContent, imageFromClipboard)){
                    imageContent = imageFromClipboard;
                    //clipboardManager.setClipboard(imageContent);
                    System.out.println("Clipboard image was changed!");
                }
            }
        }
    }


    private void killMonitor(){
        running = false;
    }
}
