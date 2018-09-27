import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SignUp extends javax.swing.JFrame {
   Client client;
   public Socket so;
   Home home;
   public DataInputStream din;
   public DataOutputStream dout;
    
    public SignUp() throws IOException {
        
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        copasswd = new javax.swing.JPasswordField();
        signup = new javax.swing.JLabel();
        signin = new javax.swing.JLabel();
        passwd = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/giphy.gif"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 320, 250));

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 340, 40));

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 340, 40));
        getContentPane().add(copasswd, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 340, 40));

        signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signupMouseClicked(evt);
            }
        });
        getContentPane().add(signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, 340, 30));

        signin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signinMouseClicked(evt);
            }
        });
        getContentPane().add(signin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 600, 400, 40));
        getContentPane().add(passwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 340, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/signup.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 400, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        
    }//GEN-LAST:event_emailActionPerformed

    private void signupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupMouseClicked
      
        String s="signup";
        ArrayList<String> clientdetails=new ArrayList<String>();
        clientdetails.add(s);
        String user,password,emailaddr,conpasswd;
        user=username.getText();
        password=passwd.getText().toString();
        emailaddr=email.getText();
        conpasswd=copasswd.getText().toString();
        clientdetails.add(user);
        clientdetails.add(password);
        clientdetails.add(emailaddr);
        if(user.equals("") || password.equals("") || emailaddr.equals("") || !(password.equals(conpasswd)))
        {
            JOptionPane.showMessageDialog(null,"Please Enter details correctly");
        }
        else
        {
          try {
            client=new Client(IPtoconnect.ip,1234);
        }
          catch (IOException ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        din=client.dinreturn();
        dout=client.doutreturn();
        so = client.sreturn();
        System.out.println("Connected");
            for(int i=0;i<clientdetails.size();i++)
            {
                try {
                    dout.writeUTF(clientdetails.get(i));
                } catch (IOException ex) {
                    Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
           System.out.println("sent");
           
           try {
               String status=din.readUTF();
               System.out.println(status);
               if(status.equals("ok"))
               {
                   this.setVisible(false);
                   String str=din.readUTF();
                   home=new Home(so,din,dout);
               }
               else
               {
                   //String fg=din.readUTF();
                   JOptionPane.showMessageDialog(null,"username exists");
               }

           }  catch (Exception ex) {
              // Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("Sign up error");
           }
        }
        
    }//GEN-LAST:event_signupMouseClicked

    private void signinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signinMouseClicked
        new SignIn().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_signinMouseClicked

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    public static void main(String args[]) throws IOException {
        Scanner sc=new Scanner(System.in);
        String ip=sc.nextLine();
        System.out.println(ip);
        IPtoconnect.ip=ip;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SignUp().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField copasswd;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField passwd;
    private javax.swing.JLabel signin;
    private javax.swing.JLabel signup;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
