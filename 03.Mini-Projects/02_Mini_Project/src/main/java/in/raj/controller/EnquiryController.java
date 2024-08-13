package in.raj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class EnquiryController {
    @Autowired
    private HttpSession session;
    @GetMapping("/dashboard")
    public String dashBoardPage() {
//        System.out.println("dashboard method calling ........");
        //TODO -> logic to fetch data for dashboard
        return "dashboard";
    }

    @GetMapping("/enquiry")
    public String enquiryPage() {
        return "addEnquiry";
    }

    @GetMapping("/enquires")
    public String viewEnquiryPage() {
        return "viewEnquiry";
    }
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "index";
    }

}
