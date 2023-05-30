package CC.com.spring.ex.Repository;

import CC.com.spring.ex.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
