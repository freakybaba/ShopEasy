
package flipzonserver;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConnectedClient extends Thread {
    private final Socket socket;
    private final String ThreadOwner;
    private final String que = "select * from `products` where `seller`!=? ";
    private final FlipZonServer flipZonServer;
    private final DataBaseConnection dataBaseConnection;
    private final ServerSocket serverSocket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String type;
     public static final int BUFFER_SIZE = 1024 * 50;
    private byte[] buffer;
    private final String path="C:\\Users\\hp\\Documents\\NetBeansProjects\\FlipZonServer\\Images\\";
  
   ConnectedClient(Socket socket,FlipZonServer flipZonServer,ServerSocket serverSocket,String user) throws Exception 
    {
        
                this.ThreadOwner = user;
                this.flipZonServer =flipZonServer;
                this.socket = socket;
                this.serverSocket = serverSocket;
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataBaseConnection= new DataBaseConnection();
                connection = new DataBaseConnection().makeConn();
               
    }
  
    @Override
    public void run()
    {
        try {
            Integer avgTransaction = 0;
            preparedStatement = connection.prepareStatement(Queries.getTop5UsersQuery);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int f = 0;
            int rows = resultSet.getRow();
            if(rows<=5)
            {
                dataOutputStream.writeUTF("G");
                f=1;
            }
            else{
                resultSet = preparedStatement.executeQuery();
                for(int i = 0;i<5;i++)
                {
                    resultSet.next();
                     avgTransaction = avgTransaction + resultSet.getInt(2);
                    if(resultSet.getString(1).equals(ThreadOwner))
                    {
                      
                         dataOutputStream.writeUTF("G");
                         System.out.println("Golden user logged in");
                         f=1;
                        
                    }
                }
            }
            if(f==0)
            {
                avgTransaction /= 5;
                  preparedStatement = connection.prepareStatement("select `transaction` from `users` where `username`= '"+ThreadOwner+"';");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer userTran = resultSet.getInt(1);
            if(userTran>=20000)
            {
                dataOutputStream.writeUTF("S");
                System.out.println("silver user logged in");
                f=2;
            }
            else
            {
                dataOutputStream.writeUTF("N");
            }
                
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
       
         
       while(true)
       {
           
           try {
               System.out.println("waiting to read query for user :"+ ThreadOwner);
                type = dataInputStream.readUTF();
                System.out.println(type);
                  if(type.equals("home"))
                  {
                    System.out.println("home query requested for :"+ThreadOwner);
                    String homeQuery = que +" order by id desc;";
                    preparedStatement = connection.prepareStatement(homeQuery);
                    preparedStatement.setString(1,ThreadOwner);
                    try{
                    resultSet= preparedStatement.executeQuery();
                    
                    }catch(Exception e){
                     System.out.println("error in query execution 'home' type");
                    }
                    resultSet.last();
                    int r = resultSet.getRow();
                    resultSet= preparedStatement.executeQuery();
                    Integer item = Math.min(r,10);
                     
                    dataOutputStream.writeUTF(item.toString());
                    for(int j = 0;j<item;j++)
                    {
                        resultSet.next();
                        String ipath = resultSet.getString(9);
                        System.out.println("path " +ipath);
                        dataOutputStream.writeUTF(resultSet.getString(2));dataOutputStream.writeUTF(resultSet.getString(3));
                        dataOutputStream.writeUTF(resultSet.getString(4));dataOutputStream.writeUTF(resultSet.getString(5));
                        dataOutputStream.writeUTF(resultSet.getString(6));dataOutputStream.writeUTF(resultSet.getString(7));
                        dataOutputStream.writeUTF(resultSet.getString(8));dataOutputStream.writeUTF(resultSet.getString(10));
                        dataOutputStream.writeUTF(resultSet.getString(11));
                        sentImage(ipath);
                            }
                    System.out.println(ThreadOwner + " "+ item+" rows sent");
                           
                       
                       
               }//end of home
                  else if(type.equals("mobile")||type.equals("fashion") || type.equals("other")||type.equals("electronics")||type.equals("food"))
                  {
                        System.out.println(type+" button clicked");
                        String tmpQ = que + "AND `categ`='"+type+"' order by price asc";
                        preparedStatement = connection.prepareStatement(tmpQ);
                         preparedStatement.setString(1,ThreadOwner);
                        resultSet= preparedStatement.executeQuery();
                        resultSet.last();
                        Integer r = resultSet.getRow();
                        dataOutputStream.writeUTF(r.toString());
                        resultSet= preparedStatement.executeQuery();
                        System.out.println(r);
                        for(int j = 0;j<r;j++)
                        {
                            resultSet.next();
                             String ipath = resultSet.getString(9);
                            dataOutputStream.writeUTF(resultSet.getString(2));dataOutputStream.writeUTF(resultSet.getString(3));
                            dataOutputStream.writeUTF(resultSet.getString(4));dataOutputStream.writeUTF(resultSet.getString(5));
                            dataOutputStream.writeUTF(resultSet.getString(6));dataOutputStream.writeUTF(resultSet.getString(7));
                            dataOutputStream.writeUTF(resultSet.getString(8));dataOutputStream.writeUTF(resultSet.getString(10));
                            dataOutputStream.writeUTF(resultSet.getString(11));
                               sentImage(ipath);
                        }
                        System.out.println(ThreadOwner + " "+ r+" rows sent");
                  }//end of category
                 
                  else if(type.equals("additem"))
                  {
                      
                      System.out.println("clicked to sell clicked");
                      String values[] = new String[8];
                      try{
                      for(int i = 0;i<8;i++)
                      {
                          values[i]=dataInputStream.readUTF();
                      }}
                      catch(Exception e){
                          System.out.println("error in reading");
                      }
                       preparedStatement = connection.prepareStatement(Queries.productIdQuery);
                       preparedStatement.setString(1,this.ThreadOwner);
                       ResultSet tmp =preparedStatement.executeQuery();
                       tmp.next();
                       Integer currfz = tmp.getInt(1);
                       currfz++;
                        String pathOfImage =path+this.ThreadOwner+ currfz.toString()+values[7];
                        System.out.println("path "+pathOfImage);
                      
                      this.getImage(pathOfImage);                                    //updating user table with product
                       preparedStatement = connection.prepareStatement(Queries.addProductQuery);
                       preparedStatement.setString(1, values[0]);
                       preparedStatement.setString(2, values[1]);
                       preparedStatement.setString(3, values[2]);
                       preparedStatement.setString(4, values[3]);
                       preparedStatement.setString(5, values[4]);
                       preparedStatement.setString(6, this.ThreadOwner);
                       preparedStatement.setString(7, values[5]);
                       preparedStatement.setString(8, pathOfImage);
                       preparedStatement.setInt(9,currfz);
                       preparedStatement.setString(10,values[6]);
                             for(int i = 0;i<8;i++)
                                 System.out.println("values "+values[i]);
                        try{
                        preparedStatement.execute();}
                        catch(Exception e){
                            System.out.println("error in additem type");
                        }
                                                                                     //increasing product number of user
                        preparedStatement = connection.prepareStatement(Queries.updateProductIdQuery);
                         preparedStatement.setInt(1,currfz);
                        preparedStatement.setString(2,this.ThreadOwner);
                       
                        preparedStatement.execute();
                      
                  }//end of additem
                  else if(type.equals("addtocart"))
                  {
                      System.out.println("add to cart clicked");
                      String fid = (dataInputStream.readUTF());
                      String seller = dataInputStream.readUTF();
                      Integer quantity = Integer.parseInt(dataInputStream.readUTF());
                      System.out.println(fid+" "+seller);
                      String q = "SELECT * FROM `products` WHERE `fzid`='"+fid+"' AND `seller`='"+seller+"'";
                      String q1 = "SELECT `stock` FROM `cart` WHERE `fzid`='"+fid+"' AND `seller`='"+seller+"' AND `cartowner`='"+this.ThreadOwner+"'";
                      preparedStatement = connection.prepareStatement(q);
                     resultSet=preparedStatement.executeQuery();
                      resultSet.last();
                      Integer rows = resultSet.getRow();
                      resultSet = preparedStatement.executeQuery();
                      if(rows==0)
                      {
                          System.out.println("item not found");
                          dataOutputStream.writeUTF("error");
                          continue;
                      }
                      resultSet.next();
                      int prevStockInCart=0;
                      preparedStatement = connection.prepareStatement(q1);
                      ResultSet resultSet1 = preparedStatement.executeQuery();
                      resultSet1.last();
                      if(resultSet1.getRow()!=0)
                      {
                          prevStockInCart=resultSet1.getInt(1);
                      }
                      int currQ= resultSet.getInt(5);
                      if(currQ<quantity+prevStockInCart)
                      {
                          System.out.println("product not sufficient");
                          dataOutputStream.writeUTF("less");
                          continue;
                      }
                      String data[]= new String[11];
                      for(int i = 0;i<10;i++)
                      {
                          data[i]= resultSet.getString(i+2);
                      }
                      Integer newStock = quantity + prevStockInCart;
                      data[3] = newStock.toString();
                      data[10]=this.ThreadOwner;
                      if(prevStockInCart>0)
                      {
                          preparedStatement = connection.prepareStatement(Queries.updateCartQuery);
                          preparedStatement.setInt(1, newStock);
                          preparedStatement.setString(2,fid);
                          preparedStatement.setString(3,seller);
                          preparedStatement.setString(4,this.ThreadOwner);
                          
                          preparedStatement.execute();
                      }
                      else{
                      preparedStatement = connection.prepareStatement(Queries.insertItemQuery);
                      for(int i = 0;i<11;i++)
                      preparedStatement.setString(i+1, data[i]);
                      preparedStatement.execute();
                      }
                      System.out.println("added to cat");
                      dataOutputStream.writeUTF("ok");
                      
                      
                  }//end of add to cart
                  else if(type.equals("cart"))
                  {
                      System.out.println("cart clicked");
                       preparedStatement = connection.prepareStatement(Queries.selectCartQuery);
                       preparedStatement.setString(1,this.ThreadOwner);
                       resultSet = preparedStatement.executeQuery();
                       resultSet.last();
                       Integer rows = resultSet.getRow();
                       dataOutputStream.writeUTF(rows.toString());
                       resultSet = preparedStatement.executeQuery();
                       for(int i = 0;i<rows;i++)
                       {
                           resultSet.next();
                           System.out.println("sending cart");
                           for(int j = 1;j<=10;j++)
                               dataOutputStream.writeUTF(resultSet.getString(j));
                       }
                       
                       System.out.println(ThreadOwner + " "+ rows+" rows sent");
                  }//end of cart                                                                                         //cart button
                  else if(type.equals("buynow"))
                  {
                      System.out.println("buy Now Clicked");
                      
                      
                      ArrayList<Integer> stockArray=SharedMethods.synchronizedMethods(this.ThreadOwner,type,dataInputStream,dataOutputStream);
                      Integer size = stockArray.size();
                      dataOutputStream.writeUTF(size.toString());
                      System.out.println("size of array :"+size);
                      for(int i = 0;i<stockArray.size();i++)
                      {
                          dataOutputStream.writeUTF(stockArray.get(i).toString());
                          System.out.println(stockArray.get(i).toString());
                      }
                  }//end of buynow
                  else if(type.equals("removecartrow"))
                  {
                      String fzid = dataInputStream.readUTF();
                      String seller = dataInputStream.readUTF();
                        preparedStatement = connection.prepareStatement(Queries.deleteFromCartQuery);
                       preparedStatement.setString(1,seller);
                       preparedStatement.setString(2, fzid);
                       preparedStatement.setString(3, this.ThreadOwner);
                       preparedStatement.execute();
                       System.out.println(ThreadOwner + "  :Item deleted");
                      
                  }//end of remove cartRow
                  else if(type.equals("history"))
                  {
                      System.out.println(ThreadOwner +" history clicked");
                      preparedStatement = connection.prepareStatement(Queries.getShoppingHistoryQuery);
                      preparedStatement.setString(1, ThreadOwner);
                      resultSet  = preparedStatement.executeQuery();
                      Integer rows=0;
                      resultSet.last();
                      rows = resultSet.getRow();
                      resultSet  = preparedStatement.executeQuery();
                      dataOutputStream.writeUTF(rows.toString());
                      for(int i = 0;i<rows;i++)
                      {
                          resultSet.next();
                          for(int j = 0;j<12;j++)
                              dataOutputStream.writeUTF(resultSet.getString(j+2));
                      }
                      System.out.println(ThreadOwner + " "+ rows+" rows sent");
                      
                  }// end of hostoty
                  else if(type.equals("solditems"))
                  {
                       System.out.println(ThreadOwner +" solditems clicked");
                      preparedStatement = connection.prepareStatement(Queries.getSoldItemsQuery);
                      preparedStatement.setString(1, ThreadOwner);
                      resultSet  = preparedStatement.executeQuery();
                      Integer rows=0;
                      resultSet.last();
                      rows = resultSet.getRow();
                      resultSet  = preparedStatement.executeQuery();
                      dataOutputStream.writeUTF(rows.toString());
                      for(int i = 0;i<rows;i++)
                      {
                          resultSet.next();
                          for(int j = 0;j<12;j++)
                              dataOutputStream.writeUTF(resultSet.getString(j+2));
                      }
                      System.out.println(ThreadOwner + " "+ rows+" rows sent");
                  }//end of sold items
                  else if(type.equals("addeditems"))
                  {
                       System.out.println(ThreadOwner +" addeditems clicked");
                       preparedStatement = connection.prepareStatement(Queries.getAddedItemsQuery);
                      preparedStatement.setString(1, ThreadOwner);
                      resultSet  = preparedStatement.executeQuery();
                      Integer rows=0;
                      resultSet.last();
                      rows = resultSet.getRow();
                      resultSet  = preparedStatement.executeQuery();
                      dataOutputStream.writeUTF(rows.toString());
                      for(int i = 0;i<rows;i++)
                      {
                          resultSet.next();
                          for(int j = 0;j<10;j++)
                              dataOutputStream.writeUTF(resultSet.getString(j+2));
                      }
                      System.out.println(ThreadOwner + " "+ rows+" rows sent");
                  }//end of added items
                  else if(type.equals("buyertimehistory"))
                  {
                       System.out.println(ThreadOwner +" buyertimehistory");
                      
                      String fromDate = dataInputStream.readUTF();
                      String toDate = dataInputStream.readUTF();
                      preparedStatement = connection.prepareStatement(Queries.getBuyerTimeHistoryQuery);
                      preparedStatement.setString(1,fromDate);
                      preparedStatement.setString(2,toDate);
                      preparedStatement.setString(3,ThreadOwner);
                      resultSet = preparedStatement.executeQuery();
                      resultSet.last();
                      Integer rows = resultSet.getRow();
                      resultSet = preparedStatement.executeQuery();
                      dataOutputStream.writeUTF(rows.toString());
                      for(int i = 0;i<rows;i++)
                      {
                          resultSet.next();
                          for(int j = 0;j<12;j++)
                          {
                              dataOutputStream.writeUTF(resultSet.getString(j+2));
                          }
                      }
                       System.out.println(ThreadOwner + " "+ rows+" rows sent");
                      }//end of buyertimehistory
                  else if(type.equals("updateproductdetails"))
                  {
                       System.out.println(ThreadOwner +" updateproductdatails");
                      ArrayList<Integer> stockArray=SharedMethods.synchronizedMethods
                                         (this.ThreadOwner,type,dataInputStream,dataOutputStream);
                  }//updateproduct details
                  else if(type.equals("removeitem"))
                  {
                      System.out.println(ThreadOwner +" :remove item");
                      ArrayList<Integer> stockArray=SharedMethods.synchronizedMethods
                                         (this.ThreadOwner,type,dataInputStream,dataOutputStream);
                  }
                  else if(type.equals("analysis"))
                  {
                      System.out.println(ThreadOwner+": analysis entered");
                      String fromDate = dataInputStream.readUTF();
                      String toDate = dataInputStream.readUTF();
                      String[] categoryArray = {"mobile","electronics","food","fashion","other"};
                      for(int i = 0;i<5;i++)
                      {     
                      preparedStatement = connection.prepareStatement(Queries.getcategoryCountQuery);
                      preparedStatement.setString(1,ThreadOwner);
                      preparedStatement.setString(2,categoryArray[i]);
                      preparedStatement.setString(3,fromDate);
                      preparedStatement.setString(4,toDate);
                      resultSet = preparedStatement.executeQuery();
                      resultSet.next();
                          System.out.println(resultSet.getString(1));
                      dataOutputStream.writeUTF(resultSet.getString(1));
                      }
                      
                  }
           } catch (Exception ex) {
               System.out.println("Client closed the connection or Exception in run "+socket+"");
               try {
                   socket.close();
               } catch (IOException ex1) {
                   
               }
               break;
           }
           
       }
    }
     public void sentImage(String path) throws  Exception {
         try{
             System.out.println("sent called");
             Socket socket = serverSocket.accept();
             System.out.println("connection for socket made");
             System.out.println(socket);
             System.out.println(path);
                BufferedInputStream in=null ;
                BufferedOutputStream out=null;
                
                System.out.println("connected to send image");
                try{
            buffer = new byte[BUFFER_SIZE];
            
                  in=  new BufferedInputStream(
                         new FileInputStream(path));

                out= 
                    new BufferedOutputStream(socket.getOutputStream());}
                catch(Exception e)
                {
                    System.out.println(e);
                }
   System.out.println("stream created to send image");

               int len ;
               while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                    System.out.print("#");
               }
               System.out.println("img send");
               in.close();
               out.flush();
               out.close();
               socket.close();

              // System.out.println("\nImage sent!");
         }catch(Exception e)
         {
             System.out.println("exception in sending image");
         }
         

     }
     
    public void getImage(String path) throws Exception
     {
        try{
            System.out.println("get called");
          
         Socket socket = serverSocket.accept();
            System.out.println("connected to receive image");
          BufferedInputStream in = 
               new BufferedInputStream(socket.getInputStream());

          BufferedOutputStream out = 
               new BufferedOutputStream(new FileOutputStream(path));
               
          int len = 0;
          while ((len = in.read(buffer)) > 0) {
               out.write(buffer, 0, len);
               System.out.print("#");
          }
         System.out.println("-------------------------------------------------------------------imge added");
          in.close();
          out.flush();
          out.close();
          socket.close();
         
        }
        catch(Exception e)
        {
            System.out.println("error in image dl");
        }
     }

   
    
    
}
