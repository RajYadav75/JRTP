package in.raj.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Setter
@Getter
@Table(name = "COMMENT_TBL")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @Lob
    private String content;
    @CreationTimestamp
    private LocalDate createdOn;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
