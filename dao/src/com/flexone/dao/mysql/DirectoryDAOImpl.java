package com.flexone.dao.mysql;

import com.flexone.bo.Directory;
import com.flexone.dao.DirectoryDAO;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDAOImpl extends MySQL implements DirectoryDAO {
    @Override
    public Directory getDirectoryById(int id) {
        Directory directory = null;
        connect();
        try {
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, 10);
            cStmt.setInt(2, id);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()) {
                directory = new Directory();
                directory.setId(rs.getInt(1));
                directory.setDirName(rs.getString(2));
                directory.setDirSize(rs.getDouble(3));
                directory.setNumberOfFiles(rs.getInt(4));
                directory.setPath(rs.getString(5));
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return directory;
    }

    @Override
    public List<Directory> getDirectoryList() {
        connect();
        List<Directory> directoryList = new ArrayList<>();
        try {
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, 20);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()) {
                Directory directory = new Directory();
                directory.setId(rs.getInt(1));
                directory.setDirName(rs.getString(2));
                directory.setDirSize(rs.getDouble(3));
                directory.setNumberOfFiles(rs.getInt(4));
                directory.setPath(rs.getString(5));

                directoryList.add(directory);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return directoryList;
    }

    @Override
    public int insertDirectory(Directory directory) {
        connect();
        int id = 0;

        try {
            String sp = "{CALL ExecDirectory(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, Procedures.INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3, directory.getDirName());
            cStmt.setDouble(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getPath());

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
    public boolean updateDirectory(Directory directory) {
        connect();
        int id = 0;

        try {
            String sp = "{CALL ExecDirectory(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, Procedures.UPDATE);
            cStmt.setInt(2, directory.getId());
            cStmt.setString(3, directory.getDirName());
            cStmt.setDouble(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getPath());

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
    public boolean deleteDirectory(int directoryId) {
        connect();
        int id = 0;

        try {
            String sp = "{CALL ExecDirectory(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, Procedures.DELETE);
            cStmt.setInt(2, directoryId);
            cStmt.setString(3, "");
            cStmt.setDouble(4, 0.0);
            cStmt.setInt(5, 0);
            cStmt.setString(6, "");

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
    public Directory getDirWithMostFiles() {
        Directory directory = null;
        connect();
        try {
            String sp = "{call GetDirWithMostFiles()}";
            CallableStatement cStmt = connection.prepareCall(sp);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()) {
                directory = new Directory();
                directory.setId(rs.getInt(1));
                directory.setDirName(rs.getString(2));
                directory.setDirSize(rs.getDouble(3));
                directory.setNumberOfFiles(rs.getInt(4));
                directory.setPath(rs.getString(5));
            }

        } catch (SQLException ex) {
            logger.error(ex);
        }
        return directory;
    }

    @Override
    public Directory getDirWithLargestSize() {
        Directory directory = null;
        connect();
        try {
            String sp = "{call GetDirWithLargestSize()}";
            CallableStatement cStmt = connection.prepareCall(sp);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()) {
                directory = new Directory();
                directory.setId(rs.getInt(1));
                directory.setDirName(rs.getString(2));
                directory.setDirSize(rs.getDouble(3));
                directory.setNumberOfFiles(rs.getInt(4));
                directory.setPath(rs.getString(5));
            }

        } catch (SQLException ex) {
            logger.error(ex);
        }
        return directory;
    }
}
