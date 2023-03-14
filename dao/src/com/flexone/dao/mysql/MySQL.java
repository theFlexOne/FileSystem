package com.flexone.dao.mysql;

import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQL {
    protected static String dbHost = "localhost";
    protected static String dbName = "file_system";
    protected static String dbUser = "consoleUser";
    protected static String dbPass = "C7791#sql";
    protected static String useSSL = "false";
    protected static String procBod = "true";

    protected static Connection connection = null;

    final static Logger logger = Logger.getLogger(MySQL.class);

    protected static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("MySQL Driver not found! " + e);
        }
        logger.info("MySQL Driver Registered");


        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPass);
        } catch (SQLException e) {
            logger.error("Connection failed " + e);
        }
        if (connection != null) {
            logger.info("Successfully connected to MySQL database");
        } else {
            logger.info("Connection failed!");
        }

    }

    public static void clearDB() {
        connect();

        try {
            String sp = "{call ClearDB()}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.executeQuery();

        } catch (SQLException ex) {
            logger.error(ex);
        }
    }
}
