package com.flexone.bo;

import java.util.Arrays;
import java.util.List;

public class Directory extends BaseBO {

    // region FIELDS
    private String dirName;
    private double dirSize;
    private int numberOfFiles;
    private String path;
    private Directory parent;
    // endregion

    // region CONSTRUCTORS
    public Directory() {
    }
    // endregion

    // region GETTERS & SETTERS
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

    public Directory getParent() {
        return parent;
    }

    public Directory setParent(Directory parent) {
        this.parent = parent;
        return this;
    }
    // endregion

    // region METHODS
    public String toString() {
        return path;
    }
    // endregion
}
