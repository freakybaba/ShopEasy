
import java.awt.Image;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;




public class Admin extends javax.swing.JFrame {

    String impath="";
    File file=null;
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    byte buffer[] = new byte[1024*5];
    
    
    
    public Admin() {
        initComponents();
    }
    
     public Admin(Socket s,DataInputStream din,DataOutputStream dout) {
        initComponents();
        this.s=s;
        this.din=din;
        this.dout=dout;
        this.setVisible(true);
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stock = new javax.swing.JTextField();
        dchooser = new com.toedter.calendar.JDateChooser();
        price = new javax.swing.JTextField();
        brand = new javax.swing.JTextField();
        description = new javax.swing.JTextField();
        catg = new javax.swing.JComboBox<>();
        clicktobuy = new javax.swing.JLabel();
        addeditems = new javax.swing.JLabel();
        soldItems = new javax.swing.JLabel();
        browse = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        analysis = new javax.swing.JLabel();
        add = new javax.swing.JLabel();
        offer = new javax.swing.JTextField();
        pimage = new javax.swing.JLabel();
        bgadmin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 170, -1));
        getContentPane().add(dchooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, 210, -1));
        getContentPane().add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 170, -1));
        getContentPane().add(brand, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 170, -1));

        description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionActionPerformed(evt);
            }
        });
        getContentPane().add(description, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 220, 220, 220));

        catg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mobile", "electronics", "food", "fashion", "other" }));
        getContentPane().add(catg, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 170, -1));

        clicktobuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clicktobuyMouseClicked(evt);
            }
        });
        getContentPane().add(clicktobuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 7, 140, 40));

        addeditems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addeditemsMouseClicked(evt);
            }
        });
        getContentPane().add(addeditems, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 280, 60));

        soldItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                soldItemsMouseClicked(evt);
            }
        });
        getContentPane().add(soldItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 280, 60));

        browse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseMouseClicked(evt);
            }
        });
        getContentPane().add(browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 70, 30));

        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 40, 110, 40));

        analysis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                analysisMouseClicked(evt);
            }
        });
        getContentPane().add(analysis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 280, 60));

        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 430, 140, 50));
        getContentPane().add(offer, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 160, 210, -1));
        getContentPane().add(pimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 340, 280));

        bgadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/additem.png"))); // NOI18N
        getContentPane().add(bgadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        String [] str = new String[11];
        int i=0;
        int flag=1;
        String st=catg.getSelectedItem().toString();
        str[i++]=st;
        
        str[i++]=brand.getText();
        
        try {  
            
        int a=Integer.parseInt(price.getText());
        int b=Integer.parseInt(stock.getText());
        int c=Integer.parseInt(offer.getText());
        if(a<=0||b<=0||c<0|| a>=99999999||b>=99999999||c>100)
        {
            flag=0;
            JOptionPane.showMessageDialog(null,"choose specific quantity");
            return;
        }
         
        } 
         catch (NumberFormatException e) {
             flag=0;
            JOptionPane.showMessageDialog(null,"Fill Integers at specified position not strings");
            return;
         }

        
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        String date=sfd.format(dchooser.getDate());
        System.out.println(date);
        Date d = new Date();
        String currDate = sfd.format(d);
       
        
        str[i++]=price.getText();
        str[i++]=stock.getText();
        str[i++]=offer.getText();
        str[i++]=description.getText();
        str[i++]=date;
        
        if(impath.equals(""))
        {
            flag=0;
             JOptionPane.showMessageDialog(null,"Choose an image for item");
            System.out.println("image is not selected");
            return;
        }
        else
        {
            str[i++]=file.getName().toString();
        }
        
        
        
        
            
           if(date.compareTo(currDate)<=0)
        {
            flag = 0;
            JOptionPane.showMessageDialog(null,"Choose Expire date correctly");
            return;
        }
        


           for(int j=0;j<7;j++)
           {   
              System.out.println("out "+j);
              if(str[j].equals(""))
              {
                 flag=0;
                 System.out.println(j);
                 JOptionPane.showMessageDialog(null, "No entry Should be Empty");
                 return;
              }
           }
           
          
        
          System.out.println(flag);
        if(flag==1)
        {
            try{
                System.out.println("adding");
            dout.writeUTF("additem");
            for(int j=0;j<8;j++){
                dout.writeUTF(str[j]);
                 //System.out.println("added");
            }
               
            }
            catch(Exception e)
            {
                System.out.println("Error in retailer sending");
            }
            
            try {
                this.startClient();
                 JOptionPane.showMessageDialog(null, "Item uploaded successfully");
                 this.setVisible(false);
                 new Admin(s,din,dout).setVisible(true);
                
            } catch (Exception ex) {
                System.out.println("error in image writing");
            }
        }
        
        
    }//GEN-LAST:event_addMouseClicked

    private void browseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseClicked
        
         file=null;
        JFileChooser jf=new JFileChooser();
       
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg","gif","jpeg","png"); 
        jf.setFileFilter(filter);
        int option=jf.showOpenDialog(this);
        
        if(option==JFileChooser.APPROVE_OPTION)
        {
           file=jf.getSelectedFile();
           if(file.exists())
           {
                  System.out.println(file.getPath());
                   
                   impath=file.getAbsolutePath();
               
                  pimage.setIcon(new ImageIcon(new ImageIcon(impath).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                  System.out.println(file.getAbsolutePath());
           }
           else{
                   impath="";
                   System.out.println("file does not exist");
                }

                       
                
        }   
    }//GEN-LAST:event_browseMouseClicked

    private void descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionActionPerformed
        
    }//GEN-LAST:event_descriptionActionPerformed

    private void clicktobuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clicktobuyMouseClicked
       this.setVisible(false);
        try {
            Home ho = new Home(s,din,dout);
        } catch (Exception ex) {
            System.out.println("error in redirect to home");
        }
    }//GEN-LAST:event_clicktobuyMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        this.setVisible(false);
        SignIn signin=new SignIn();
        signin.setVisible(true);
    }//GEN-LAST:event_logoutMouseClicked

    private void addeditemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addeditemsMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        AddedItem ad = new AddedItem(s,din,dout);
    }//GEN-LAST:event_addeditemsMouseClicked

    private void soldItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_soldItemsMouseClicked
        System.out.println("Sold items clicked");
        SoldItemHistory sold=new SoldItemHistory(s,din,dout);    
        
    }//GEN-LAST:event_soldItemsMouseClicked

    private void analysisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_analysisMouseClicked
         Analysis analysis=new Analysis(s,din,dout);
    }//GEN-LAST:event_analysisMouseClicked
    
