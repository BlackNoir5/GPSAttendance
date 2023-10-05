package CC.com.spring.ex.Service;

import CC.com.spring.ex.Entity.AttendEntity;
import CC.com.spring.ex.Entity.StatEntity;
import CC.com.spring.ex.Repository.AttendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendService {
    @Autowired
    private AttendRepository attendRepository;

    public List<AttendEntity> findByAttendEntityList(String uid){return attendRepository.findByUser_UidOrderByWeek(uid);}

    public int updateByUid(String uid, int week, int attend) { return attendRepository.updateByUid(uid, week, attend);}

    public StatEntity statisticWeek(int week) { return attendRepository.statisticWeek(week);}

    public StatEntity StatisticTime(int week, int times) { return attendRepository.statisticTime(week, times);}
}
