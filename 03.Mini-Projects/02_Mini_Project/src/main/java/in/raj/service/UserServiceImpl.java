package in.raj.service;

import in.raj.binding.LoginForm;
import in.raj.binding.SignUpForm;
import in.raj.binding.UnlockForm;
import in.raj.entity.UserDtlsEntity;
import in.raj.repository.UserDtlsRepo;
import in.raj.util.EmailUtils;
import in.raj.util.PwdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDtlsRepo userDtlsRepo;
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private HttpSession session;

    @Override
    public String login(LoginForm form) {
        UserDtlsEntity entity =
                userDtlsRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
        if (entity == null) {
            return "Invalid Credentials";
        }
        if (entity.getAccStatus().equals("LOCKED")) {
            return "Your Account Locked";
        }
        //Todo -> create session and store user data in session
        session.setAttribute("userId",entity.getUserId());
        return "success";
    }

    @Override
    public boolean signUp(SignUpForm form) {
        UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());
        if (user != null) {
            return false;
        }

        //TODO -> Copy data from binding obj to entity obj
        UserDtlsEntity entity = new UserDtlsEntity();
        BeanUtils.copyProperties(form, entity);


        //TODO -> Generate Random Password and set to object
        String tempPwd = PwdUtils.generateRandomPwd();
        entity.setPwd(tempPwd);

        //TODO -> Set Account status as LOCKED
        entity.setAccStatus("LOCKED");


        //TODO -> Insert Record
        userDtlsRepo.save(entity);

        // TODO -> Send Email to unlock the Account
        String to = form.getEmail();
        String subject = "Unlock Your Account | Raj | JRTP";
        StringBuffer body = new StringBuffer("");
        body.append("<h1>Use below temporary password to unlock your account </h1>");
        body.append("Temporary Password -> " + tempPwd);
        body.append("<br>");
        body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">Click Here To Unlock Your Account</a>");

        emailUtils.sendEmail(to, subject, body.toString());

        return true;
    }

    @Override
    public boolean unlockAccount(UnlockForm form) {
        UserDtlsEntity entity = userDtlsRepo.findByEmail(form.getEmail());
        if (entity.getPwd().equals(form.getTempPwd())) {
            entity.setPwd(form.getNewPwd());
            entity.setAccStatus("UNLOCKED");
            userDtlsRepo.save(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean forgotPwd(String email) {
        // TODO -> Check record presence in DB with given email
        UserDtlsEntity entity = userDtlsRepo.findByEmail(email);

        // TODO -> If record not available send error msg
        if(entity == null){
            return false;
        }

        // TODO -> If record available send pwd to email and send success msg
        String Subject = "Recover Password";
        String body = "Your Password :: "+entity.getPwd();
        emailUtils.sendEmail(email,Subject,body);
        return true;
    }
}
