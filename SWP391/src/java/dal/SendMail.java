/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author User
 */
public class SendMail {
     public void sendEmail(String email, String code) {
        // Cấu hình các thuộc tính cho phiên gửi email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo phiên gửi email
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
             @Override
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication("quochung28082002@gmail.com", "tukkolhkworoithy");
             }
         });

        try {
            // Tạo nội dung email
            String subject = "Mã xác nhận quên mật khẩu";
            String body = "This is your clinic code " +code +"/n Remember it";
            // Tạo tin nhắn email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(body);
            // Gửi email
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
     
     public static void main(String[] args) {
        SendMail sm = new SendMail();
        sm.sendEmail("vuanhquang0401@gmail.com", "abcd");
    }
}
