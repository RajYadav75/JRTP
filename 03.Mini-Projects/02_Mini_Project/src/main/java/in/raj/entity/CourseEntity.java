package in.raj.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "AIT_COURSES")
@Data
public class CourseEntity {
    @Id
    @GeneratedValue
    private Integer courseId;
    private String courseName;
}
