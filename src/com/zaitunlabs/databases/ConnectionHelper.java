/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.databases;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ahmad
 */
public class ConnectionHelper {
    public static final String DATABASE_NAME = "jualanjavarc";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String URL = "jdbc:mysql://192.168.64.2:3306/" + DATABASE_NAME;
    
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
        return connection;
    }
    
    
}
