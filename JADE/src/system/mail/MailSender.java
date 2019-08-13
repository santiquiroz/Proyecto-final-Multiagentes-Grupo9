/*
 * Esta clase permite enviar correos, se penso originalmente para utilizar Gmail,
 * para usarlo con Gmail se debe de haber autorizado previamente el uso por aplicaciones no seguras
 * en la cuenta a usar y haber ingresado el correo y la clave en el archivo email.cfg del proyecto.
 */
package system.mail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import ontologias.Notificacion;

/**
 *
 * @author santi
 */
public class MailSender {

    private String SMTP_SERVER;
    private String SMTP_SERVER_PORT;

    private String PASSWORD;

    private String EMAIL_FROM;
    private String EMAIL_TO ;
    private String EMAIL_TO_CC;

    private String EMAIL_SUBJECT;
    private String EMAIL_TEXT;

    public MailSender() {
        SMTP_SERVER = "smtp.gmail.com";
        SMTP_SERVER_PORT = "465";
        

        EMAIL_FROM = "";
        EMAIL_TO = "";
        EMAIL_TO_CC = "";

        EMAIL_SUBJECT = "";
        EMAIL_TEXT = "";
        BufferedReader bf;
        try {
            bf = new BufferedReader(new FileReader("configuraciones/mail.cfg"));
            String linea;
            while ((linea = bf.readLine())!=null) {
                String[] parametros = linea.split("=");
                if(parametros[0].equals("correo")){
                    EMAIL_FROM=parametros[1];
                }
                else if(parametros[0].equals("clave")){
                    PASSWORD=parametros[1];
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se encontro el archivo email.cfg");
        } catch (IOException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    

    public void send(String para, String subject, String content) {
        this.EMAIL_TO = para;
        this.EMAIL_SUBJECT = subject;
        
        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", SMTP_SERVER);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", SMTP_SERVER_PORT);
            props.put("mail.debug", "true");
            props.put("mail.smtp.socketFactory.port", SMTP_SERVER_PORT);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            Session session;
            session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_FROM, PASSWORD);
                }
            });

            //session.setDebug(true);
            Transport transport = session.getTransport();
            InternetAddress addressFrom = new InternetAddress(EMAIL_FROM);

            MimeMessage message = new MimeMessage(session);
            message.setSender(addressFrom);
            message.setSubject(subject);
            message.setContent(content, "text/plain");

            InternetAddress[] iAdressArray = InternetAddress.parse(EMAIL_TO);
            int iAdressArraySize = iAdressArray.length;
            for (int i = 0; i < iAdressArraySize; i++) {
                message.addRecipient(Message.RecipientType.TO, iAdressArray[i]);
            }

            transport.connect();
            Transport.send(message);
            transport.close();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
