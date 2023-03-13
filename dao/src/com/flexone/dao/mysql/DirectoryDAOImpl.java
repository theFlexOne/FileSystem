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
        connect();
        Directory directory = null;
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
    public int insertDirectory(Directory person) {
        return 0;
    }

    @Override
    public boolean updateDirectory(Directory person) {
        return false;
    }

    @Override
    public boolean deleteDirectory(int personId) {
        return false;
    }
}
