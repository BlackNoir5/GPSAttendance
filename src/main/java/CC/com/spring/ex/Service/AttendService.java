package CC.com.spring.ex.Service;

import CC.com.spring.ex.Entity.AttendEntity;
import CC.com.spring.ex.Entity.UserEntity;
import CC.com.spring.ex.Repository.AttendRepository;
import CC.com.spring.ex.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendService {
    @Autowired
    private AttendRepository attendRepository;

    public List<AttendEntity> findByAttendEntityList(String id){ return attendRepository.findByUser_UidOrderByWeek(id);}

}
