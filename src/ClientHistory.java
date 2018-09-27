
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ClientHistory extends javax.swing.JFrame {
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    DefaultTableModel tm;
    

    public ClientHistory() {
        initComponents();
    }
    
    public ClientHistory(Socket s,DataInputStream din,DataOutputStream dout) {
        initComponents();
        //contentPane.setBackground(Color.BLUE);
        this.setVisible(true);
        this.s=s;
        this.din=din;
        this.dout=dout;
        try {
            dout.writeUTF("history");
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
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
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
                  
                  
                  tm.addRow(new Object[]{(i+1),str[1],str[3],str[2],str[4],str[11],str[5],Float.toString(totalPriceOfItem)});
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
        } catch (IOException ex) {
            System.out.println("Error in opening");
        }

    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        fromDate = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        toDate = new com.toedter.calendar.JDateChooser();
        from = new javax.swing.JLabel();
        to = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("History");
        setBackground(new java.awt.Color(115, 214, 46));
        setMaximumSize(new java.awt.Dimension(1147, 547));
        setMinimumSize(new java.awt.Dimension(1147, 547));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/giphy .gif"))); // NOI18N

        historyTable.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SNo.", "Brand", "Quantity", "Price", "Discount", "Date", "Seller", "Total Price"
            }
        ));
        jScrollPane1.setViewportView(historyTable);
        if (historyTable.getColumnModel().getColumnCount() > 0) {
            historyTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(228, 143, 30));
        jButton2.setText("Get History");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        from.setBackground(new java.awt.Color(208, 183, 37));
        from.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        from.setForeground(new java.awt.Color(232, 161, 28));
        from.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        from.setText("From  ");

        to.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        to.setForeground(new java.awt.Color(216, 133, 40));
        to.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        to.setText("To");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fromDate, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(toDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fromDate, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(toDate, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            
            if (fromDate.getDate() == null || toDate.getDate()==null) {
              JOptionPane.showMessageDialog(null, "Please Choose Date", "Warning!", JOptionPane.ERROR_MESSAGE);
              return ;
            }
            String fdate=sfd.format(fromDate.getDate()).toString();
            System.out.println(fdate+"fdate");
            String tdate=sfd.format(toDate.getDate()).toString();
            System.out.println(tdate+"tdate");
            
            if(fdate.compareTo(tdate)>0)
            {
                JOptionPane.showMessageDialog(null,"Choose correct dates","Warning!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
             dout.writeUTF("buyertimehistory");
             dout.writeUTF(fdate);
             dout.writeUTF(tdate);
             this.initcategory();
        } catch (Exception ex) {
            System.out.println("Error in sending");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel from;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JTable historyTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel to;
    private com.toedter.calendar.JDateChooser toDate;
    // End of variables declaration//GEN-END:variables
}
