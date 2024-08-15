package in.raj.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "AIT_ENQURIRY_STATUS")
@Data
public class EnqStatusEntity {
    @Id
    @GeneratedValue
    private Integer statusId;
    private String statusName;
}
