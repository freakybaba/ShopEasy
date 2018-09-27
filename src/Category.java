
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.table.*;


public class Category extends javax.swing.JFrame {
    String path;
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    DefaultTableModel tm;
    int numq;
     ArrayList<Collection> items=new ArrayList<Collection>();
    byte buffer[] = new byte[1024*5];
    public Category() {
        initComponents();
    }
    
    
    public Category(Socket s,DataInputStream din,DataOutputStream dout,String str) throws Exception
    {
       initComponents();
        this.s=s;
        this.tm = (DefaultTableModel) mobtable.getModel();
        this.din=din;
        this.dout=dout;
        try{
        if(str.equals("mobile"))
        {
            dout.writeUTF("mobile");
            categbg.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/mob.png"));
        }
        else if(str.equals("fash"))
        {
            dout.writeUTF("fashion");
            categbg.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/fash.png"));
        }
        else if(str.equals("elec"))
        {
            dout.writeUTF("electronics");
            categbg.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/elec.png"));
        }
        else if(str.equals("food"))
        {
            dout.writeUTF("food");
            categbg.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/food.png"));
        }
        else if(str.equals("other"))
        {
            dout.writeUTF("other");
            categbg.setIcon(new ImageIcon("/home/himanshu/NetBeansProjects/FlipZon/images/other.png"));
        }
        
        }
        catch(Exception e)
        {
            System.out.println("Not Connecting");
        }
        //home.setVisible(false);
        this.setVisible(true);
       
        this.initcategory();
    }

     public void initcategory() throws Exception
    {
       numq=Integer.parseInt(din.readUTF());
       JLabel [] jl = new JLabel[numq+2];
      
       
       
       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
       centerRenderer.setHorizontalAlignment( JLabel.CENTER );
       mobtable.setDefaultRenderer(String.class, centerRenderer);
       mobtable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
       //mobtable.getTableHeader().setBackground(Color.red);
       
       for(int i=0;i<numq;i++)
       {
           String str[]=new String[10];
           
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
           
           
          this.startClient(i);
           //System.out.println("Not get path");
           //System.out.println(path);
            str[9]=path;
            items.add(new Collection(str));
            
            jl[i]=new JLabel();
           jl[i].setPreferredSize(new Dimension(150, 150));
           System.out.println("Iamge pahth"+str[9]);
           jl[i].setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(200, 130, Image.SCALE_DEFAULT)));
           
            tm.addRow(new Object[]{str[2],str[5],str[4],jl[i],str[3]});
           
           
            mobtable.getColumn("Image").setCellRenderer(new LabelRenderer());
            mobtable.setRowHeight(150);
            mobtable.getColumnModel().getColumn(3).setPreferredWidth(100);
            mobtable.getColumnModel().getColumn(0).setPreferredWidth(100);
            mobtable.getColumnModel().getColumn(1).setPreferredWidth(100);
            mobtable.getColumnModel().getColumn(2).setPreferredWidth(100);
            mobtable.getColumnModel().getColumn(4).setPreferredWidth(100);
            
            
            mobtable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            mobtable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            mobtable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            mobtable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
         
           
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

