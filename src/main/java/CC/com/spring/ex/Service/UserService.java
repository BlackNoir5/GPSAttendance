package CC.com.spring.ex.Service;

import CC.com.spring.ex.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int login(HttpServletRequest request){
        String uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        int result = userRepository.findUser(uid, pw);

        if (1==result) {
            HttpSession session = request.getSession();
            session.setAttribute("uid", uid);
            session.setAttribute("pw", pw);
        }
        return result;
    }

    public void findName(HttpServletRequest request){
        String uid = request.getParameter("uid");
        String result = userRepository.findName(uid);

        if (null != result){
            HttpSession session = request.getSession();
            session.setAttribute("name", result);
        }
    }
}
