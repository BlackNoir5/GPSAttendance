package CC.com.spring.ex.Repository;

import CC.com.spring.ex.Entity.AttendEntity;
import CC.com.spring.ex.Entity.ExcelEntity;
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

    @Query("SELECT SUM(CASE WHEN attend = 1  then 1 ELSE 0 END) AS attend, COUNT(*) AS all FROM attendance where week = ?1 and user.times.times = ?2")
    StatEntity statisticTime(int week, int times);

    @Query("select user.times.times as times, user.times.dept as dept, user.uid as uid, user.name as name, COUNT(CASE WHEN week = 1 AND attend = 1  then 1 END) AS week1, COUNT(CASE WHEN week = 2 AND attend = 1  then 1 END) AS week2, COUNT(CASE WHEN week = 3 AND attend = 1  then 1 END) AS week3, COUNT(CASE WHEN week = 4 AND attend = 1  then 1 END) AS week4, COUNT(CASE WHEN week = 5 AND attend = 1  then 1 END) AS week5, COUNT(CASE WHEN week = 6 AND attend = 1  then 1 END) AS week6, COUNT(CASE WHEN week = 7 AND attend = 1  then 1 END) AS week7, COUNT(CASE WHEN week = 8 AND attend = 1  then 1 END) AS week8, COUNT(CASE WHEN week = 9 AND attend = 1  then 1 END) AS week9, COUNT(CASE WHEN week = 10 AND attend = 1  then 1 END) AS week10, COUNT(CASE WHEN week = 11 AND attend = 1  then 1 END) AS week11, COUNT(CASE WHEN week = 12 AND attend = 1  then 1 END) AS week12, COUNT(CASE WHEN week = 13 AND attend = 1  then 1 END) AS week13, COUNT(CASE WHEN week = 14 AND attend = 1  then 1 END) AS week14, COUNT(CASE WHEN week = 15 AND attend = 1  then 1 END) AS week15, COUNT(CASE WHEN week = 16 AND attend = 1  then 1 END) AS week16, COUNT(CASE WHEN attend = 0  then 1 END) as result FROM attendance GROUP BY uid")
    List<ExcelEntity> excel();
}
