/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DBConnection {

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        // initialize all the information regarding database connection
        String dbDriver = "com.mysql.jdbc.Driver";
        
        // non docker env
        //String dbURL = "jdbc:mysql://192.168.56.119:3306/";           // using ip
        //String dbURL = "jdbc:mysql://docker-learning:3306/";            // using hostname
        //String dbUser = "dbadmin2";             // non docker env
        //String dbUserPass = "P@ssw0rd1234";     // non docker env
        
        // docker env
        //String dbURL = "jdbc:mysql://appsdb:3306/";
        //String dbUser = "root";                
        //String dbUserPass = "P@ssw0rd1234";
        
        // MySQL
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "bookland";
        String dbUser = "root";
        String dbUserPass = "";
        
        Class.forName(dbDriver);
        Connection dbConn = DriverManager.getConnection(dbURL + dbName + "?serverTimezone=UTC", dbUser, dbUserPass);
        System.out.println("connected to mybookstore database");
        return dbConn;
        
    }
}
