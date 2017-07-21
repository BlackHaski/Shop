package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import shop.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    private static String adminEmail = "nlutsik1@gmail.com";
    private static String adminPass = "N620910222";
    @Autowired
    JavaMailSenderImpl javaMailSender;

    public void send(String username, String email, String password, String message, String realUsername) {
        javaMailSender.setUsername(email);
        javaMailSender.setPassword(password);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessage.setFrom(new InternetAddress(email));
            mimeMessageHelper.setTo("nlutsik1@gmail.com");
            if (realUsername.length() > 0)
                mimeMessageHelper.setText("Hello! My name is - " + username + "!" + "\n" +
                        "My Profile:" + "<a href = \"/cabinet-" + realUsername + "\"<a>" + realUsername + "</a>" + "\n" +
                        "\n" + message, true);
            else
                mimeMessageHelper.setText("Hello! My name is - " + username + "!" + "\n" + message);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendFromAdmin(String email, String message) {
        javaMailSender.setUsername(adminEmail);
        javaMailSender.setPassword(adminPass);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessage.setFrom(new InternetAddress(adminEmail));
            mimeMessageHelper.setTo(email);
            mimeMessage.setText(message);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
