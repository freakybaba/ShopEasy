
package flipzonserver;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class newClient extends Thread {
    Socket s;
    String ThreadOwner;
    String que = "select * from ";
    static int c = 0;
    FlipZonServer fzs;
    DBconn dbc;
    ServerSocket ss;
    DataInputStream dis;
    DataOutputStream dos;
    Connection conn;
     PreparedStatement st;
   
    public static final int BUFFER_SIZE = 1024 * 50;
     private byte[] buffer;
    newClient(Socket s,FlipZonServer fzs,ServerSocket ss,String user) throws SQLException, IOException
    {
        this.ThreadOwner = user;
        this.fzs =fzs;
        this.s = s;
        this.ss = ss;
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
         dbc= new DBconn();
         conn = dbc.makeConn();
         int imageNo = 0;
    }
  
    @Override
    public void run()
    {
       
         String type;
         //String k = "mobdb";
         String cat[] = {"mobdb","fooddb","otherdb","elecdb","fashdb"};
       while(true)
       {
           
           try {
               System.out.println("waiting to read query");
                type = dis.readUTF();
                System.out.println(type);
                  if(type.equals("home"))
                  {
                      System.out.println("home query requested");
                      for(int i = 0;i<5;i++)
                      {
                           String tmpQ = que + cat[i]+" order by id desc;";
                           st = conn.prepareStatement(tmpQ);
                           ResultSet rs=null;
                            try{
                            rs= st.executeQuery();
                            }catch(Exception e){
                             System.out.println("error in query execution 'home' type");
                            }
                            rs.last();
                            int r = rs.getRow();
                            rs= st.executeQuery();
                            Integer item = Math.min(r,2);
                            dos.writeUTF(item.toString());
                            for(int j = 0;j<item;j++)
                            {
                                rs.next();
                                String ipath = rs.getString(9);
                                
                                dos.writeUTF(rs.getString(2));dos.writeUTF(rs.getString(3));
                                dos.writeUTF(rs.getString(4));dos.writeUTF(rs.getString(5));
                                dos.writeUTF(rs.getString(6));dos.writeUTF(rs.getString(7));
                                dos.writeUTF(rs.getString(8));dos.writeUTF(rs.getString(10));
                                this.sentImage(ipath);

                            }
                           
                       
                       }
               }//end of home
                  else if(type.equals("mobdb"))
                  {
                       System.out.println("mobedb query requested");
                      String tmpQ = que + cat[0];
                       st = conn.prepareStatement(tmpQ);
                       ResultSet rs= st.executeQuery();
                       rs.last();
                        Integer r = rs.getRow();
                       dos.writeUTF(r.toString());
                       rs= st.executeQuery();
                       for(int j = 0;j<r;j++)
                       {
                           rs.next();
                           String ipath = rs.getString(9);
                           dos.writeUTF(rs.getString(2));dos.writeUTF(rs.getString(3));
                           dos.writeUTF(rs.getString(4));dos.writeUTF(rs.getString(5));
                           dos.writeUTF(rs.getString(6));dos.writeUTF(rs.getString(7));
                           dos.writeUTF(rs.getString(8));dos.writeUTF(rs.getString(10));
                          this.sentImage(ipath);
                           
                       }
                  }//end of mob db
                  else if(type.equals("fooddb"))
                  {
                       System.out.println("food query requested");
                        String tmpQ = que + cat[1];
                       st = conn.prepareStatement(tmpQ);
                       ResultSet rs= st.executeQuery();
                       rs.last();
                       Integer r = rs.getRow();
                       dos.writeUTF(r.toString());
                       rs= st.executeQuery();
                       for(int j = 0;j<r;j++)
                       {
                           rs.next();
                           String ipath = rs.getString(9);
                           dos.writeUTF(rs.getString(2));dos.writeUTF(rs.getString(3));
                           dos.writeUTF(rs.getString(4));dos.writeUTF(rs.getString(5));
                           dos.writeUTF(rs.getString(6));dos.writeUTF(rs.getString(7));
                           dos.writeUTF(rs.getString(8));dos.writeUTF(rs.getString(10));
                          this.sentImage(ipath);
                           
                       }
                  }//end of fooddb
                  else if(type.equals("otherdb"))
                  {
                       System.out.println("others query requested");
                      String tmpQ = que + cat[2];
                       st = conn.prepareStatement(tmpQ);
                       ResultSet rs= st.executeQuery();
                       rs.last();
                        Integer r = rs.getRow();
                       dos.writeUTF(r.toString());
                       rs= st.executeQuery();
                       for(int j = 0;j<r;j++)
                       {
                           rs.next();
                           String ipath = rs.getString(9);
                           dos.writeUTF(rs.getString(2));dos.writeUTF(rs.getString(3));
                           dos.writeUTF(rs.getString(4));dos.writeUTF(rs.getString(5));
                           dos.writeUTF(rs.getString(6));dos.writeUTF(rs.getString(7));
                           dos.writeUTF(rs.getString(8));dos.writeUTF(rs.getString(10));
                          this.sentImage(ipath);
                           
                       }
                  }//end of otherdb
                  else if(type.equals("elecdb"))
                  {
                       System.out.println("elec query requested");
                       String tmpQ = que + cat[3];
                       st = conn.prepareStatement(tmpQ);
                       ResultSet rs= st.executeQuery();
                       rs.last();
                       Integer r = rs.getRow();
                       dos.writeUTF(r.toString());
                       rs= st.executeQuery();
                       for(int j = 0;j<r;j++)
                       {
                           rs.next();
                           String ipath=rs.getString(9);
                           dos.writeUTF(rs.getString(2));dos.writeUTF(rs.getString(3));
                           dos.writeUTF(rs.getString(4));dos.writeUTF(rs.getString(5));
                           dos.writeUTF(rs.getString(6));dos.writeUTF(rs.getString(7));
                           dos.writeUTF(rs.getString(8));dos.writeUTF(rs.getString(10));
                          this.sentImage(ipath);
                           
                       }
                  }//end of elecdb
                  else if(type.equals("fashdb"))
                  {
                       System.out.println("fashion query requested");
                       String tmpQ = que + cat[4];
                        st = conn.prepareStatement(tmpQ);
                       ResultSet rs= st.executeQuery();
                       rs.last();
                        Integer r = rs.getRow();
                       dos.writeUTF(r.toString());
                       rs= st.executeQuery();
                       for(int j = 0;j<r;j++)
                       {
                           rs.next();
                           String ipath = rs.getString(9);
                           dos.writeUTF(rs.getString(2));dos.writeUTF(rs.getString(3));
                           dos.writeUTF(rs.getString(4));dos.writeUTF(rs.getString(5));
                           dos.writeUTF(rs.getString(6));dos.writeUTF(rs.getString(7));
                           dos.writeUTF(rs.getString(8));dos.writeUTF(rs.getString(10));
                          this.sentImage(ipath);
                           
                       }
                  }//end of fash db
                  else if(type.equals("additem"))
                  {
                      
                      System.out.println("additem requested");
                      String values[] = new String[7];
                      try{
                      for(int i = 0;i<7;i++)
                      {
                          values[i]=dis.readUTF();
                      }}
                      catch(Exception e){
                          System.out.println("error in reading");
                      }
                      String insertUser = "INSERT INTO `"+this.ThreadOwner+"@addedr`(`categ`, `brand`, `price`, "
                                + "`stock`, `offerX`, `seller`, `extra`, `ipath`,`fzid`) VALUES (?,?,?,?,?,?,?,?,?)";
                       String path="C:\\Users\\hp\\Documents\\NetBeansProjects\\FlipZonServer\\Images\\";
                       path = path+values[6];
                        System.out.println("path "+path);
                       String insertCat ="INSERT INTO `"+values[0]+"`(`categ`, `brand`, `price`, "
                                + "`stock`, `offerX`, `seller`, `extra`, `ipath`,`fzid`) VALUES (?,?,?,?,?,?,?,?,?)";
                      String fzQ = "SELECT `prodnum` FROM `users` WHERE `username`= ?" ;
                       st = conn.prepareStatement(fzQ);
                       st.setString(1,this.ThreadOwner);
                       ResultSet tmp = st.executeQuery();
                       tmp.next();
                       int currfz = tmp.getInt(1);
                       currfz++;
                       getImage(path);                                                  //updating user table with product
                       st = conn.prepareStatement(insertUser);
                       st.setString(1, values[0]);
                       st.setString(2, values[1]);
                       st.setString(3, values[2]);
                       st.setString(4, values[3]);
                       st.setString(5, values[4]);
                       st.setString(6, this.ThreadOwner);
                       st.setString(7, values[5]);
                       st.setString(8, path);
                       st.setInt(9,currfz);
                             
                        try{
                        st.execute();}
                        catch(Exception e){
                            System.out.println("error in additem type");
                        }
                                                                                        //updating category table
                       st = conn.prepareStatement(insertCat);
                       st.setString(1, values[0]);
                       st.setString(2, values[1]);
                       st.setString(3, values[2]);
                       st.setString(4, values[3]);
                       st.setString(5, values[4]);
                       st.setString(6, this.ThreadOwner);
                       st.setString(7, values[5]);
                       st.setString(8, path);
                       st.setInt(9,currfz);
                              
                        try{
                        st.execute();}
                        catch(Exception e){
                            System.out.println("error in additem type");
                        }
                                                                                     //increasing product number of user
                        String updatefz = "UPDATE `users` SET `prodnum`=? WHERE username= ?";
                        st = conn.prepareStatement(updatefz);
                         st.setInt(1,currfz);
                        st.setString(2,this.ThreadOwner);
                       
                        st.execute();
                      
                  }
           } catch (Exception ex) {
               System.out.println("Client closed the connection or Exception in run "+s+"");
               break;
           }
           
       }
    }
     public void sentImage(String path) throws IOException {
         try{
             Socket s = ss.accept();
            buffer = new byte[BUFFER_SIZE];
               BufferedInputStream in = 
                    new BufferedInputStream(
                         new FileInputStream(path));

               BufferedOutputStream out = 
                    new BufferedOutputStream(s.getOutputStream());


               int len = 0;
               while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                   // System.out.print("#");
               }
               in.close();
               out.flush();
               out.close();
               s.close();

              // System.out.println("\nImage sent!");
         }catch(Exception e)
         {
             System.out.println("exception in sending image");
         }
         
//         InputStream inputStream = null;
//         String dir = path;
//       OutputStream outputStream = s.getOutputStream();
//    BufferedImage image = ImageIO.read(new File(dir));
//    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//    ImageIO.write(image, "jpg", byteArrayOutputStream);
//    byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//    outputStream.write(size);
//    outputStream.write(byteArrayOutputStream.toByteArray());

     }
     public void getImage(String k1) throws FileNotFoundException, IOException
     {
        try{
         Socket socket = ss.accept();
          BufferedInputStream in = 
               new BufferedInputStream(socket.getInputStream());

          BufferedOutputStream out = 
               new BufferedOutputStream(new FileOutputStream(k1));
               
          int len = 0;
          while ((len = in.read(buffer)) > 0) {
               out.write(buffer, 0, len);
               System.out.print("#");
          }
          in.close();
          out.flush();
          out.close();
          socket.close();
          System.out.println("\nimage received");
        }
        catch(Exception e)
        {
            System.out.println("error in image");
        }
     }
    
    
}
