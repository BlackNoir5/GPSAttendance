package CC.com.spring.ex.Service;

import CC.com.spring.ex.Entity.SuggestEntity;
import CC.com.spring.ex.Repository.SuggestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestService {
    @Autowired
    private SuggestRepository suggestRepository;

    public SuggestEntity save(SuggestEntity entity) {return suggestRepository.save(entity);}
}
