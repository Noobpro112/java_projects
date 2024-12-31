package sendemail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App {
    public static void main(String[] args) {
        String host = "smtp.gmail.com";  // SMTP do Gmail
        final String sender = "reismatheusyan87@gmail.com";  // Seu e-mail
        final String password = "Sua senha de APP GOOGLE ";  // Senha de aplicativo gerada pelo Google
        String recipient = "reismatheusyan83@gmail.com";  // E-mail do destinatário

        // Configurações para a conexão com o servidor SMTP do Gmail
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");

        // Criando a sessão com autenticação
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);  // Passando e-mail e senha de aplicativo
            }
        });

        try {
            // Criando a mensagem MIME
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));  // E-mail do remetente
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));  // E-mail do destinatário
            message.setSubject("This is Subject");  // Assunto do e-mail
            message.setText("This is a test mail");  // Corpo do e-mail

            // Enviando a mensagem
            Transport.send(message);
            System.out.println("Mail successfully sent");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
