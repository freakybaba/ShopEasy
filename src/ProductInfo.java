
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ProductInfo extends javax.swing.JFrame {
         Socket s;
         DataInputStream din;
         DataOutputStream dout;
         Collection c;
         ArrayList<String> about=new ArrayList<String>();
   
    public ProductInfo() {
        initComponents();
    }
    
     public ProductInfo(Socket s,DataInputStream din,DataOutputStream dout,Collection c,ArrayList<String> st) {
        initComponents();
        this.din=din;
        this.dout=dout;
        this.s=s;
        this.c=c;
        this.about=st;
        this.setVisible(true);
        this.initinfo();
        
    }
    
     public void initinfo()
     {
         for(int i=0;i<9;i++)
         {
             pinfo.append(about.get(i)+" : "+c.str[i]+"\n\n");
         }
         System.out.println(c.str[9]);
         pimage.setIcon(new ImageIcon(new ImageIcon(c.str[9]).getImage().getScaledInstance(380, 430, Image.SCALE_DEFAULT)));
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addtocart = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pimage = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pinfo = new javax.swing.JTextArea();
        quantity = new javax.swing.JTextField();
        productback = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Product Information");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addtocart.setText("ADD TO CART");
        addtocart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtocartActionPerformed(evt);
            }
        });
        getContentPane().add(addtocart, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 480, 150, 40));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quantity");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 410, 100, 40));

        pimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(pimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 390, 440));

        pinfo.setEditable(false);
        pinfo.setBackground(new java.awt.Color(250, 248, 250));
        pinfo.setColumns(20);
        pinfo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        pinfo.setForeground(new java.awt.Color(241, 19, 17));
        pinfo.setRows(5);
        pinfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(pinfo);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 370, 340));
        getContentPane().add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 410, 120, 40));

        productback.setBackground(new java.awt.Color(148, 229, 66));
        productback.setForeground(new java.awt.Color(229, 50, 50));
        productback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/productBack.jpg"))); // NOI18N
        getContentPane().add(productback, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addtocartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtocartActionPerformed
       
             try {
                 
                 if(quantity.getText().equals(""))
                 {
                     JOptionPane.showMessageDialog(null,"Select a valid quantity");
                     return;
                 }
                 int stockSelected = Integer.parseInt(quantity.getText());
                 if(stockSelected<=0)
                 {
                     JOptionPane.showMessageDialog(null,"Select a valid quantity");
                     return;
                 }
                 dout.writeUTF("addtocart");
                 dout.writeUTF(c.str[7]);
                 dout.writeUTF(c.str[5]);
                 dout.writeUTF(quantity.getText());
                 
                 String s=din.readUTF();
                 
                 if(s.equals("error"))
                 {
                    JOptionPane.showMessageDialog(null,"Out in stock,Click Home");
                 }
                 else if(s.equals("less"))
                 {
                     JOptionPane.showMessageDialog(null,"Select less quatity, Click Home to refersh");
                 }
                 else
                 {
                     JOptionPane.showMessageDialog(null,"Added to cart");
                 }
                 
             } catch (IOException ex){
                 System.out.println("Not querying about product info");
             }
    }//GEN-LAST:event_addtocartActionPerformed

   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addtocart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pimage;
    private javax.swing.JTextArea pinfo;
    private javax.swing.JLabel productback;
    private javax.swing.JTextField quantity;
    // End of variables declaration//GEN-END:variables
}
