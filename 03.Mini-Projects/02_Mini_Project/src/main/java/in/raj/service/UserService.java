package in.raj.service;

import in.raj.binding.LoginForm;
import in.raj.binding.SignUpForm;
import in.raj.binding.UnlockForm;

public interface UserService {

    public String  login(LoginForm form);

    public boolean signUp(SignUpForm form);

    public boolean unlockAccount(UnlockForm form);

    public String forgotPwd(String email);
}