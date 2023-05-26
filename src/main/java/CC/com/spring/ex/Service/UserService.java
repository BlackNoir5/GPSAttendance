package CC.com.spring.ex.Service;

import CC.com.spring.ex.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int login(String uid, String pw){
        return userRepository.findUser(uid, pw);
    }
}
