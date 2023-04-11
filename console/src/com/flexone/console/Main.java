package com.flexone.console;

import com.flexone.bo.*;
import com.flexone.dao.DirectoryDAO;
import com.flexone.dao.FileDAO;
import com.flexone.dao.mysql.DirectoryDAOImpl;
import com.flexone.dao.mysql.FileDAOImpl;
import com.flexone.dao.mysql.MySQL;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean isOpen = false;

    final static Logger logger = Logger.getLogger(Main.class);
    final static Scanner scanner = new Scanner(System.in);

    final static DirectoryDAO directoryDAO = new DirectoryDAOImpl();
    final static FileDAO fileDAO = new FileDAOImpl();

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        Main.isOpen = true;


        // Prompt user for a starting directory path
        System.out.print("Please choose a directory path to start from: ");
        String path = new File(scanner.nextLine()).getPath();

        // Seed DB with directories and files in path
        System.out.println("Loading '" + path + "' into the DB");
        Directory rootDir = seedFileSystem(path, 0);

        while (Main.isOpen) {
            // Display menu and receive user's option
            System.out.println();
            System.out.println("Current directory: " + path);
            System.out.println("======================================================");
            System.out.println("Please pick from one of the following options:");
            System.out.println("    1  -  Display directory with most files");
            System.out.println("    2  -  Display directory largest in size");
            System.out.println("    3  -  Display 5 largest files in size");
            System.out.println("    4  -  Display all files of a certain type");
            System.out.println("    5  -  Clear the db and start over");
            System.out.println("    6  -  Exit");
            System.out.println("======================================================");
            System.out.print("Choose an option: ");

            // Prompt user for an option
            String option = scanner.nextLine();

            if (option.equals("6")) {
                System.out.println();
                System.out.println("Goodbye");
                MySQL.clearDB();
                return;
            }

            // Execute action based on option entered
            executeOption(option);

            System.out.println();
            System.out.print("Would you like to continue? (y/n) ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("n")) {
                System.out.println();
                System.out.println("Goodbye");
                MySQL.clearDB();
                return;
            }
        }
    }

    private static Directory seedFileSystem(String path, int parentId) {
        File rootDir = new File(path);
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        FileDAO fileDAO = new FileDAOImpl();

        Directory dir = new Directory();
        dir.setDirName(rootDir.getName());
        dir.setPath(path);
        if (parentId > 0) {
            dir.setParent(directoryDAO.getDirectoryById(parentId));
        }

        int dirId = directoryDAO.insertDirectory(dir);
        dir.setId(dirId);

        File[] filesInDir = rootDir.listFiles();

        assert filesInDir != null;
        for (File f : filesInDir) {
            if (f.isDirectory()) {
                seedFileSystem(f.getPath(), dirId);
            } else {
                FileBO file = new FileBO();
                file.setFileDir(dir);
                file.setFileName(f.getName());
                String fileType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1);
                if (fileType.equals(file.getFileName())) fileType = "File";
                file.setFileType(fileType);
                file.setPath(f.getPath());
                file.setFileSize(f.length());

                fileDAO.insertFile(file);
            }
        }
        return dir;
    }

    private static void executeOption(String option) {
        switch (option) {
            // Display directory with most files
            case "1" -> {
                Directory dir = directoryDAO.getDirWithMostFiles();
                System.out.println();
                System.out.println("======================================================");
                System.out.println("Directory Name: " + dir.getDirName());
                System.out.println("Number of Files: " + dir.getNumberOfFiles());
                System.out.println("Path: " + dir.getPath());
                System.out.println("======================================================");
                return;
            }

            // Display directory largest in size
            case "2" -> {
                Directory dir = directoryDAO.getDirWithLargestSize();
                System.out.println();
                System.out.println("======================================================");
                System.out.println("Directory Name: " + dir.getDirName());
                System.out.println("Directory Size (MB): " + dir.getDirSize() / 1000000);
                System.out.println("Path: " + dir.getPath());
                System.out.println("======================================================");
                return;
            }

            // Display 5 largest files in size
            case "3" -> {
                List<FileBO> files = fileDAO.getLargestFiles(5);
                System.out.println();
                System.out.println("======================================================");
                System.out.println("The 5 largest files, in order, are:");
                System.out.println("    1) File Name: " + files.get(0).getFileName() + " | Size (MB): " + (double) files.get(0).getFileSize() / 1000000);
                System.out.println("    2) File Name: " + files.get(1).getFileName() + " | Size (MB): " + (double) files.get(1).getFileSize() / 1000000);
                System.out.println("    3) File Name: " + files.get(2).getFileName() + " | Size (MB): " + (double) files.get(2).getFileSize() / 1000000);
                System.out.println("    4) File Name: " + files.get(3).getFileName() + " | Size (MB): " + (double) files.get(3).getFileSize() / 1000000);
                System.out.println("    5) File Name: " + files.get(4).getFileName() + " | Size (MB): " + (double) files.get(4).getFileSize() / 1000000);
                System.out.println("======================================================");
                return;
            }

            // Display all files of a certain type
            case "4" -> {
                System.out.print("Please enter a file type: ");
                String fileType = scanner.nextLine();

                List<FileBO> files = fileDAO.getFilesOfType(fileType);

                System.out.println();
                System.out.println("======================================================");
                System.out.println("The following are all the files with a type of '" + fileType + "':");
                for (int i = 0; i < files.size(); i++) {
                    System.out.println("    " + (i + 1) + ") File Name: " + files.get(i).getFileName() + " | Path: " + files.get(i).getPath());
                }
                System.out.println("======================================================");
                return;
            }

            // Clear the DB and start over
            case "5" -> {
                System.out.println();
                System.out.println("Starting over");
                MySQL.clearDB();
                init();
                return;

            }
            default -> System.out.println("WTF?");
        }
    }
}