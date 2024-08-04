package in.raj.repository;

import in.raj.entity.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {
    @Query("select  distinct (planName) from CitizenPlan ")
    List<String> getPlanNames();

    @Query("select  distinct (planStatus) from CitizenPlan ")
    List<String> getPlanStatus();
}
