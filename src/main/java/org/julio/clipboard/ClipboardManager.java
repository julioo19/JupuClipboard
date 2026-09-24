package org.julio.clipboard;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ClipboardManager {
    protected Clipboard clientClipboard;

    public ClipboardManager (){
        this.clientClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public synchronized void setClipboard(Image imageToClipboard){
        ImageSelection imageSelection = new ImageSelection(imageToClipboard);
        clientClipboard.setContents(imageSelection, null);
    }
    public synchronized void setClipboard(String messageToClipboard){
        StringSelection stringSelection = new StringSelection(messageToClipboard);
        clientClipboard.setContents(stringSelection, null);

    }

    public synchronized void setClipboard(List<File> fileList){
        FileSelection fileSelection = new FileSelection(fileList);
        clientClipboard.setContents(fileSelection, null);
    }

    public synchronized String getClipboard(){
        String clipboardContent = "";
        try{
            Transferable contents = clientClipboard.getContents(null);
            boolean hasTransferableText = contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
            if (hasTransferableText){
                clipboardContent = (String) contents.getTransferData(DataFlavor.stringFlavor);
            }
            else{
                System.out.println("No text was found in Clipboard!");
            }
        }
        catch (UnsupportedFlavorException e){
            System.err.println("Clipboard data is not text!");
        }
        catch (IOException e){
            System.err.println("An I/O error occurred when retrieving clipboard data!");
        }

        return clipboardContent;
    }

    public synchronized Image getImageClipboard(){
        Image clipboardContent = null;
        try{
            Transferable contents = clientClipboard.getContents(null);
            boolean hasTransferableImage = contents != null && contents.isDataFlavorSupported(DataFlavor.imageFlavor);
            if (hasTransferableImage){
                clipboardContent = (Image) contents.getTransferData(DataFlavor.imageFlavor);
            }
            else{
                System.out.println("No image was found in clipboard");
            }
        }
        catch (UnsupportedFlavorException e){
            System.err.println("Clipboard data is not an image!");
        }
        catch (IOException e){
            System.err.println("An I/O error occurred when retrieving clipboard data!");
        }
        return clipboardContent;
    }

    @SuppressWarnings("unchecked")
    public synchronized List<File> getFileClipboard(){
        List <File> clipboardContents = new ArrayList<>();
        try{
            Transferable contents = clientClipboard.getContents(null);
            boolean hasTransferableFile = contents != null && contents.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            if (hasTransferableFile){
                clipboardContents = (List<File>) contents.getTransferData(DataFlavor.javaFileListFlavor);
            }
        }
        catch (UnsupportedFlavorException e){
            System.err.println("Clipboard data is not a file!");
        }
        catch (IOException e){
            System.err.println("An I/O error occurred when retrieving clipboard data!");
        }
        return clipboardContents;
    }

}
