package CC.com.spring.ex.command;

import CC.com.spring.ex.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TestSignUpCommand {

    @Autowired
    private UserDAO dao;

    public int execute(HttpServletRequest request) {
        System.out.println("===== Test SignUp Command is Running =====");

        int uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String authority = phoneFormat(request.getParameter("authority"));

        System.out.println("==========");
        System.out.println("ID : " + uid + ", PW : " + pw + ", Name : " + name + ", auth : " + authority);
        System.out.println("==========");

        int result = dao.userSignup(uid, pw, name, authority);
        if (1 == result) {
            System.out.println("===== Sign Up Success =====");
        } else {
            System.out.println("===== Sign Up Fail =====");
        }

        return result;
    }

    public String phoneFormat(String number) {
        String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
        return number.replaceAll(regEx, "$1-$2-$3");
    }
}
