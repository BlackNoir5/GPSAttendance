package CC.com.spring.ex.Repository;

import CC.com.spring.ex.Entity.SuggestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestRepository extends JpaRepository<SuggestEntity, Integer> {

    SuggestEntity save(SuggestEntity entity);
}
