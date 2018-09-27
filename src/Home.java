
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class Home extends javax.swing.JFrame {
    String path;
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    byte buffer[] = new byte[1024*5];
    public int numq;
    DefaultTableModel tm;
    String cg;
    ArrayList<Collection> items=new ArrayList<Collection>();
    ArrayList<String> st=new ArrayList<String>();
    public Home() {
        initComponents();
        
    }
    
    //SignUp to home
    public Home(Socket s,DataInputStream din,DataOutputStream dout) throws Exception
    {
        
        initComponents();
        this.setVisible(true);
        homeback.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/src/Image/home.png")); 
        this.s=s;
        
        this.din=din;
        this.dout=dout;
        try{
        dout.writeUTF("home");
        }
        catch(Exception e)
        {
            System.out.println("NOt sent");
        }
        this.initcategory();
    }
    
   
    
    // Initializing the Home Table
    public void initcategory() throws Exception
    {
       this.tm = (DefaultTableModel) table.getModel();
       items.clear();
       tm.getDataVector().removeAllElements();
       
       int k=0;
       JLabel [] jl = new JLabel[100];
       
      
       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
       JTableHeader header = table.getTableHeader();
       header.setPreferredSize(new Dimension(100, 32));
       
       //tm.setRowCount(0);
       centerRenderer.setHorizontalAlignment( JLabel.CENTER );
       table.setDefaultRenderer(String.class, centerRenderer);
       table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
       table.getTableHeader().setBackground(Color.GREEN);
       
       try{
            
          numq=Integer.parseInt(din.readUTF());
       }  catch(Exception e)
          {
             System.out.println("number not recieved");
           }
           
           for(int l=0;l<numq;l++){
           
           String str[]=new String[15];
           
           for(int j=0;j<9;j++)
           {
               try{
                   str[j]=din.readUTF();
                   System.out.println(str[j]);
               }
               catch(Exception e)
               {
                   System.out.println("error in reading "+ j);
               }
           }
           // str[0]=category
           // str[1]=brand
           // str[2]=price
           // str[3]=stock
           // str[4]=offer
           // str[5]=seller
           // str[6]=extra
           // str[7]=fzid
           // str[8]=expiry
           
            st.add("Category");
            st.add("Brand");
            st.add("Price");
            st.add("Stock");
            st.add("Offer");
            st.add("Seller");
            st.add("Extra");
            st.add("fzid");
            st.add("Expiry Date");
           
          this.startClient(k);
          str[9]=path;
          items.add(new Collection(str));
          
          jl[k]=new JLabel();
          jl[k].setPreferredSize(new Dimension(150, 150));
          System.out.println("Iamge pahth"+path);
          jl[k].setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(200, 130, Image.SCALE_DEFAULT)));
           
          tm.addRow(new Object[]{str[1],str[3],str[2],jl[k],str[4]});
           
           
            table.getColumn("Image").setCellRenderer(new LabelRenderer());
            table.setRowHeight(150);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
            
            
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            k++;
           
       } 
      
    }
    
    
    // For Showing Images into Column Image
    class LabelRenderer implements TableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
             return (Component)value;//To change body of generated methods, choose Tools | Templates.
        }
        
    }
   
    
    
     // For Getting Image from Server
     public void startClient(int i) throws Exception {
               
         //sleep(2000);
         Socket so=new Socket(IPtoconnect.ip,4321);
         String im=Integer.toString(i);
         im="img"+im+".jpg";
         path="/home/himanshu/NetBeansProjects/FlipZon/images"+im;
          BufferedInputStream in =new BufferedInputStream(so.getInputStream());

          BufferedOutputStream out = 
               new BufferedOutputStream(new FileOutputStream(path));
               
          int len = 0;
          while ((len = in.read(buffer)) > 0) {
               out.write(buffer, 0, len);
               System.out.print("#");
          }
          in.close();
          out.flush();
          out.close();
          System.out.println("\nDone!");
          so.close();
     }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cart = new javax.swing.JLabel();
        log = new javax.swing.JLabel();
        food = new javax.swing.JLabel();
        elec = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        other = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        fash = new javax.swing.JLabel();
        History = new javax.swing.JLabel();
        mob = new javax.swing.JLabel();
        sell = new javax.swing.JLabel();
        homeback = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartMouseClicked(evt);
            }
        });
        getContentPane().add(cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 50, 80, 40));

        log.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logMouseClicked(evt);
            }
        });
        getContentPane().add(log, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 50, 110, 40));

        food.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodMouseClicked(evt);
            }
        });
        getContentPane().add(food, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 280, 60));

        elec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                elecMouseClicked(evt);
            }
        });
        getContentPane().add(elec, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 280, 70));

        home.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        getContentPane().add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 47, 120, 40));

        other.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otherMouseClicked(evt);
            }
        });
        getContentPane().add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 280, 60));

        table.setBackground(new java.awt.Color(252, 251, 242));
        table.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Brand", "Stock", "Price", "Image", "Offer"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 1020, 510));

        fash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fashMouseClicked(evt);
            }
        });
        getContentPane().add(fash, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 280, 60));

        History.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HistoryMouseClicked(evt);
            }
        });
        getContentPane().add(History, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 120, 40));

        mob.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mobMouseClicked(evt);
            }
        });
        getContentPane().add(mob, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 280, 50));

        sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellMouseClicked(evt);
            }
        });
        getContentPane().add(sell, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 140, 40));

        homeback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/home.png"))); // NOI18N
        homeback.setMaximumSize(new java.awt.Dimension(1300, 500));
        homeback.setMinimumSize(new java.awt.Dimension(1300, 500));
        homeback.setPreferredSize(new java.awt.Dimension(1300, 550));
        getContentPane().add(homeback, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    // Moblie category
    private void mobMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobMouseClicked
            
        try {
            
            homeback.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/mob.png"));
            tm.setRowCount(0);
            dout.writeUTF("mobile");
            this.initcategory();
        }   catch (Exception ex) {
            System.out.println("not call to category");
        }
          
        
    }//GEN-LAST:event_mobMouseClicked

    private void elecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elecMouseClicked
        // TODO add your handling code here:
        try {
            
            homeback.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/elec.png"));
            tm.setRowCount(0);
            dout.writeUTF("electronics");
            this.initcategory();
        }   catch (Exception ex) {
            System.out.println("not call to category");
        }
          
    }//GEN-LAST:event_elecMouseClicked

    private void foodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodMouseClicked
        // TODO add your handling code here:
        try {
          
            homeback.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/food.png"));
            tm.setRowCount(0);
            dout.writeUTF("food");
            this.initcategory();
        }   catch (Exception ex) {
            System.out.println("not call to category");
        }
          
    }//GEN-LAST:event_foodMouseClicked

    private void fashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fashMouseClicked
        // TODO add your handling code here: 
       try {
            
            homeback.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/fash.png"));
            tm.setRowCount(0);
            dout.writeUTF("fashion");
            this.initcategory();
        }   catch (Exception ex) {
            System.out.println("not call to category");
        }
          
        
    }//GEN-LAST:event_fashMouseClicked

    private void otherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otherMouseClicked
        // TODO add your handling code here:
        try {
            
            homeback.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/other.png"));
            tm.setRowCount(0);
            dout.writeUTF("other");
            this.initcategory();
        } catch (Exception ex) {
            System.out.println("not call to category");
        }
          
    }//GEN-LAST:event_otherMouseClicked

    private void logMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logMouseClicked
        // TODO add your handling code here:
            this.setVisible(false);
        try {
            s.close();
        }   catch (IOException ex) {
            System.out.println("exception in logging out");
        }
            new SignIn().setVisible(true);
    }//GEN-LAST:event_logMouseClicked

    private void sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellMouseClicked
        // TODO add your handling code herfalsee:
        this.setVisible(false);
        Admin ad = new Admin(s,din,dout);
        
    }//GEN-LAST:event_sellMouseClicked

    private void cartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        Cart c=new Cart(s,din,dout);
        
        
        
        
    }//GEN-LAST:event_cartMouseClicked
 
    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
       
        try{
            homeback.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/src/Image/home.png"));
            tm.setRowCount(0);
            dout.writeUTF("home");
            this.initcategory();
        }   catch(Exception e)
        {
            System.out.println("error in loading home");
        }
        
    }//GEN-LAST:event_homeMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        //DefaultTableModel tm = (DefaultTableModel)table.getModel();
        int row = table.getSelectedRow();
        ProductInfo pf = new ProductInfo(s,din,dout,items.get(row),st);
    }//GEN-LAST:event_tableMouseClicked

    private void HistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryMouseClicked
        
        ClientHistory clienhistory=new ClientHistory(s,din,dout);
        
        
        
    }//GEN-LAST:event_HistoryMouseClicked

  
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel History;
    private javax.swing.JLabel cart;
    private javax.swing.JLabel elec;
    private javax.swing.JLabel fash;
    private javax.swing.JLabel food;
    private javax.swing.JLabel home;
    private javax.swing.JLabel homeback;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel log;
    private javax.swing.JLabel mob;
    private javax.swing.JLabel other;
    private javax.swing.JLabel sell;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
