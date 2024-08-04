package in.raj.service;

import in.raj.entity.CitizenPlan;
import in.raj.request.SearchRequest;

import java.util.List;

public interface ReportService {
    public List<String> getPlanNames();


    public List<String> getPlanStatuses();


    public List<CitizenPlan> search(SearchRequest request);


    public boolean exportExcel();


    public boolean exportPdf();


}
