
package flipzonserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FlipZonServer {

    private ServerSocket serverSocket1,serverSocket2 ;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket socket;
     static PreparedStatement preparedStatement;
     static Connection dataBaseConnection;
    static ResultSet resultSet;
     static String username,email,password,type,encryptedPassword;
    public synchronized  ArrayList<Integer> buyNow(String cartOwner,String type,DataInputStream dataInputStream,DataOutputStream dataOutputStream) throws Exception
    {
        System.out.println("entered in synchronized method");
         preparedStatement = dataBaseConnection.prepareStatement(Queries.selectCartQuery);
         preparedStatement.setString(1,cartOwner);
         resultSet = preparedStatement.executeQuery();
         ArrayList <Integer> stockArray = new ArrayList<>();
         ArrayList <String> fzidArray = new ArrayList<>();
         ArrayList <String> sellerArray = new ArrayList<>();
         try{
         while(resultSet.next())
         {
             String productId = resultSet.getString(10);
             String sellerOfProduct = resultSet.getString(7);
             Integer stockOfItem = resultSet.getInt(5);
             
             sellerArray.add(sellerOfProduct);
             fzidArray.add(productId);
             
             
             preparedStatement = dataBaseConnection.prepareStatement(Queries.getProductStockQuery);
             preparedStatement.setString(1,sellerOfProduct);
             preparedStatement.setString(2,productId);
              ResultSet resultSet1=null;
          
             resultSet1= preparedStatement.executeQuery();
            
         
             resultSet1.last();
             int row = resultSet1.getRow();
             resultSet1 = preparedStatement.executeQuery();
             
     
             if(row==0)
             {
                 
                stockArray.add(-1);
                continue;
             }
             
             resultSet1.next();
             Integer availableStock = resultSet1.getInt(1);
            
                 stockArray.add(availableStock-stockOfItem);
            }
         }
             
         catch(Exception e)
         {
             System.out.println(e);
         }
        for(int i = 0;i<stockArray.size();i++)
        {
            if(stockArray.get(i)<0)
            {   System.out.println("item not sold");
                return stockArray;
            }
        }
          DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
       String buyingDate = dateformat.format(date);
        
          preparedStatement = dataBaseConnection.prepareStatement(Queries.moveCartToHistoryQuery);
          preparedStatement.setString(1,buyingDate);
          preparedStatement.setString(2,cartOwner);
          preparedStatement.execute();
        System.out.println("updation-------------------------");
        for(int i = 0;i<stockArray.size();i++)
        {
            if(stockArray.get(i).equals(0))
            {
              preparedStatement = dataBaseConnection.prepareStatement(Queries.deleteFromProductQuery);
             preparedStatement.setString(1,sellerArray.get(i));
             preparedStatement.setString(2,fzidArray.get(i));
             preparedStatement.execute();
            }
            else
            {
             preparedStatement = dataBaseConnection.prepareStatement(Queries.updateProductStockQuery);
             preparedStatement.setInt(1,stockArray.get(i));
             preparedStatement.setString(2,fzidArray.get(i));
             preparedStatement.setString(3,sellerArray.get(i));
             preparedStatement.execute();
            }
 
        }
        
     //code to empty the cart of corresponding cartOwner   
        preparedStatement = dataBaseConnection.prepareStatement(Queries.emptyCartQuery);
        preparedStatement.setString(1,cartOwner);
        preparedStatement.execute();
        System.out.println("ended successfully");
        return stockArray;
        
    }
    public FlipZonServer() throws Exception
    {
        dataBaseConnection = new DataBaseConnection().makeConn();
        SharedMethods.dataBaseConnection = this.dataBaseConnection;
        serverSocket1= new ServerSocket(1234);
        serverSocket2 = new ServerSocket(4321);
        while(true)
        {   System.out.println("waiting for connection");
            socket =  serverSocket1.accept();
            System.out.println("Client Connected");
            dataInputStream = new DataInputStream( socket.getInputStream());
            dataOutputStream = new DataOutputStream( socket.getOutputStream());
            type =dataInputStream.readUTF();
            System.out.println(type);
            if(type.equals("signup"))                                                 //Signup case
            {
                
                username = dataInputStream.readUTF();
                password = dataInputStream.readUTF();
                email = dataInputStream.readUTF();
                preparedStatement = dataBaseConnection.prepareStatement(Queries.signupQuery);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,email);
                try{
                resultSet=preparedStatement.executeQuery();}
                catch(Exception e){
                System.out.println("error in signup query");
                }
                int rows = 0;
                while (resultSet.next())
                        ++rows;

                    if (rows == 0) {
                        preparedStatement = dataBaseConnection.prepareStatement(Queries.updateUserTableQuery);
                        try{
                        
                            encryptedPassword =new  HashPassword().hash(password);
                        }catch(Exception e)
                        {
                            System.out.println("error in password hashing");
                        }
                        preparedStatement.setString(1,username);
                        preparedStatement.setString(2,encryptedPassword);
                        preparedStatement.setString(3,email);
                        preparedStatement.execute();
                        System.out.println("data updated of user "+ username + " in table and connection established");
                        dataOutputStream.writeUTF("ok");
                        System.out.println("new Client connected");
                        try{
                        ConnectedClient newClient = new ConnectedClient(socket,this,serverSocket2,username);
                        newClient.start();}
                        catch(Exception e)
                         {
                             System.out.println("error in creating client thread");
                             socket.close();
                         }
        
                    }
                    else
                    {
                        System.out.println("duplicate user found");
                        dataOutputStream.writeUTF("error");
                        socket.close();
                    }
           }
            else                                                                        //login case
            {
                 username = dataInputStream.readUTF();
                 password = dataInputStream.readUTF();
                        try{
                        
                            encryptedPassword =new HashPassword().hash(password);
                        }catch(Exception e)
                        {
                            System.out.println("error in password hashing");
                        }
                preparedStatement = dataBaseConnection.prepareStatement(Queries.loginQuery);
                preparedStatement.setString(1,username);
                try{
                resultSet=preparedStatement.executeQuery();
                }
                catch(Exception e){
                    System.out.println("error in login query");
                }
                
               
                resultSet.last();
                int row = resultSet.getRow();
                System.out.println(row);
               
                System.out.println(resultSet.getRow());
                if(resultSet.getRow()!=0)                                                  //if user exist in database
                {
                   
                    String dataBasePassword = resultSet.getString(1);
                    if(dataBasePassword.equals(encryptedPassword))                           //matching database password with 
                    {
                         System.out.println("Password matched");
                         ConnectedClient newClient = new ConnectedClient(socket,this,serverSocket2,username);
                         newClient.start();
                         dataOutputStream.writeUTF("ok");
                        
                    }
                    else{
                        System.out.println("pass not matched");
                        dataOutputStream.writeUTF("error");
                    }
                }
                else
                {
                        System.out.println("No entry matched matched");
                        dataOutputStream.writeUTF("error");
                }
                
                
            }
           
        }
    }

    public static void main(String[] args) throws Exception {
        
        FlipZonServer flipZonServer;
        flipZonServer = new FlipZonServer();
    }
    
}
