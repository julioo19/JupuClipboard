package org.julio.clipboard;

import java.awt.datatransfer.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSelection implements Transferable, ClipboardOwner {

    private List<File> listofFiles;
    private final static int FILE = 0;

    private final static DataFlavor[] flavors = {
      DataFlavor.javaFileListFlavor
    };

    public FileSelection(List<File> listofFiles){
        this.listofFiles = listofFiles;
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
        if (flavor.equals(flavors[FILE])){
            return listofFiles;
        }
        else{
            throw new UnsupportedFlavorException(flavor);
        }
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
}
