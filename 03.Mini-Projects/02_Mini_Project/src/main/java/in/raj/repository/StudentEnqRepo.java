package in.raj.repository;


import in.raj.entity.StudentEnqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity,Integer> {
}
