/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flipzonserver;

import static flipzonserver.FlipZonServer.preparedStatement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hp
 */
public class SharedMethods 
{
     static PreparedStatement preparedStatement;
     static Connection dataBaseConnection;
     static ResultSet resultSet;

   
   
 public synchronized static ArrayList<Integer> synchronizedMethods(String cartOwner,String type,DataInputStream dataInputStream,DataOutputStream dataOutputStream) throws Exception
    {
          ArrayList <Integer> stockArray = new ArrayList<>();
            System.out.println("entered in synchronized method");
            if(type.equals("buynow"))
            {   
             String amount = dataInputStream.readUTF();
             int amt = (int)Float.parseFloat(amount);
             preparedStatement = FlipZonServer.dataBaseConnection.prepareStatement(Queries.selectCartQuery);
             preparedStatement.setString(1,cartOwner);
             resultSet = preparedStatement.executeQuery();
            // ArrayList <Integer> stockArray = new ArrayList<>();
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
            //updation  of product Stock
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
                 preparedStatement = FlipZonServer.dataBaseConnection.prepareStatement(Queries.updateProductStockQuery);
                 preparedStatement.setInt(1,stockArray.get(i));
                 preparedStatement.setString(2,fzidArray.get(i));
                 preparedStatement.setString(3,sellerArray.get(i));
                 preparedStatement.execute();
                }

            }
            //updation of transaction of user
            preparedStatement = dataBaseConnection.prepareStatement(Queries.getUserTransactionQuery);
            preparedStatement.setString(1,cartOwner);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int previousTransaction = resultSet.getInt(1);
            int newTransaction = previousTransaction+amt;
            preparedStatement = FlipZonServer.dataBaseConnection.prepareStatement(Queries.updateUserTransactionQuery);
            preparedStatement.setInt(1,newTransaction);
            preparedStatement.setString(2,cartOwner);
            preparedStatement.execute();
            
        preparedStatement = dataBaseConnection.prepareStatement(Queries.emptyCartQuery);
        preparedStatement.setString(1,cartOwner);
        preparedStatement.execute();
        System.out.println("ended successfully");
        return stockArray;
       }//end of buynow
      else if(type.equals("updateproductdetails"))
      {
         // System.out.println("");
          String seller = cartOwner;
          String operation = dataInputStream.readUTF();
          String fzid = dataInputStream.readUTF();
          int newPrice,newStock,newDiscount;
          String newExpiry;
          newPrice = Integer.parseInt(dataInputStream.readUTF());
          newStock = Integer.parseInt(dataInputStream.readUTF());
          newDiscount = Integer.parseInt(dataInputStream.readUTF());
          newExpiry = dataInputStream.readUTF();
          //check for if item has been already sold out
          preparedStatement =FlipZonServer.dataBaseConnection.prepareStatement(Queries.checkItemAvailabilityQuery);
          preparedStatement.setString(1,fzid);
          preparedStatement.setString(2,seller);
          resultSet =preparedStatement.executeQuery();
          resultSet.last();
          int rows=resultSet.getRow();
          if(rows==0) //if yes
          {
              dataOutputStream.writeUTF("deleted");
              return stockArray;
          }//else check for new updated stock as it should not be negative
          int currentStock =resultSet.getInt(1);
          int updateStock=0;
          if(operation.equals("decrease"))
          {
             if(currentStock-newStock<=0)
             {
                 dataOutputStream.writeUTF("error");
                 return stockArray;
             }
            updateStock = currentStock-newStock;
          }
          else
          {
              updateStock = currentStock+newStock;
          }
          dataOutputStream.writeUTF("ok");
          
          //update product details
          preparedStatement = FlipZonServer.dataBaseConnection.prepareStatement(Queries.updateItemDetailsQuery);
          preparedStatement.setInt(1,newPrice);
          preparedStatement.setInt(2,updateStock);
          preparedStatement.setInt(3,newDiscount);
          preparedStatement.setString(4,newExpiry);
          preparedStatement.setString(5,fzid);
          preparedStatement.setString(6,seller);
          preparedStatement.execute();
          
          //update cart
         preparedStatement = FlipZonServer.dataBaseConnection.prepareStatement(Queries.updateCartDetailsQuery);
          preparedStatement.setInt(1,newPrice);
          preparedStatement.setInt(2,newDiscount);
          preparedStatement.setString(3,newExpiry);
          preparedStatement.setString(4,fzid);
          preparedStatement.setString(5,seller);
          preparedStatement.execute();
          
          
          System.out.println("ended successfully");
          
      }//end of update
      else if(type.equals("removeitem"))
      {
          String fzid = dataInputStream.readUTF();
           String seller = cartOwner;
           //check if exist or not
          preparedStatement =FlipZonServer.dataBaseConnection.prepareStatement(Queries.checkItemAvailabilityQuery);
          preparedStatement.setString(1,fzid);
          preparedStatement.setString(2,seller);
          resultSet =preparedStatement.executeQuery();
          resultSet.last();
          int rows=resultSet.getRow();
          if(rows==0) //if not
          {
              dataOutputStream.writeUTF("deleted");
              return stockArray;
          
          }
          dataOutputStream.writeUTF("ok");
          preparedStatement =FlipZonServer.dataBaseConnection.prepareStatement(Queries.deleteFromProductQuery);
          preparedStatement.setString(1,seller);
          preparedStatement.setString(2,fzid);
          preparedStatement.execute();
          return stockArray;
          
      }
     return stockArray;
    }
 

}