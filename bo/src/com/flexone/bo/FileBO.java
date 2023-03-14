package com.flexone.bo;


import java.util.List;

public class FileBO extends BaseBO {
    // region FIELDS
    private String fileName;
    private String fileType;
    private long fileSize;
    private String path;
    private Directory fileDir;
    // endregion

    // region CONSTRUCTORS
    public FileBO() {
        this.fileDir = new Directory();
    }
    // endregion

    // region GETTERS & SETTERS
    public String getFileName() {
        return fileName;
    }

    public FileBO setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public FileBO setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public FileBO setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getPath() {
        return path;
    }

    public FileBO setPath(String path) {
        this.path = path;
        return this;
    }

    public Directory getFileDir() {
        return fileDir;
    }

    public FileBO setFileDir(Directory fileDir) {
        this.fileDir = fileDir;
        return this;
    }
    // endregion

    // region METHODS
    public String toString() {
        return path;
    }
    // endregion
}
