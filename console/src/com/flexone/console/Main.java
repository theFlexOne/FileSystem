package com.flexone.console;

import com.flexone.bo.*;
import com.flexone.dao.DirectoryDAO;
import com.flexone.dao.mysql.DirectoryDAOImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {

//    public static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {

        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        List<Directory> directoryList = directoryDAO.getDirectoryList();

        System.out.println(directoryList.size());

        for (Directory dir : directoryList) {
            System.out.println(dir.toString());
        }
//
//        Directory directory = new Directory();
//        directory.setPath("/home/").setDirName("home/");
//        FileBO file = new FileBO();
//        file.setPath("/home/index.html").setFileName("index.html");
//        file.setFileDir(directory);
//
//        System.out.println(file.toString());

    }
}