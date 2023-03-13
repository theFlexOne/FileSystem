package com.flexone.dao;

import com.flexone.bo.Directory;

import java.util.List;

public interface DirectoryDAO {
    // region GET METHODS
    public Directory getDirectoryById(int personId);
    public List<Directory> getDirectoryList();
    // endregion

    // EXECUTE METHODS
    public int insertDirectory(Directory person);
    public boolean updateDirectory(Directory person);
    public boolean deleteDirectory(int personId);
    // endregion

}
