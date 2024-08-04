package in.raj.service;

import in.raj.entity.CitizenPlan;
import in.raj.repository.CitizenPlanRepository;
import in.raj.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private CitizenPlanRepository planrepo;

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
        return null;
    }

    @Override
    public boolean exportExcel() {
        return false;
    }

    @Override
    public boolean exportPdf() {
        return false;
    }
}
