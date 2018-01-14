package ru.shop.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    private final static String from = "";
    private final static String pass = "";
    private final static String user = "";

    public static void send(String to) {
        Properties props = new Properties();
        props.put("server", "smtp.yandex.ru");
        props.put("from", EmailSender.from);
        props.put("user", EmailSender.user);
        props.put("pass", EmailSender.pass);
        props.put("to", to);
        props.put("themas", "test");
        props.put("text", "test");
        props.put("port", "465");

        SendEmail.SMTP_SERVER = props.getProperty("server");
        SendEmail.SMTP_Port = props.getProperty("port");
        SendEmail.EMAIL_FROM = props.getProperty("from");
        SendEmail.SMTP_AUTH_USER = props.getProperty("user");
        SendEmail.SMTP_AUTH_PWD = props.getProperty("pass");
        SendEmail.REPLY_TO = props.getProperty("replyto");


        String emailTo = props.getProperty("to");
        String thema = props.getProperty("thema");
        String text = props.getProperty("text");

        SendEmail se = new SendEmail(emailTo, thema);
        se.sendMessage(text);
        System.out.println("Сообщение отправлено");

    }
}

class SendEmail {
    private Message message = null;
    protected static String SMTP_SERVER = null;
    protected static String SMTP_Port = null;
    protected static String SMTP_AUTH_USER = null;
    protected static String SMTP_AUTH_PWD = null;
    protected static String EMAIL_FROM = null;
    protected static String REPLY_TO = null;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public SendEmail(final String emailTo, final String thema) {
        // Настройка SMTP SSL
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.port", SMTP_Port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        try {
            Authenticator auth = new EmailAuthenticator(SMTP_AUTH_USER,
                    SMTP_AUTH_PWD);
            Session session = Session.getDefaultInstance(properties, auth);
            session.setDebug(false);

            InternetAddress email_from = new InternetAddress(EMAIL_FROM);
            InternetAddress email_to = new InternetAddress(emailTo);
            InternetAddress reply_to = (REPLY_TO != null) ?
                    new InternetAddress(REPLY_TO) : null;
            message = new MimeMessage(session);
            message.setFrom(email_from);
            message.setRecipient(Message.RecipientType.TO, email_to);
            message.setSubject(thema);
            if (reply_to != null)
                message.setReplyTo(new Address[]{reply_to});
        } catch (AddressException e) {
            System.err.println(e.getMessage());
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean sendMessage(final String text) {
        boolean result = false;
        try {
            // Содержимое сообщения
            Multipart mmp = new MimeMultipart();
            // Текст сообщения
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(text, "text/plain; charset=utf-8");
            mmp.addBodyPart(bodyPart);
            // Определение контента сообщения
            message.setContent(mmp);
            // Отправка сообщения
            Transport.send(message);
            result = true;
        } catch (MessagingException e) {
            // Ошибка отправки сообщения
            System.err.println(e.getMessage());
        }
        return result;
    }
}

class EmailAuthenticator extends javax.mail.Authenticator {
    private String login;
    private String password;

    public EmailAuthenticator(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(login, password);
    }
}