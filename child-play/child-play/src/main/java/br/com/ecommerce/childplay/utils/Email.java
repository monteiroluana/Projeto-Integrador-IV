package br.com.ecommerce.childplay.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public void generateAndSendEmail(String email, String emailText, String subject) throws AddressException, MessagingException {

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
      
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("email@email.com"));
        generateMailMessage.setSubject(subject);
        generateMailMessage.setContent(emailText, "text/html");
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com","devolution.tads@gmail.com", "d3volution");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
