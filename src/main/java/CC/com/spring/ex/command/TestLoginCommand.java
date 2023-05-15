package CC.com.spring.ex.command;

import CC.com.spring.ex.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestLoginCommand {

    @Autowired
    private UserDAO dao;

    public int execute(int uid, String pw) {
        System.out.println("===== Test Login Command is Running =====");
        System.out.println("===== ID : " + uid + ", PW : " + pw + " =====");

        int result = dao.userLogin(uid, pw);
        if (1 == result) {
            System.out.println("===== ID is Exists =====");
        } else {
            System.out.println("===== ID is Not Exists =====");
        }

        return result;
    }
}
