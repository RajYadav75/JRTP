package in.raj.service;

import in.raj.binding.DashboardResponse;
import in.raj.binding.EnquiryForm;
import in.raj.binding.EnquirySearchCriteria;
import in.raj.constants.AppConstant;
import in.raj.entity.CourseEntity;
import in.raj.entity.EnqStatusEntity;
import in.raj.entity.StudentEnqEntity;
import in.raj.entity.UserDtlsEntity;
import in.raj.repository.CourseRepo;
import in.raj.repository.EnqStatusRepo;
import in.raj.repository.StudentEnqRepo;
import in.raj.repository.UserDtlsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService {


    @Autowired
    private UserDtlsRepo userDtlsRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EnqStatusRepo statusRepo;

    @Autowired
    private StudentEnqRepo enqRepo;

    @Autowired
    private HttpSession session;


    @Override
    public List<String> getCourseNames() {
        List<CourseEntity> findAll = courseRepo.findAll();
        List<String> names = new ArrayList<>();
        for (CourseEntity entity : findAll) {
            names.add(entity.getCourseName());
        }
        return names;
    }

    @Override
    public List<String> getEnqStatus() {
        List<EnqStatusEntity> findAll = statusRepo.findAll();
        List<String> statusList = new ArrayList<>();
        for (EnqStatusEntity entity : findAll){
            statusList.add(entity.getStatusName());
        }
        return statusList;
    }

    @Override
    public DashboardResponse getDashboardData(Integer userId) {
        DashboardResponse response = new DashboardResponse();
        Optional<UserDtlsEntity> findById =
                userDtlsRepo.findById(userId);
        if (findById.isPresent()) {
            UserDtlsEntity userEntity = findById.get();
            List<StudentEnqEntity> enquiries = userEntity.getEnquiries();
            Integer totalCnt = enquiries.size();

            Integer enrolledCnt = enquiries.stream()
                    .filter(e -> e.getEnqStatus().equals("Enrolled"))
                    .collect(Collectors.toList()).size();

            Integer lostCnt = enquiries.stream()
                    .filter(e -> e.getEnqStatus().equals("Lost"))
                    .collect(Collectors.toList()).size();

            response.setTotalEnquiriesCnt(totalCnt);
            response.setEnrolledCnt(enrolledCnt);
            response.setLostCnt(lostCnt);
        }
        return response;
    }

    @Override
    public boolean upsertEnquiry(EnquiryForm form) {
        StudentEnqEntity enqEntity = new StudentEnqEntity();
        BeanUtils.copyProperties(form,enqEntity);
        Integer userId = (Integer) session.getAttribute(AppConstant.STR_USER_ID);
        UserDtlsEntity userEntity = userDtlsRepo.findById(userId).get();
        enqEntity.setUser(userEntity);
        enqRepo.save(enqEntity);
        return true;
    }

    @Override
    public List<StudentEnqEntity> getEnquiries() {
        Integer userId = (Integer) session.getAttribute(AppConstant.STR_USER_ID);
        Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
        if (findById.isPresent()){
            UserDtlsEntity userDtlsEntity = findById.get();
            List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
            return enquiries;
        }
        return null;
    }

    @Override
    public EnquiryForm getEnquiry(Integer enqId) {
        return null;
    }

    @Override
    public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {
        Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
        if (findById.isPresent()){
            UserDtlsEntity userDtlsEntity = findById.get();
            List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
            // TODO -> Filter Logic
            if (null != criteria.getCourseName() & !"".equals(criteria.getCourseName())){
                enquiries = enquiries.stream()
                        .filter(e -> e.getCourseName().equals(criteria.getCourseName()))
                        .collect(Collectors.toList());
            }
            if (null != criteria.getEnqStatus() & !"".equals(criteria.getEnqStatus())){
                enquiries = enquiries.stream()
                        .filter(e->e.getEnqStatus().equals(criteria.getEnqStatus()))
                        .collect(Collectors.toList());
            }

            if (null != criteria.getClassMode() & !"".equals(criteria.getClassMode())){
                enquiries = enquiries.stream()
                        .filter(e->e.getClassMode().equals(criteria.getClassMode()))
                        .collect(Collectors.toList());
            }
            return enquiries;
        }
        return null;
    }
}
