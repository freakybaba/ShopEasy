
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
   public Socket s;
   public DataInputStream din;
   public DataOutputStream dout;
  // private InputStream in;
   public Client(String host,int port) throws IOException
   {
       s = new Socket(host,port);
       dout = new DataOutputStream(s.getOutputStream());
       din = new DataInputStream(s.getInputStream());
   }

    Client() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Socket sreturn()
    {
        return s;
    }
    public DataInputStream dinreturn()
    {
        return din;
    }
    public DataOutputStream doutreturn()
    {
        return dout;
    }
    
}
