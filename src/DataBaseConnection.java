
package flipzonserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    Connection makeConn() throws SQLException
    {
         String url = "jdbc:mysql://localhost:3307/flipzondb?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pass = "";
       
         Connection conn = DriverManager.getConnection(url,user,pass);
         return conn;
         
        
    }
    
}
