package in.raj.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "AIT_STUDENT_ENQURIES")
@Data
public class StudentEnqEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enqId;
    private String studentName;
    private Long studentPhno;
    private String classMode;
    private String courseName;
    private String enqStatus;
    @CreationTimestamp
    private LocalDate dateCreated;
    @UpdateTimestamp
    private LocalDate lastUpdated;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private  UserDtlsEntity user;


}
