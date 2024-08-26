package in.raj.restcontroller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {
    @Value("${welcomeMsg}")
    private String  wmsg;
    @Value("${greetMsg}")
    private String gmsg;

    @GetMapping("/welcome")
    public String getWelcomeMsg(){
        // return "Welcome To Raj GitHub Repo..";
        return wmsg;
    }
    @GetMapping("/greet")
    public String getGreetMsg(){
        // return "Good Morning";
        return gmsg;
    }
}
