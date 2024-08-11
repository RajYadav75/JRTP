package in.raj.service;

import in.raj.binding.LoginForm;
import in.raj.binding.SignUpForm;
import in.raj.binding.UnlockForm;
import in.raj.util.PwdUtils;

public class UserServiceImpl implements UserService{
    @Override
    public String login(LoginForm form) {
        return "";
    }

    @Override
    public boolean signUp(SignUpForm form) {
        //TODO -> Copy data from binding obj to entity obj

        //TODO -> Generate Random Password and set to object
        String tempPwd = PwdUtils.generateRandomPwd();


        //TODO-> Set Account status as LOCKED





        //TODO-> Insert Record




        // TODO-> Send email to unlock the account





        return false;
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
