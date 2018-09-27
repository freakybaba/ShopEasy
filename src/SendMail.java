import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail 
{
static String s1 = "<head>\n" +
"<style>\n" +
"*{\n" +
"	font-size: 5vw;\n" +
"	color: #D5B95B;\n" +
"	box-sizing:border-box;\n" +
"}\n" +
"body{\n" +
"background: #E6E6E6	;\n" +
"}\n" +
".s{\n" +
"	font-weight: bold;\n" +
"	color:black;\n" +
"}\n" +
"#c{\n" +
"	border: 5px solid black;\n" +
"}\n" +
"</style>\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"</head>\n" +
"<body>\n" +
"<div>\n" +
"	Hello <span style=\"color:black\"><span class=\"s\">";
static String s2= "</span></span>\n" +
"	<p id=\"q\">Thanks for shopping at FlipZon</p>\n" +
"	<p>Your checkout details are as follows:</p>\n" +
"\n" +
"	<hr style=\"background:black;height:4px;\">\n" +
"<div id=\"c\">\n" +
"	\n" +
"	<p style=\"color:black\">\n" +
"		<span class=\"s\">Date :</span> ";
static String s3 = "<br>\n" +
"		<span class=\"s\">No. of Items : </span>";
static String s4 = "<br>\n" +
"		<span class=\"s\">Shipping address :</span>";
static String s5 = "<br>\n" +
"		<span class=\"s\">Paid price : </span>";
 static String s6 = "<br>\n" +
"	</p>\n" +
"	<p>\n" +
"\n" +
"	</p></div>\n" +
"	\n" +
"</div>\n" +
"</body>";
    public  static void send(String to, String sub,String date,String address,String item,String price) 
    {
        String msg = s1+IPtoconnect.name+s2+date+s3+item+s4+address+s5+price+s6;
         Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("noreplyFZS@gmail.com", "amitj4056");
            }
          });

        try {

            Message mmessage = new MimeMessage(session);
            mmessage.setContent(msg, "text/html; charset=utf-8");
            mmessage.setFrom(new InternetAddress("noreplyFZS@gmail.com"));
            mmessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            mmessage.setSubject(sub);
            

            Transport.send(mmessage);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
      
        
       
        
    }
}