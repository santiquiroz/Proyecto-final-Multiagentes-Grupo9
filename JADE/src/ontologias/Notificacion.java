package ontologias;

import com.sun.mail.smtp.SMTPTransport;
import jade.content.AgentAction;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Notificacion implements AgentAction {
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "santiqupgui@gmail.com";
    private static final String PASSWORD = "19591990san0";

    private static final String EMAIL_FROM = "santiqupgui@gmail.com";
    private static final String EMAIL_TO = "squiroz@unal.edu.co , dospinao@unal.edu.co";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";
   
    
    public void notificar(String para,String asunto, String contenido) {


        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
		
			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

			// cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
            msg.setSubject(EMAIL_SUBJECT);
			
			// content 
            msg.setText(EMAIL_TEXT);
			
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}