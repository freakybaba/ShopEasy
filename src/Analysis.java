
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


public class Analysis extends javax.swing.JFrame {
    Socket s;
    DataInputStream din;
    DataOutputStream dout;

    public Analysis() {
        initComponents();
    }
    
    public Analysis(Socket s,DataInputStream din,DataOutputStream dout) {
        initComponents();
        this.setVisible(true);
        this.s=s;
        this.din=din;
        this.dout=dout;
        
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fromDate = new com.toedter.calendar.JDateChooser();
        toDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Analyse = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Analyse your growth");
        setBackground(java.awt.Color.red);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(237, 148, 48));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("From");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(230, 138, 49));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("To");

        Analyse.setText("Analyse");
        Analyse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnalyseActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/analysis.gif"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(Analyse, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(toDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fromDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(Analyse, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnalyseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnalyseActionPerformed
        
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        if(fromDate.getDate()==null || toDate.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "Fill fields Correctly", "Warning!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String from=sfd.format(fromDate.getDate()).toString();
        String to=sfd.format(toDate.getDate()).toString();
        if(from.compareTo(to)>0)
        {
            JOptionPane.showMessageDialog(null, "Fill choose data correctly", "Warning!", JOptionPane.ERROR_MESSAGE);
            return ;
        }
        try {
            dout.writeUTF("analysis");
            dout.writeUTF(from);
            dout.writeUTF(to);
            float mobile=Float.parseFloat(din.readUTF());
            float electronics=Float.parseFloat(din.readUTF());
            float food=Float.parseFloat(din.readUTF());
            float fashion=Float.parseFloat(din.readUTF());
            float other=Float.parseFloat(din.readUTF());
            
            //System.out.println(mobile+" "+food);
            
            float total=mobile+electronics+food+fashion+other;
            if(total==0)
            {
                JOptionPane.showMessageDialog(null,"No item sold in given period");
                return;
            }
            mobile=(mobile * (float)100)/total;
            electronics=(electronics * (float)100)/total;
            food=(food * (float)100)/total;
            fashion=(fashion * (float)100)/total;
            other=(other * (float)100)/total;
            
            DefaultPieDataset piedata=new DefaultPieDataset();
            piedata.setValue("Mobile",new Float(mobile));
            piedata.setValue("Electronics",new Float(electronics));
            piedata.setValue("Fashion",new Float(fashion));
            piedata.setValue("Other",new Float(other));
            piedata.setValue("Food",new Float(food));
         
            JFreeChart chart=ChartFactory.createPieChart("Pie Chart", piedata,true,true,true);
            PiePlot p = (PiePlot) chart.getPlot();
            ChartFrame frame=new ChartFrame("Pie Chart",chart);
            frame.setVisible(true);
            frame.setSize(450,500);
            
        } catch (IOException ex) {
          
            System.out.println("Error in Analysing");
        }

        
        
    }//GEN-LAST:event_AnalyseActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analysis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Analyse;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.toedter.calendar.JDateChooser toDate;
    // End of variables declaration//GEN-END:variables
}
