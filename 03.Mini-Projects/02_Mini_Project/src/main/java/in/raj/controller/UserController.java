package in.raj.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import in.raj.binding.SignUpForm;
import in.raj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("user",new SignUpForm());
        return "signup";
    }


    @PostMapping("/signup")
    public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model){
        boolean status = userService.signUp(form);
        if (status){
            model.addAttribute("succMsg","Account Created -->  Check your Email");
        }else {
            model.addAttribute("errMsg","Choose Unique Email");
        }
        return "signup";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }




    @GetMapping("/unlock")
    public String unlockPage() {
        return "unlock";
    }

    @GetMapping("/forgot")
    public String forgotPwdPage() {
        return "forgotPwd";
    }
}
