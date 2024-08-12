package in.raj.controller;


import in.raj.binding.LoginForm;
import in.raj.binding.SignUpForm;
import in.raj.binding.UnlockForm;
import in.raj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("user", new SignUpForm());
        return "signup";
    }


    @PostMapping("/signup")
    public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model) {
        boolean status = userService.signUp(form);
        if (status) {
            model.addAttribute("succMsg", "Account Created -->  Check your Email");
        } else {
            model.addAttribute("errMsg", "Choose Unique Email");
        }
        return "signup";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }


    @GetMapping("/unlock")
    public String unlockPage(@RequestParam String email, Model model) {

        UnlockForm unlockFormObj = new UnlockForm();
        unlockFormObj.setEmail(email);
        model.addAttribute("unlock", unlockFormObj);

        return "unlock";
    }

    @PostMapping("/unlock")
    public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
        System.out.println(unlock);
        if (unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
            boolean status = userService.unlockAccount(unlock);
            if (status) {
                model.addAttribute("succMsg", "Your account unlocked successfully");

            } else {
                model.addAttribute("errMsg", "Given Temporary Password is incorrect, Check your Email");
            }
        } else {
            model.addAttribute("errMsg", "New Pwd and Confirm pwd Should be same");
        }
        return "unlock";
    }

    @GetMapping("/forgot")
    public String forgotPwdPage() {
        return "forgotPwd";
    }
}
