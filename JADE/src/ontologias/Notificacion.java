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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import system.mail.MailSender;

public class Notificacion implements AgentAction {

    public Notificacion (){
        super();
        new MailSender().send("squirozu@unal.edu.co, dospinao@unal.edu.co", "Correo informativo", "Este es un puto correo de prueba.");
    
    }

}

    