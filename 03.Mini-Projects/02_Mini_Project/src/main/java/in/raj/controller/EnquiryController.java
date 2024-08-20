package in.raj.controller;

import in.raj.binding.DashboardResponse;
import in.raj.binding.EnquiryForm;
import in.raj.binding.EnquirySearchCriteria;
import in.raj.entity.StudentEnqEntity;
import in.raj.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EnquiryController {
    @Autowired
    private EnquiryService enqService;
    @Autowired
    private HttpSession session;

    @GetMapping("/dashboard")
    public String dashBoardPage(Model model) {
//        System.out.println("dashboard method calling ........");
        //TODO -> logic to fetch data for dashboard
        Integer userId = (Integer) session.getAttribute("userId");
        DashboardResponse dashboardData = enqService.getDashboardData(userId);
        model.addAttribute("dashboardData", dashboardData);

        return "dashboard";
    }

    private void initForm(Model model){
        //TODO -> Get Courses for Drop Down
        List<String> courses = enqService.getCourseNames();
        //TODO -> Get Enquiry Status for Drop Down
        List<String> enqStatuses = enqService.getEnqStatus();
        //TODO -> Create Binding Class Object
        EnquiryForm formObj = new EnquiryForm();
        //TODO -> Set Data in Model Object
        model.addAttribute("courseNames",courses);
        model.addAttribute("statusNames",enqStatuses);
        model.addAttribute("formObj",formObj);
    }
    @GetMapping("/enquiry")
    public String addEnquiryPage(Model model) {
        initForm(model);
        return "addEnquiry";
    }
    @PostMapping("/addEnq")
    public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model){
        System.out.println(formObj);
        // Todo-> Save the Data
        boolean status = enqService.upsertEnquiry(formObj);
        if (status){
            model.addAttribute("succMsg","Enquiry Added");
        }else {
            model.addAttribute("errMsg","Problem Occured");
        }

        return "addEnquiry";
    }

    @GetMapping("/enquires")
    public String viewEnquiryPage(Model model) {
        initForm(model);
        List<StudentEnqEntity> enquiries = enqService.getEnquiries();
        model.addAttribute("enquiries",enquiries);
        return "viewEnquiry";
    }
@   GetMapping("/filterEnquiries")
    public String getFilteredEnqs(@RequestParam String cname,@RequestParam String status,@RequestParam String mode, Model model){
        EnquirySearchCriteria criteria = new EnquirySearchCriteria();
        criteria.setCourseName(cname);
        criteria.setClassMode(mode);
        criteria.setEnqStatus(status);

        Integer userId = (Integer) session.getAttribute("userId");
        List<StudentEnqEntity> filteredEnqs = enqService.getFilteredEnqs(criteria, userId);
        model.addAttribute("enquiries",filteredEnqs);
        return "filterEnquiry";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "index";
    }

}
