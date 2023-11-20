package CC.com.spring.ex.Repository;

import CC.com.spring.ex.Entity.SuggestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SuggestRepository extends JpaRepository<SuggestEntity, Integer> {

    SuggestEntity save(SuggestEntity entity);

    List<SuggestEntity> findByProcess(int process);

    SuggestEntity findByNum(int num);

    boolean existsByProcess(int process);

    @Modifying
    @Transactional
    @Query("update suggest set process = ?1 where num = ?2")
    int updateProcess(int process, int num);
}
