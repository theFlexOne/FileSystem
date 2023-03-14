package com.flexone.dao;

import com.flexone.bo.FileBO;

import java.util.List;

public interface FileDAO {
    // region GET METHODS
    public FileBO getFileById(int fileId);
    public List<FileBO> getFileList();
    // endregion

    // region EXECUTE METHODS
    public int insertFile(FileBO file);
    public boolean updateFile(FileBO file);
    public boolean deleteFile(int fileId);
    // endregion

    // region CUSTOM METHODS

    public List<FileBO> getLargestFiles(int count);
    public List<FileBO> getFilesOfType(String type);

    // endregion
}
