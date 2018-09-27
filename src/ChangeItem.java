
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ChangeItem extends javax.swing.JFrame {
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    DefaultTableModel tm;
    Collection col;
    public ChangeItem() {
        initComponents();
    }
    public ChangeItem(Socket s,DataInputStream din,DataOutputStream dout,Collection c) {
        initComponents();
        this.setVisible(true);
        this.s=s;
        this.din=din;
        this.col=c;
        this.dout=dout;
      
        
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stock = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        discount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();
        increase = new javax.swing.JButton();
        clickToRemove = new javax.swing.JButton();
        decrease = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update");

        discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inr/Dcr Stock");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("New Price");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("New Discount");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Expiry Date");

        increase.setText("Increase");
        increase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseActionPerformed(evt);
            }
        });

        clickToRemove.setText(" Remove");
        clickToRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickToRemoveActionPerformed(evt);
            }
        });

        decrease.setText("Decrease");
        decrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("* mention old details in case of no change. ");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setText("* for increase or decrease choose corresponding options.");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setText("* click remove to remove the item from inventory. ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stock, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(price, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(discount, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addGap(47, 47, 47))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(increase, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(decrease, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clickToRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increase)
                    .addComponent(decrease)
                    .addComponent(clickToRemove))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountActionPerformed
       
        
        
    }//GEN-LAST:event_discountActionPerformed

    private void clickToRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickToRemoveActionPerformed
        try {
            dout.writeUTF("removeitem");
            dout.writeUTF(col.str[8]);
            
        } catch (IOException ex) {
            Logger.getLogger(ChangeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            String reply = din.readUTF();
            if(reply.equals("ok"))
            {
                JOptionPane.showMessageDialog(null, "Item Removed");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Item is already sold");
            }
            
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Error in updation", "Warning!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_clickToRemoveActionPerformed

    private void increaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseActionPerformed
        String st=stock.getText();
        String pr=price.getText();
        String dis=discount.getText();
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        if(st.equals("")||pr.equals("")||dis.equals("")||date.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "Fill fields Correctly", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
        int pric=Integer.parseInt(pr);
        int sto=Integer.parseInt(st);
        int disc=Integer.parseInt(dis);
         if(pric<0 || sto<0 || disc<0)
        {
            JOptionPane.showMessageDialog(null, "Fill fields Correctly", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Fill fields Correctly", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }      
        String dat=sfd.format(date.getDate());
        try{
            dout.writeUTF("updateproductdetails");
            dout.writeUTF("increase");
            dout.writeUTF(col.str[8]);
            dout.writeUTF(pr);
            dout.writeUTF(st);
            dout.writeUTF(dis);
            dout.writeUTF(dat); 
             
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error in updation", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String reply = din.readUTF();
            if(reply.equals("ok"))
            {
                JOptionPane.showMessageDialog(null, "Stock increased");
                this.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "item sold please add the item ");
            }
            
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Error in updation", "Warning!", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_increaseActionPerformed

    private void decreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseActionPerformed
        
         String st=stock.getText();
        String pr=price.getText();
        String dis=discount.getText();
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        if(st.equals("")||pr.equals("")||dis.equals("")||date.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "Fill fields Correctly", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
        int pric=Integer.parseInt(pr);
        int sto=Integer.parseInt(st);
        int disc=Integer.parseInt(dis);
         if(pric<0 || sto<0 || disc<0)
        {
            JOptionPane.showMessageDialog(null, "Fill fields Correctly", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Fill fields Correctly", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }      
        String dat=sfd.format(date.getDate());
        try{
            dout.writeUTF("updateproductdetails");
            dout.writeUTF("decrease");
            dout.writeUTF(col.str[8]);
            dout.writeUTF(pr);
            dout.writeUTF(st);
            dout.writeUTF(dis);
            dout.writeUTF(dat); 
             
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error in updation", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String reply = din.readUTF();
            if(reply.equals("ok"))
            {
                JOptionPane.showMessageDialog(null, "Stock increased");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "previous stock is less than decreasing amount");
            }
            
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Error in updation", "Warning!", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_decreaseActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clickToRemove;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JButton decrease;
    private javax.swing.JTextField discount;
    private javax.swing.JButton increase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField price;
    private javax.swing.JTextField stock;
    // End of variables declaration//GEN-END:variables

}
