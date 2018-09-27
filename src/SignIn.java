import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SignIn extends javax.swing.JFrame {
        
   Client client;
   public Socket so;
   Home home;
   public DataInputStream din;
   public DataOutputStream dout;
    
    public SignIn() {
        initComponents();
        //username.setForeground(Color.BLUE);
        //passwd.setForeground(Color.BLUE);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SignUp = new javax.swing.JLabel();
        ForGotPasswd = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        LoginButton = new javax.swing.JLabel();
        passwd = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SignIn");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignUpMouseClicked(evt);
            }
        });
        getContentPane().add(SignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, 80, 20));

        ForGotPasswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ForGotPasswdMouseClicked(evt);
            }
        });
        getContentPane().add(ForGotPasswd, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 120, 20));

        username.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 440, 40));

        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginButtonMouseClicked(evt);
            }
        });
        getContentPane().add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 327, 440, 40));

        passwd.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        passwd.setToolTipText("password");
        getContentPane().add(passwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 277, 440, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/SignIn.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 532));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseClicked
        
         String s="login";
        ArrayList<String> clientdetails=new ArrayList<String>();
        clientdetails.add(s);
        String user,password,emailaddr,conpasswd;
        user=username.getText();
        password=passwd.getText().toString();
        
        clientdetails.add(user);
        clientdetails.add(password);
        
        if(user.equals("") || password.equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please Enter details correctly");
        }
        else
        {
          try {
            client=new Client(IPtoconnect.ip,1234);
        }
          catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error in login");
            return;
        }
          try{
        din=client.dinreturn();
        dout=client.doutreturn();
        so = client.sreturn();
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,"Stream creation failed try again");
              return;
          }
        //System.out.println("Connected");
            for(int i=0;i<clientdetails.size();i++)
            {
                try {
                    dout.writeUTF(clientdetails.get(i));
                } catch (IOException ex) {
                    //Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,"not sending info");
                }
            }

           System.out.println("sent");
           try {
               String status=din.readUTF();
               System.out.println(status);
               if(status.equals("ok"))
               {
                   IPtoconnect.name=user;
                   this.setVisible(false);
                   String value;
                   IPtoconnect.specialOffer=din.readUTF();
                   value=IPtoconnect.specialOffer;
                   
                   if(value.equals("S"))
                   {
                        
                       IPtoconnect.extraDiscount=10;
                       JOptionPane.showMessageDialog(null,"You have special discount of "+IPtoconnect.extraDiscount+"%.");
                   }
                   else if(value.equals("G"))
                   {
                        
                       IPtoconnect.extraDiscount=20;
                       JOptionPane.showMessageDialog(null,"You have special discount of "+IPtoconnect.extraDiscount+"%.");
                       
                   }
                   
                   home=new Home(so,din,dout);
               }
               else
               {
                   //String fg=din.readUTF();
                   JOptionPane.showMessageDialog(null,"invalid credentials");
                   return;
               }

           }  catch (Exception ex) {
              // Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("login error");
           }
        }
        
    }//GEN-LAST:event_LoginButtonMouseClicked

    private void ForGotPasswdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ForGotPasswdMouseClicked
        System.out.println("Forgot Password");
    }//GEN-LAST:event_ForGotPasswdMouseClicked

    private void SignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignUpMouseClicked
        try {
            this.setVisible(false);
            new SignUp().setVisible(true);
        } catch (IOException ex) {
            System.out.println("SignUp opening error");
        }
    }//GEN-LAST:event_SignUpMouseClicked

   
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        String ip=sc.nextLine();
        System.out.println(ip);
        IPtoconnect.ip=ip;
        
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ForGotPasswd;
    private javax.swing.JLabel LoginButton;
    private javax.swing.JLabel SignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField passwd;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
