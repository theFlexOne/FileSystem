package com.flexone.dao.mysql;

import com.flexone.bo.Directory;
import com.flexone.bo.FileBO;
import com.flexone.dao.DirectoryDAO;
import com.flexone.dao.FileDAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDAOImpl extends MySQL implements FileDAO {
    @Override
    public FileBO getFileById(int fileId) {
        FileBO file = null;
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();

        connect();

        try {
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, 10);
            cStmt.setInt(2, fileId);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()) {
                file = new FileBO();
                file.setId(rs.getInt(1));
                file.setFileName(rs.getString(2));
                file.setFileType(rs.getString(3));
                file.setFileSize(rs.getLong(4));
                file.setPath(rs.getString(5));
                file.setFileDir(directoryDAO.getDirectoryById(rs.getInt(6)));
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return file;

    }

    @Override
    public List<FileBO> getFileList() {
        connect();
        List<FileBO> fileList = new ArrayList<>();
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        try {
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, 20);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()) {
                FileBO file = new FileBO();
                file = new FileBO();
                file.setId(rs.getInt(1));
                file.setFileName(rs.getString(2));
                file.setFileType(rs.getString(3));
                file.setFileSize(rs.getLong(4));
                file.setPath(rs.getString(5));
                file.setFileDir(directoryDAO.getDirectoryById(rs.getInt(6)));

                fileList.add(file);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return fileList;
    }

    @Override
    public int insertFile(FileBO file) {
        connect();
        int id = 0;

        try {
            String sp = "{CALL ExecFile(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, Procedures.INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3, file.getFileName());
            cStmt.setString(4, file.getFileType());
            cStmt.setDouble(5, file.getFileSize());
            cStmt.setString(6, file.getPath());
            cStmt.setInt(7, file.getFileDir().getId());

            ResultSet rs = cStmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return id;
    }

    @Override
    public boolean updateFile(FileBO file) {
        connect();
        int id = 0;

        try {
            String sp = "{CALL ExecFile(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, Procedures.UPDATE);
            cStmt.setInt(2, file.getId());
            cStmt.setString(3, file.getFileName());
            cStmt.setString(4, file.getFileType());
            cStmt.setDouble(5, file.getFileSize());
            cStmt.setString(6, file.getPath());
            cStmt.setInt(7, file.getFileDir().getId());

            ResultSet rs = cStmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return id > 0;
    }

    @Override
    public boolean deleteFile(int fileId) {
        connect();
        int id = 0;

        try {
            String sp = "{CALL ExecFile(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, Procedures.DELETE);
            cStmt.setInt(2, fileId);
            cStmt.setString(3, "");
            cStmt.setString(4, "");
            cStmt.setDouble(5, 0.0);
            cStmt.setString(6, "");
            cStmt.setInt(7, 0);

            ResultSet rs = cStmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return id > 0;
    }

    @Override
    public List<FileBO> getLargestFiles(int count) {
        List<FileBO> fileList = new ArrayList<>();
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();

        connect();

        try {
            String sp = "{call GetLargestFiles(?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, count);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()) {
                FileBO file = new FileBO();
                file.setId(rs.getInt(1));
                file.setFileName(rs.getString(2));
                file.setFileType(rs.getString(3));
                file.setFileSize(rs.getLong(4));
                file.setPath(rs.getString(5));
                file.setFileDir(directoryDAO.getDirectoryById(rs.getInt(6)));

                fileList.add(file);

            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return fileList;

    }

    @Override
    public List<FileBO> getFilesOfType(String type) {
        List<FileBO> fileList = new ArrayList<>();
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();

        connect();

        try {
            String sp = "{call GetFilesOfType(?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setString(1, type);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()) {
                FileBO file = new FileBO();
                file.setId(rs.getInt(1));
                file.setFileName(rs.getString(2));
                file.setFileType(rs.getString(3));
                file.setFileSize(rs.getLong(4));
                file.setPath(rs.getString(5));
                file.setFileDir(directoryDAO.getDirectoryById(rs.getInt(6)));

                fileList.add(file);

            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return fileList;

    }
}
