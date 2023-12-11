package CC.com.spring.ex.Repository;

import CC.com.spring.ex.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByAuthority(int auth);
}
