package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {


    @Value("${staff.email}")
    private String expeditor;

    @Value("${staff.password}")
    private String parola;


    private Properties properties = new Properties();
    private Session session;

    public void sendRegisterEmail(String destinatarAdresa, String parola) {
        String subiect = "Credentiale cont Contract Management UTCN";
        String html = "<hr />"+"<h2>Bine ati venit!</h2>"+"<hr />"+
                      "<p>Credentialele dumneavoastra sunt:</p>"+
                      "<p>E-mail: "  +destinatarAdresa+"</p>"+
                      "<p>Parola: "+parola+"</p>";



        setProperties();

        try {
            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(html, "text/html");

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(htmlBodyPart);

            InternetAddress expeditor = new InternetAddress(this.expeditor);
            InternetAddress destinatar = new InternetAddress(destinatarAdresa);

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(expeditor);
            mimeMessage.setSubject(subiect);
            mimeMessage.setRecipient(Message.RecipientType.TO, destinatar);
            mimeMessage.setContent(mimeMultipart);

            Transport.send(mimeMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setProperties() {
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(expeditor, parola);
            }
        });
    }



}
