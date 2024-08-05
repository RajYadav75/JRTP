package in.raj.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String subject, String body, String to) {
        try {
            MimeMessage mimeMsg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setTo("rajyadav@gmail.com");
            // For Attachment
//            helper.addAttachment(to,null);

            mailSender.send(mimeMsg);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
