package CC.com.spring.ex.Service;

import CC.com.spring.ex.Entity.UserEntity;
import CC.com.spring.ex.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(String id){ return userRepository.getReferenceById(id); }
    public Boolean existsById(String id){ return userRepository.existsById(id); }
}
