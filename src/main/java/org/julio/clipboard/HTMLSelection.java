package org.julio.clipboard;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class HTMLSelection implements Transferable {

    private String plainText;
    private String htmlText;

    public HTMLSelection(String plainText, String htmlText){
        this.plainText = plainText;
        this.htmlText = htmlText;
    }

    private static final DataFlavor[] flavors = {
        DataFlavor.allHtmlFlavor, DataFlavor.stringFlavor
    };

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
        String exportedText;
        if (flavor == DataFlavor.stringFlavor){
            exportedText = plainText;
        }
        else if (flavor == DataFlavor.allHtmlFlavor){
            exportedText = htmlText;
        }
        else{
            throw new UnsupportedFlavorException(flavor);
        }
        return exportedText;
    }
}
