package in.raj.repository;

import in.raj.entity.UserDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity,Integer> {
}
