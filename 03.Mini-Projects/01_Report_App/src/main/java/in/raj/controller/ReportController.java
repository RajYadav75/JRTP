package in.raj.controller;

import in.raj.request.SearchRequest;
import in.raj.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportController {
    @Autowired
    private ReportService service;

    @GetMapping("/")
    public String indexPage(Model model){
        init(model);
        return "index";
    }

    private void init(Model model) {
        model.addAttribute("search", new SearchRequest());
        model.addAttribute("names",service.getPlanNames());
        model.addAttribute("status",service.getPlanStatuses());
    }

    @PostMapping("/search")
    public String handleSearch(SearchRequest request,Model model){
        System.out.println(request);
        init(model);
        return "index";
    }

}
