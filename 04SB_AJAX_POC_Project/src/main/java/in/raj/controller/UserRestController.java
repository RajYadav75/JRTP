package in.raj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRestController {
    @GetMapping("/msg")
    @ResponseBody
    public String getMsg(@RequestParam("name") String name) {
        String msg = "Hello, " + name;
        return msg;
    }


    @GetMapping("/cmsg")
    @ResponseBody
    public String getDropDownMsg(@RequestParam("cname") String cname) {
        String msg = "I am going to " + cname+ " in next month";
        return msg;
    }


    @GetMapping("/")
    public String load() {
        return "index";
    }
}
