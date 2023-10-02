package CC.com.spring.ex.Repository;

import CC.com.spring.ex.Entity.AttendEntity;
import CC.com.spring.ex.Entity.StatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AttendRepository extends JpaRepository<AttendEntity, Integer> {

    List<AttendEntity> findByUser_UidOrderByWeek(String uid);

    @Modifying
    @Transactional
    @Query("update attendance set attend = ?3 where user.uid = ?1 and week = ?2")
    int updateByUid(String uid, int week, int attend);

    @Query("SELECT SUM(CASE WHEN attend = 1  then 1 ELSE 0 END) AS attend, COUNT(*) AS all FROM attendance where week = ?1")
    StatEntity statisticWeek(int week);
}
