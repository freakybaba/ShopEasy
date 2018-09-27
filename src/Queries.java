/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flipzonserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class Queries {
     static String signupQuery = "select username from users where username=? OR email=?";
   static final String loginQuery = "select password from users where username=?";
     static final String updateUserTableQuery = "insert into users (username,password,email) values(?,?,?);";
   
    
    static String getProductStockQuery="SELECT stock FROM `products` WHERE seller=? AND fzid=?;";
   static String deleteFromProductQuery="DELETE FROM `products` WHERE `seller`=? AND `fzid`=?;";
   static  String moveCartToHistoryQuery="INSERT into history ( `categ`, `brand`, `price`, `stock`, `offerX`, `seller`, `extra`,"
            + " `ipath`, `fzid`, `expiry`, `cartowner`, `buyingdate`) SELECT `categ`, `brand`, `price`, `stock`,"
            + " `offerX`, `seller`, `extra`, `ipath`, `fzid`, `expiry`, `cartowner`,? FROM `cart` WHERE `cartowner`=?;";
    static String updateProductStockQuery="UPDATE `products` SET `stock`=? WHERE `fzid`=? AND `seller`=?;";
  static String emptyCartQuery="DELETE  FROM `cart` WHERE `cartowner`=?;";
     static  String deleteFromCartQuery="DELETE FROM `cart` WHERE `seller`=? AND `fzid`=? AND `cartowner`=?;";
   static  final String addProductQuery = "INSERT INTO `products`(`categ`, `brand`, `price`, "
                                + "`stock`, `offerX`, `seller`, `extra`, `ipath`,`fzid`,`expiry`) VALUES (?,?,?,?,?,?,?,?,?,?)";
    
     static final String productIdQuery = "SELECT `prodnum` FROM `users` WHERE `username`= ?" ;
     static final String updateProductIdQuery = "UPDATE `users` SET `prodnum`=? WHERE username= ?";
    static final String insertItemQuery = "INSERT INTO `cart`(`categ`, `brand`, `price`, "
                                + "`stock`, `offerX`, `seller`, `extra`, `ipath`,`fzid`,`expiry`,`cartowner`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    static final String selectCartQuery = "SELECT * FROM `cart` WHERE cartowner=?";
    //private static final String selectCartQuery = "SELECT * FROM `cart` WHERE cartowner=?";
    static final String updateCartQuery="UPDATE `cart` SET `stock`=? WHERE `fzid`=? AND `seller`=? AND `cartowner`=?";
    static final String getShoppingHistoryQuery="select * from `history` where `cartowner`=? order by id DESC;";
    static final String getSoldItemsQuery="select * from `history` where `seller`=? order by id DESC;";
    static final String getAddedItemsQuery="select * from `products` where `seller`=? order by id DESC;";
    static final String getBuyerTimeHistoryQuery="SELECT * FROM `history` WHERE `buyingdate`>=? AND `buyingdate`<=? AND `cartowner`=? order by id desc;";
    static final String checkItemAvailabilityQuery = "SELECT `stock` from `products` where `fzid`=? AND `seller`=?";
    static final String updateItemDetailsQuery="UPDATE `products` SET `price`=?,`stock`=?,`offerX`=?,`expiry`=? where `fzid`=? AND `seller`=?;";
    static final String updateCartDetailsQuery="UPDATE `cart` SET `price`=?,`offerX`=?,`expiry`=? where `fzid`=? AND `seller`=?;";
    static final String getcategoryCountQuery="SELECT count(*) FROM `history` WHERE seller=? AND `categ`=? AND `buyingdate`>=? AND `buyingdate`<=?;";
    static final String getUserTransactionQuery="select `transaction` from users where username=?";
    static final String updateUserTransactionQuery="Update `users` SET `transaction`=? where username=?;";
    static final String getTop5UsersQuery="select `username`,`transaction` from `users` order by `transaction` desc";
}
