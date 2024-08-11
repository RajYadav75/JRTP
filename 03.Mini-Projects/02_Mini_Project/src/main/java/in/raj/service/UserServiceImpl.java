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

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDtlsRepo userDtlsRepo;
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public String login(LoginForm form) {
        return "";
    }

    @Override
    public boolean signUp(SignUpForm form) {
        UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());
        if (user != null){
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
        return false;
    }

    @Override
    public String forgotPwd(String email) {
        return "";
    }
}
