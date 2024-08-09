package in.raj.service;

import in.raj.binding.DashboardResponse;
import in.raj.binding.EnquiryForm;
import in.raj.binding.EnquirySearchCriteria;

import java.util.List;

public interface EnquiryService {
    public List<String> getCourseNames();

    public List<String> getEnqStatus();

    public DashboardResponse getDashboardData(Integer userId);

    public String addEnquiry(EnquiryForm form);

    public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria);

    public EnquiryForm getEnquiry(Integer enqId);
}
