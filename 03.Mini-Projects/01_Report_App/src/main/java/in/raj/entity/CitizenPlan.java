package in.raj.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "CITIZEN_PLANS_INFO")
public class CitizenPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citizenId;
    private String citizenName;
    private String gender;
    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Double benefitAmt;
    private String denialReason;
    private LocalDate terminatedDate;
    private String terminationRsn;
}
