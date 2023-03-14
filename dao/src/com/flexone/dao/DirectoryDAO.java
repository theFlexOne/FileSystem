package com.flexone.dao;

import com.flexone.bo.Directory;

import java.util.List;

public interface DirectoryDAO {
    // region GET METHODS
    public Directory getDirectoryById(int directoryId);
    public List<Directory> getDirectoryList();
    // endregion

    // region EXECUTE METHODS
    public int insertDirectory(Directory directory);
    public boolean updateDirectory(Directory directory);
    public boolean deleteDirectory(int directoryId);
    // endregion

    // region CUSTOM GET METHODS
    public Directory getDirWithMostFiles();
    public Directory getDirWithLargestSize();
    // endregion

}
