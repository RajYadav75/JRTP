package in.raj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {
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

}
