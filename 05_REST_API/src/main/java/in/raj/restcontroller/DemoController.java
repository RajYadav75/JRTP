package in.raj.restcontroller;

import in.raj.constant.AppConstant;
import in.raj.propertiesclass.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private AppProperties appProperties;

    @GetMapping("/p1")
    public String getWelcomeMsg(){
        return appProperties.getMessages().get(AppConstant.WELCOME_MSG);
    }
    @GetMapping("/p2")
    public String getGreetMsg(){
        return appProperties.getMessages().get(AppConstant.GREET_MSG);
    }
}
