package org.julio.clipboard;

public class ClipboardSnapshot {
    private int snapshotId;
    ClipboardType clipboardType;
    private long snapshotTimestamp;
    private long payloadLength;
    private byte[] payload;

    public ClipboardSnapshot(ClipboardType cType, byte[] payload){
        this.clipboardType = cType;
        this.payload = payload;
    }


}
