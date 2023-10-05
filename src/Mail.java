import java.security.MessageDigest;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
class Mail implements java.io.Serializable {
    static String mailAdd;
    static String mailpassword;
    public Mail() {
    }
    public void setMailAdd(String mailAdd) {
        Mail.mailAdd = mailAdd;
    }
    public void setMailpassword(String mailpassword) {
        Mail.mailpassword = mailpassword;
    }
    public static void sendmail(String Recepient, String Subject, String Content) throws
            MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String MyEmail = mailAdd;
        String password = mailpassword;
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MyEmail,password);
            }
        });
        Message message = sendingMail(session,MyEmail,Recepient,Subject,Content);
        Transport.send(message);
    }
    private static Message sendingMail(Session session, String MyEmail,String Recepient, String
            Subject,String Txt) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MyEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(Recepient));
        message.setSubject(Subject);
        message.setText(Txt);
        return message;
    }
}