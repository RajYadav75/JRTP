package in.raj.service;

import in.raj.binding.LoginForm;
import in.raj.binding.SignUpForm;
import in.raj.binding.UnlockForm;

public interface UserService {

    public String login(LoginForm form);

    public String signUp(SignUpForm form);

    public String unlockAccount(UnlockForm form);

    public String forgotPwd(String email);
}