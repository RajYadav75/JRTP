package in.raj.service;

import in.raj.entity.CitizenPlan;
import in.raj.repository.CitizenPlanRepository;
import in.raj.request.SearchRequest;
import in.raj.util.EmailUtil;
import in.raj.util.ExcelGenerator;
import in.raj.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private CitizenPlanRepository planrepo;

    @Autowired
    private ExcelGenerator excelGenerator;

    @Autowired
    private PDFGenerator pdfGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public List<String> getPlanNames() {
        return planrepo.getPlanNames();
    }

    @Override
    public List<String> getPlanStatuses() {
        return planrepo.getPlanStatus();

    }

    @Override
    public List<CitizenPlan> search(SearchRequest request) {
        CitizenPlan entity = new CitizenPlan();

        if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
            entity.setPlanName(request.getPlanName());
        }
        if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
            entity.setPlanStatus(request.getPlanStatus());
        }
        if (null != request.getGender() && !"".equals(request.getGender())) {
            entity.setGender(request.getGender());
        }
        if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
            String startDate = request.getStartDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //convert String to LocalDate
            LocalDate localDate = LocalDate.parse(startDate, formatter);
            entity.setPlanStartDate(localDate);
        }
        if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
            String endDate = request.getEndDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //convert String to LocalDate
            LocalDate localDate = LocalDate.parse(endDate, formatter);
            entity.setPlanEndDate(localDate);
        }

        return planrepo.findAll(Example.of(entity));
    }

    @Override
    public boolean exportExcel(HttpServletResponse response) throws Exception {
        File f = new File("Plans.xls");
        List<CitizenPlan> plans = planrepo.findAll();
        excelGenerator.generate(response,plans, f);
        String subject = "Test Mail Subject";
        String body = "<h1>Test Mail Body</h1>";
        String to = "rajyadav@gmail.com";
        emailUtil.sendEmail(subject, body, to,f);
        f.delete();
        return true;
    }

    @Override
    public boolean exportPdf(HttpServletResponse response) throws Exception {
        File f = new File("Plans.pdf");
        List<CitizenPlan> plans = planrepo.findAll();
        pdfGenerator.generate(response,plans,f);
        String subject = "Test Mail Subject";
        String body = "<h1>Test Mail Body</h1>";
        String to = "rajyadav@gmail.com";
        emailUtil.sendEmail(subject, body, to,f);
        f.delete();
        return true;
    }
}
