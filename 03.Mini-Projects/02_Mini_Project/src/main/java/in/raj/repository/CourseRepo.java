package in.raj.repository;


import in.raj.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<CourseEntity,Integer> {
}
