package in.raj.service;

import in.raj.binding.DashboardResponse;
import in.raj.binding.EnquiryForm;
import in.raj.binding.EnquirySearchCriteria;
import in.raj.entity.StudentEnqEntity;

import java.util.List;

public interface EnquiryService {
    public List<String> getCourseNames();

    public List<String> getEnqStatus();

    public DashboardResponse getDashboardData(Integer userId);

    public boolean upsertEnquiry(EnquiryForm form);

    public List<StudentEnqEntity> getEnquiries();

    public EnquiryForm getEnquiry(Integer enqId);
    public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria,Integer userId);
}
