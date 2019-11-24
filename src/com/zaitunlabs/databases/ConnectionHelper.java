/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.databases;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ahmad
 */
public class ConnectionHelper {
    private static final String DATABASE_NAME = "jualanjavarc";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://192.168.64.2:3306/" + DATABASE_NAME;
    
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
        return connection;
    }
}
