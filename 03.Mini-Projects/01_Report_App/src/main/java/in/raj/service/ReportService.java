package in.raj.service;

import in.raj.entity.CitizenPlan;
import in.raj.request.SearchRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportService {
    public List<String> getPlanNames();


    public List<String> getPlanStatuses();


    public List<CitizenPlan> search(SearchRequest request);


    public boolean exportExcel(HttpServletResponse response) throws Exception;


    public boolean exportPdf(HttpServletResponse response) throws Exception;


}