public void startClient() throws Exception {
               
         try{
            // sleep(600);
        Socket socket = new Socket(IPtoconnect.ip,4321);
         
          BufferedInputStream in = 
               new BufferedInputStream(new FileInputStream(impath));

          BufferedOutputStream out = 
               new BufferedOutputStream(socket.getOutputStream());
               
          
          int len = 0;
          while ((len = in.read(buffer)) > 0) {
               out.write(buffer, 0, len);
               System.out.print("#");
          }
          in.close();
          out.flush();
          out.close();
         
          socket.close();
          System.out.println("\nDone!");
         }
         catch(Exception e)
         {
             
         }
     }
   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JLabel addeditems;
    private javax.swing.JLabel analysis;
    private javax.swing.JLabel bgadmin;
    private javax.swing.JTextField brand;
    private javax.swing.JLabel browse;
    private javax.swing.JComboBox<String> catg;
    private javax.swing.JLabel clicktobuy;
    private com.toedter.calendar.JDateChooser dchooser;
    private javax.swing.JTextField description;
    private javax.swing.JLabel logout;
    private javax.swing.JTextField offer;
    private javax.swing.JLabel pimage;
    private javax.swing.JTextField price;
    private javax.swing.JLabel soldItems;
    private javax.swing.JTextField stock;
    // End of variables declaration//GEN-END:variables

   
}
