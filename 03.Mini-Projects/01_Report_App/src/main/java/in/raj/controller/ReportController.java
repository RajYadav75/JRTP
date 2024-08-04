package in.raj.controller;

import in.raj.entity.CitizenPlan;
import in.raj.request.SearchRequest;
import in.raj.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    private ReportService service;

    @GetMapping("/excel")
    public void excelExport(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition","attachment;filename=plans.xls");
        service.exportExcel(response);
    }


    @GetMapping("/pdf")
    public void pdfExport(HttpServletResponse response) throws Exception{
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition","attachment;filename=plans.pdf");
        service.exportPdf(response);
    }


    @GetMapping("/")
    public String indexPage(Model model){
        model.addAttribute("search", new SearchRequest());
        init(model);
        return "index";
    }

    private void init(Model model) {
        model.addAttribute("names",service.getPlanNames());
        model.addAttribute("status",service.getPlanStatuses());
    }

    @PostMapping("/search")
    public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model){
        System.out.println(search);
        List<CitizenPlan> plans = service.search(search);
        model.addAttribute("plans", plans);
        init(model);
        return "index";
    }

}