        mobcg = new javax.swing.JLabel();
        eleccg = new javax.swing.JLabel();
        foodcg = new javax.swing.JLabel();
        fashcg = new javax.swing.JLabel();
        othcg = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        cart = new javax.swing.JLabel();
        history = new javax.swing.JLabel();
        homeb = new javax.swing.JLabel();
        table = new javax.swing.JScrollPane();
        mobtable = new javax.swing.JTable();
        categbg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mobcg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mobcgMouseClicked(evt);
            }
        });
        getContentPane().add(mobcg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 270, 60));

        eleccg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eleccgMouseClicked(evt);
            }
        });
        getContentPane().add(eleccg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 270, 60));

        foodcg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodcgMouseClicked(evt);
            }
        });
        getContentPane().add(foodcg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 270, 60));

        fashcg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fashcgMouseClicked(evt);
            }
        });
        getContentPane().add(fashcg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 270, 60));

        othcg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                othcgMouseClicked(evt);
            }
        });
        getContentPane().add(othcg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 270, 60));

        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 50, 120, 40));
        getContentPane().add(cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 50, 90, 40));
        getContentPane().add(history, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 130, 40));

        homeb.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        homeb.setForeground(new java.awt.Color(240, 15, 15));
        homeb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homebMouseClicked(evt);
            }
        });
        getContentPane().add(homeb, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 130, 40));

        mobtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Brand", "Stock", "Price", "Image", "Offer"
            }
        ));
        mobtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mobtableMouseClicked(evt);
            }
        });
        table.setViewportView(mobtable);

        getContentPane().add(table, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 1010, 510));

        categbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/mob.png"))); // NOI18N
        getContentPane().add(categbg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mobcgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobcgMouseClicked
        try {
              System.out.println("call1");
              this.setVisible(false);
            Category c=new Category(s,din,dout,"mobile");
            System.out.println("call");
        } catch (Exception ex) {
            System.out.println("not call to category");
        }
        
    }//GEN-LAST:event_mobcgMouseClicked

    private void homebMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homebMouseClicked
        
        try {
            this.setVisible(false);
            Home home=new Home(s,din,dout);
        } catch (Exception ex) {
            //Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in opening home");
        }
    }//GEN-LAST:event_homebMouseClicked

    private void eleccgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eleccgMouseClicked
        // TODO add your handling code here:
        try {
              System.out.println("call1");
              this.setVisible(false);
            Category c=new Category(s,din,dout,"elec");
            System.out.println("call");
        } catch (Exception ex) {
            System.out.println("not call to category");
        }
    }//GEN-LAST:event_eleccgMouseClicked

    private void foodcgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodcgMouseClicked
        // TODO add your handling code here:
        try {
              System.out.println("call1");
              this.setVisible(false);
            Category c=new Category(s,din,dout,"food");
            System.out.println("call");
        } catch (Exception ex) {
            System.out.println("not call to category");
        }
    }//GEN-LAST:event_foodcgMouseClicked

    private void fashcgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fashcgMouseClicked
        // TODO add your handling code here:
        try {
              System.out.println("call1");
              this.setVisible(false);
            Category c=new Category(s,din,dout,"fash");
            System.out.println("call");
        } catch (Exception ex) {
            System.out.println("not call to category");
        }
    }//GEN-LAST:event_fashcgMouseClicked

    private void othcgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_othcgMouseClicked
        // TODO add your handling code here:
        try {
              System.out.println("call1");
              this.setVisible(false);
            Category c=new Category(s,din,dout,"other");
            System.out.println("call");
        } catch (Exception ex) {
            System.out.println("not call to category");
        }
    }//GEN-LAST:event_othcgMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        try {
            s.close();
        } catch (IOException ex) {
            System.out.println("exception in logging out");
        }
        new SignIn().setVisible(true);
    }//GEN-LAST:event_logoutMouseClicked

    private void mobtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobtableMouseClicked
        
        DefaultTableModel tm = (DefaultTableModel)mobtable.getModel();
        int row = mobtable.getSelectedRow();
        //ProductInfo pf = new ProductInfo(s,din,dout,items.get(row));
        
        
    }//GEN-LAST:event_mobtableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Category().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cart;
    private javax.swing.JLabel categbg;
    private javax.swing.JLabel eleccg;
    private javax.swing.JLabel fashcg;
    private javax.swing.JLabel foodcg;
    private javax.swing.JLabel history;
    private javax.swing.JLabel homeb;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel mobcg;
    private javax.swing.JTable mobtable;
    private javax.swing.JLabel othcg;
    private javax.swing.JScrollPane table;
    // End of variables declaration//GEN-END:variables
}
