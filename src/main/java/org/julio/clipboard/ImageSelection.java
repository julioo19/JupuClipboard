package org.julio.clipboard;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ImageSelection implements Transferable, ClipboardOwner {
    private Image image;
    private static final int IMAGE = 0;

    private static final DataFlavor[] flavors= {
            DataFlavor.imageFlavor
    };

    public ImageSelection(Image image){
        this.image = image;
    }
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors.clone();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for (int i = 0; i < flavors.length; i++){
            if (flavor.equals(flavors[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(flavors[IMAGE])){
            return image;
        }
        else{
            throw new UnsupportedFlavorException(flavor);
        }
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
}
