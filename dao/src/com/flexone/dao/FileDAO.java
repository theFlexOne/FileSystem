package com.flexone.dao;

import com.flexone.bo.FileBO;

import java.util.List;

public interface FileDAO {
    // region GET METHODS
    public FileBO getFileById(int personId);
    public List<FileBO> getFileList();
    // endregion

    // EXECUTE METHODS
    public int insertFile(FileBO person);
    public boolean updateFile(FileBO person);
    public boolean deleteFile(int personId);
    // endregion

}
