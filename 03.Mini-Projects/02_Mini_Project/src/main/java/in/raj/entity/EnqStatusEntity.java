package in.raj.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AIT_ENQURIRY_STATUS")
@Data
public class EnqStatusEntity {
    @Id
    private Integer statusId;
    private String statusName;
}
