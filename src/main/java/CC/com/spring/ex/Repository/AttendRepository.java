package CC.com.spring.ex.Repository;

import CC.com.spring.ex.Entity.AttendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendRepository extends JpaRepository<AttendEntity, Integer> {

    List<AttendEntity> findByUser_UidOrderByWeek(String uid);
}
