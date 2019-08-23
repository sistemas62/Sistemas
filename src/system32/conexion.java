/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system32;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pc-29
 */
public class conexion {
    public static String dbUrl = "jdbc:postgresql://172.16.0.20:5432/bpaleta";
    public static String user= "post";
    public static String pass = "123456";
    
    public void te() throws SQLException{
        Connection conn = DriverManager.getConnection(dbUrl,pass,user);
    }
    
}
