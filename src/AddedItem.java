
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class AddedItem extends javax.swing.JFrame {
    
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    DefaultTableModel tm;
    ArrayList<Collection> items=new ArrayList<>();
    public AddedItem() {
        initComponents();
    }
    
    
    public AddedItem(Socket s,DataInputStream din,DataOutputStream dout) {
        initComponents();
        this.setVisible(true);
        this.s=s;
        this.din=din;
        this.dout=dout;
        try {
            dout.writeUTF("addeditems");
        } catch (IOException ex) {
            System.out.println("Not recieving history");
        }
        this.initcategory();
    }
    
    public void initcategory()
    {
        items.clear();
        tm=(DefaultTableModel)historyTable.getModel();
        JTableHeader header = historyTable.getTableHeader();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        historyTable.setDefaultRenderer(String.class, centerRenderer);
        historyTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        historyTable.getTableHeader().setBackground(Color.GREEN);
        
        
        header.setPreferredSize(new Dimension(100, 32));
        try {
            int numq=Integer.parseInt(din.readUTF());
            if(numq==0)
            {
                JOptionPane.showMessageDialog(null,"No Item Added");
            }
            else
            {
               for(int i=0;i<numq;i++)
               { 
                  String str[]=new String[10];
                  for(int j=0;j<10;j++)
                  {
                      str[j]=din.readUTF();
                  }
//                  str[0]="categ";
//                  str[1]="brand";
//                  str[2]="price";
//                  str[3]="stock";
//                  str[4]="offer";
//                  str[5]="seller";
//                  str[[11]="buyingdate";
                  items.add(new Collection(str));
                
                  
                  tm.addRow(new Object[]{str[1],str[2],str[4],str[3],str[9]});
                  
                  
                  
            historyTable.setRowHeight(50);
            
            
            
            historyTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
           // historyTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
           // historyTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
                  
                  
                  
               }
            }
        } catch (IOException ex) {
            System.out.println("Error in opening");
        }

    }
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        solditems = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        analysis = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 150, 40));

        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 290, 60));

        solditems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solditemsMouseClicked(evt);
            }
        });
        getContentPane().add(solditems, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 290, 70));

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 157, 290, 70));

        analysis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                analysisMouseClicked(evt);
            }
        });
        getContentPane().add(analysis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 290, 70));

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 290, 70));

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 50, 130, 40));

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Brand", "Price", "Offer", "StocksAvailable", "ExpiryDate"
            }
        ));
        historyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(historyTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 1010, 510));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addeditems.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try {
            this.setVisible(false);
            Home home=new Home(s,din,dout);
        } catch (Exception ex) {
            Logger.getLogger(AddedItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
        this.setVisible(false);
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(AddedItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        new SignIn().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
       
        this.setVisible(false);
        Admin ad=new Admin(s,din,dout);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void historyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyTableMouseClicked
        try {
            int row=historyTable.getSelectedRow();
            ChangeItem changeItem=new ChangeItem(s,din,dout,items.get(row));
            
        } catch (Exception ex) {
            Logger.getLogger(AddedItem.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_historyTableMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.setVisible(false);
        Admin ad=new Admin(s,din,dout);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void solditemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solditemsMouseClicked
        // TODO add your handling code here:
        
        System.out.println("Sold items clicked");
        SoldItemHistory sold=new SoldItemHistory(s,din,dout);    
    }//GEN-LAST:event_solditemsMouseClicked

    private void analysisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_analysisMouseClicked
        // TODO add your handling code here:
        
        Analysis analysis=new Analysis(s,din,dout);
    }//GEN-LAST:event_analysisMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        
        this.setVisible(false);
        AddedItem ad = new AddedItem(s,din,dout);
    }//GEN-LAST:event_jLabel6MouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddedItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel analysis;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel solditems;
    // End of variables declaration//GEN-END:variables
}
