import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class SoldItemHistory extends javax.swing.JFrame {
    
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    DefaultTableModel tm;

    public SoldItemHistory() {
        initComponents();
    }
    
    public SoldItemHistory(Socket s,DataInputStream din,DataOutputStream dout) {
        
        initComponents();
        System.out.println("entered in solditem history");
        this.setVisible(true);
        this.s=s;
        this.din=din;
        this.dout=dout;
        try {
            dout.writeUTF("solditems");
            System.out.println("solditems ---->");
        } catch (IOException ex) {
            System.out.println("Not recieving history");
        }
        this.initcategory();
    }


     public void initcategory()
    {
        
        String str[]=new String[15];
        tm=(DefaultTableModel)historyTable.getModel();
        tm.setRowCount(0);
        
        JTableHeader header = historyTable.getTableHeader();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        historyTable.setDefaultRenderer(String.class, centerRenderer);
        historyTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        historyTable.getTableHeader().setBackground(Color.GREEN);
        
        historyTable.setEnabled(false);
       
        header.setPreferredSize(new Dimension(100, 32));
        try {
            int numq=Integer.parseInt(din.readUTF());
            if(numq==0)
            {
                JOptionPane.showMessageDialog(null,"No Buying History");
            }
            else
            {
                System.out.println(numq);
               for(int i=0;i<numq;i++)
               { 
                  for(int j=0;j<12;j++)
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
                  float price=Float.parseFloat(str[2]);
                  float quantity=Float.parseFloat(str[3]);
                  float discount=Float.parseFloat(str[4]);
                  
                  float costOfOneItem=(float) (price-(price * discount * 0.01));
                  float totalPriceOfItem=(costOfOneItem * quantity);
                  
                  
                  tm.addRow(new Object[]{i+1,str[1],str[3],str[2],str[4],str[11],str[10],Float.toString(totalPriceOfItem)});
                  historyTable.getColumnModel().getColumn(0).setPreferredWidth(15);
                  
                  
            historyTable.setRowHeight(50);
            
            
            
            historyTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
            historyTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
                  
                  
               }
            }
        } catch (Exception ex) {
            System.out.println("Error in opening");
        }

    }
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("History");

        historyTable.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SNo.", "Brand", "Quantity Sold", "Price", "Discount", "Selling Date", "Buyer", "Total Price"
            }
        ));
        jScrollPane1.setViewportView(historyTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SoldItemHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable historyTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
