
package flipzonserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ck {
    
    
    PreparedStatement st;
    Connection conn;
    private void checking() throws SQLException
    {
        conn = new DBconn().makeConn();
        System.out.println(conn);
      String k = "amit";
       String fzQ = "SELECT `prodnum` FROM `users` WHERE `username`= ?" ;
                       st = conn.prepareStatement(fzQ);
                       st.setString(1,k);
          
                       ResultSet tmp = st.executeQuery();
                             
                            
                      
        
    }
    public static void main(String[] args) throws SQLException {
       new ck().checking();
    }
    
}
