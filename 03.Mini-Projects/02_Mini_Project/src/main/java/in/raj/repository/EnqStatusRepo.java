package in.raj.repository;


import in.raj.entity.EnqStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnqStatusRepo  extends JpaRepository<EnqStatusEntity,Integer> {
}
