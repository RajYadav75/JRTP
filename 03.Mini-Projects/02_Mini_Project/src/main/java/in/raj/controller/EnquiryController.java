package in.raj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EnquiryController {
    @GetMapping("/dashboard")
    public String dashBoardPage() {
        return "dashboard";
    }

    @GetMapping("/enquiry")
    public String enquiryPage() {
        return "addEnquiry";
    }

    @GetMapping("/enquiries")
    public String viewEnquiryPage() {
        return "viewEnquiry";
    }

}
