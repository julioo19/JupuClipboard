package org.julio.clipboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ClipboardSnapshot {
    private String snapshotText;
    private String snapshotImage;
    private ClipboardType clipboardType;
    private long snapshotTimestamp;

    public ClipboardSnapshot(String snapshotText, String snapshotImage){
        this.snapshotText = snapshotText;
        this.snapshotImage = snapshotImage;
    }


    public String getSnapshotText() {
        return snapshotText;
    }

    public void setSnapshotText(String snapshotText) {
        this.snapshotText = snapshotText;
    }

    public String getSnapshotImage() {
        return snapshotImage;
    }

    public void setSnapshotImage(String snapshotImage) {
        this.snapshotImage = snapshotImage;
    }

    /*
     * TODO:
     *  1. Create a snapshot
     *  2. Serialize the snapshot to a JSON Object
     *  3. Deserialize the JSON Object to readable image or text
     * */
    public String createSnapshot(){
        ClipboardSnapshot clipboardSnapshot = new ClipboardSnapshot(this.snapshotText, this.snapshotImage);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        return gson.toJson(clipboardSnapshot);
    }


}
