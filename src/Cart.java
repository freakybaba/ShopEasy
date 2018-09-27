import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Cart extends javax.swing.JFrame {
    
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    DefaultTableModel tm;
    int numq;
    float totalPriceOfAllItems=0;
    ArrayList<String> fzidArray=new ArrayList<>();
    ArrayList<String> sellerArray=new ArrayList<>();
    public Cart() {
        initComponents();
    }
    
     public Cart(Socket s,DataInputStream din,DataOutputStream dout) {
        initComponents();
        this.setVisible(true);
        this.s=s;
        this.din=din;
        this.dout=dout;
        this.tm = (DefaultTableModel) table.getModel();
        try {
            dout.writeUTF("cart");
        } catch (IOException ex) {
             System.out.println("Error in sending cart");        
         }
        this.initcart();
    }
     
     public void initcart()
     {
         int totalamount=0;
        try {
            numq=Integer.parseInt(din.readUTF());
        } catch (IOException ex) {
            System.out.println("Error in reading count");
        }
        table.setForeground(Color.red);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        JTableHeader header = table.getTableHeader();
       header.setPreferredSize(new Dimension(100, 32));
       
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        table.getTableHeader().setBackground(Color.GREEN);
        
        
        ArrayList<Collection> al=new ArrayList<Collection>(numq+1);
         System.out.println(din);
         
        for(int i=0;i<numq;i++)
        {
            String str[]=new String[10];
            for(int j=0;j<10;j++)
            {
                try {
                    str[j]=din.readUTF();
                } catch (IOException ex) {
                    System.out.println("Error in reading details");
                }
            }
            
            
            // str[6]=seller
            // str[9]=fzid
            sellerArray.add(str[6]);
            fzidArray.add(str[9]);
            
            
            
            
            al.add(new Collection(str));
            float price=Float.parseFloat(str[3]);
            float quantity=Float.parseFloat(str[4]);
            float discount=Float.parseFloat(str[5]);
            
            float costOfOneItem=(float) (price-(price * discount * 0.01));
            float totalPriceOfItem=(costOfOneItem * quantity);
            totalPriceOfAllItems+=totalPriceOfItem;
            
            
            tm.addRow(new Object[]{str[2],str[4],str[3],str[5],str[6],Float.toString(totalPriceOfItem)});
            
            table.setRowHeight(50);
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            
            
            
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            
        }
        float dis=(float) ((totalPriceOfAllItems * (IPtoconnect.extraDiscount))*(0.01));
        totalPriceOfAllItems=(totalPriceOfAllItems-dis);
        totalPrice.setText(Float.toString(totalPriceOfAllItems));
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        totalPrice = new javax.swing.JLabel();
        homeRedirect = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        clicktosell = new javax.swing.JLabel();
        totalAmount = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        history = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        buynow = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cartBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalPrice.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        totalPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalPrice.setText("4535");
        getContentPane().add(totalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 550, 160, 30));

        homeRedirect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeRedirectMouseClicked(evt);
            }
        });
        getContentPane().add(homeRedirect, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 100, 40));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 50, 130, 50));

        clicktosell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clicktosellMouseClicked(evt);
            }
        });
        getContentPane().add(clicktosell, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 10, 150, 30));

        totalAmount.setBackground(new java.awt.Color(12, 168, 186));
        totalAmount.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        totalAmount.setForeground(new java.awt.Color(66, 35, 231));
        totalAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalAmount.setText("Total Price");
        getContentPane().add(totalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 550, 150, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Email:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 130, 30));

        address.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        address.setForeground(new java.awt.Color(0, 0, 255));
        address.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        address.setText("Shipping Address:");
        getContentPane().add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 230, 30));

        email.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(15, 185, 20));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 560, 310, 30));

        history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyMouseClicked(evt);
            }
        });
        getContentPane().add(history, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 120, 50));

        addressField.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        addressField.setForeground(new java.awt.Color(50, 175, 18));
        getContentPane().add(addressField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 480, 30));

        buynow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buynowMouseClicked(evt);
            }
        });
        getContentPane().add(buynow, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 530, 190, 40));

        table.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Brand", "Quantity", "Price", "Discount", "Seller", "Total Sum"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 97, 1300, 430));

        cartBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cart.png"))); // NOI18N
        getContentPane().add(cartBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buynowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buynowMouseClicked
          final Date date=new Date();
        if(addressField.getText().equals("") || email.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please fill shipping address and Email");
            return;
        }
         final String address = addressField.getText();
         final String emailAddress = email.getText();
        if(table.getRowCount()==0)
        {
            JOptionPane.showMessageDialog(null,"Add items to buy");
        }
        else 
        {
          try {
            int f=0;
            String itemOutOfStock="item number";
            dout.writeUTF("buynow");
            dout.writeUTF(Float.toString(totalPriceOfAllItems));
            Integer numq=Integer.parseInt(din.readUTF());
          //    System.out.println(numq);
            ArrayList<Integer> cartItem=new ArrayList<Integer>();
            for(int i=0;i<numq;i++)
            {
                String temp=din.readUTF();
                //System.out.println(temp);
                cartItem.add(Integer.parseInt(temp));
                
            }
            Integer j;
            String temp;
            for(int i=0;i<cartItem.size();i++)
            {
                temp="";
                if(cartItem.get(i)<0)
                {
                    j=i+1;
                    f=1;
                    itemOutOfStock=itemOutOfStock+" "+j.toString()+", ";
                    //temp=j.toString()+" "+"is out of stock.";
                    // JOptionPane.showMessageDialog(null,temp);
                }
            }
            itemOutOfStock=itemOutOfStock+" are out of stock";
            if(f==1)
            {
                JOptionPane.showMessageDialog(null,itemOutOfStock);
                return;
            }
            
            JOptionPane.showMessageDialog(null,"bill generated and sent to Email");
            
            tm.setRowCount(0);
            totalPrice.setText(Float.toString(0.00f));
            final String numberOfItems = numq.toString();
            try{
            new Thread(){
                public void run()
                {
                    SendMail.send(emailAddress, "Bill generation", date.toString(),address,numberOfItems,Float.toString(totalPriceOfAllItems));
                }
            }.start();
            }
            catch(Exception e)
            {
                System.out.println("exception in bill generation");
            }
            
          } catch (IOException ex) {
              System.out.println("--------Error in reading aboout cart items-----");
          } 
        }
        
    }//GEN-LAST:event_buynowMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.setVisible(false);
        new SignIn().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void homeRedirectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeRedirectMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        try {
            Home home=new Home(s,din,dout);
        } catch (Exception ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_homeRedirectMouseClicked

    private void clicktosellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clicktosellMouseClicked
       this.setVisible(false);
       Admin admin=new Admin(s,din,dout);
    }//GEN-LAST:event_clicktosellMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row=table.getSelectedRow();
        try {
            int p=JOptionPane.showConfirmDialog(null,"Do you really want to delete?","Delete",JOptionPane.YES_NO_OPTION );
            if(p==0){
             dout.writeUTF("removecartrow");
             dout.writeUTF(fzidArray.get(row));
             dout.writeUTF(sellerArray.get(row));
             this.setVisible(false);
             new Cart(s,din,dout).setVisible(true);
            }
        } catch (IOException ex) {
            //Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in deletion");
        }
    }//GEN-LAST:event_tableMouseClicked

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void historyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyMouseClicked
        // TODO add your handling code here:
        ClientHistory clienhistory=new ClientHistory(s,din,dout);
        
    }//GEN-LAST:event_historyMouseClicked

   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JTextField addressField;
    private javax.swing.JLabel buynow;
    private javax.swing.JLabel cartBackground;
    private javax.swing.JLabel clicktosell;
    private javax.swing.JTextField email;
    private javax.swing.JLabel history;
    private javax.swing.JLabel homeRedirect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalAmount;
    private javax.swing.JLabel totalPrice;
    // End of variables declaration//GEN-END:variables
}
