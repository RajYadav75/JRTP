package in.raj.entity;

import lombok.Data;

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
    private Date dateCreated;
    private Date lastUpdated;
    private  Integer userId;


}
