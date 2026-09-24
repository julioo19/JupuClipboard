package org.julio;

import java.util.Objects;

public class FileMetadata {
    private String fileName;
    private String fileCanonicalPath;
    private long fileSize;
    private long fileLastModified;

    public FileMetadata(String fileName, String fileCanonicalPath, long fileSize, long fileLastModified) {
        this.fileName = fileName;
        this.fileCanonicalPath = fileCanonicalPath;
        this.fileSize = fileSize;
        this.fileLastModified = fileLastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FileMetadata that = (FileMetadata) o;
        return fileSize == that.fileSize && fileLastModified == that.fileLastModified && Objects.equals(fileName, that.fileName) && Objects.equals(fileCanonicalPath, that.fileCanonicalPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileCanonicalPath, fileSize, fileLastModified);
    }
}
