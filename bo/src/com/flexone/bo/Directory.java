package com.flexone.bo;

public class Directory extends BaseBO {
    String dirName;
    double dirSize;
    int numberOfFiles;
    String path;

    public String getDirName() {
        return dirName;
    }

    public Directory setDirName(String dirName) {
        this.dirName = dirName;
        return this;
    }

    public double getDirSize() {
        return dirSize;
    }

    public Directory setDirSize(double dirSize) {
        this.dirSize = dirSize;
        return this;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public Directory setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Directory setPath(String path) {
        this.path = path;
        return this;
    }

    public String toString() {
        return path;
    }
}
