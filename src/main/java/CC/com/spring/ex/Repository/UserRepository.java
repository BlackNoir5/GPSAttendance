package CC.com.spring.ex.Repository;

import CC.com.spring.ex.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query(value = "select count(*) from user where uid = :uid and pw = :pw")
    int findUser(String uid, String pw);

    @Query(value = "select name from user where uid = :uid")
    String findName(String uid);

}
