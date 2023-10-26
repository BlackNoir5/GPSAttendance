package CC.com.spring.ex.Service;

import CC.com.spring.ex.Entity.SuggestEntity;
import CC.com.spring.ex.Repository.SuggestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestService {
    @Autowired
    private SuggestRepository suggestRepository;

    public SuggestEntity save(SuggestEntity entity) {return suggestRepository.save(entity);}

    public List<SuggestEntity> findByProcess(int process) {return suggestRepository.findByProcess(process);}

    public List<SuggestEntity> findAll() {return suggestRepository.findAll();}

    public SuggestEntity findByNum(int num) {return suggestRepository.findByNum(num);}

    public int updateProcess(int process, int num) {return suggestRepository.updateProcess(process, num);}
}
