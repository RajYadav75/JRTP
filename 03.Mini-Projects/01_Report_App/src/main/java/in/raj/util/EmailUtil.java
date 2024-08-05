package in.raj.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String subject, String body, String to, File f) {
        try {
            MimeMessage mimeMsg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg,true);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setTo("rajyadav@gmail.com");
            // For Attachment
            helper.addAttachment("Plans-Info",f);

            mailSender.send(mimeMsg);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
}