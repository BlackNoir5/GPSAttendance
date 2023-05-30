package CC.com.spring.ex.controller;

import CC.com.spring.ex.Entity.UserEntity;
import CC.com.spring.ex.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    private String main() { return "index.html";}

    @RequestMapping(value = "/admin")
    private String admin() { return "Login/login.html"; }

    @RequestMapping(value = "/adminPW")
    private String adminPW() { return "PW/findPW.html"; }

    @RequestMapping(value = "/mobile")
    protected String mobile(){
        return "Login/mobileLogin.html";
    }

    @RequestMapping(value = "/mobilePW")
    private String mobilePW() { return "PW/mobileFindPW.html"; }

    @RequestMapping(value = "/write")
    protected String write(){
        return "AdminPage/qrWrite.html";
    }

    @RequestMapping("/loginCheckA")
    public String  loginCheckA(HttpServletRequest request, Model model) {
        System.out.println("===== Login Checking =====");

        String uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        System.out.println("===== ID : " + uid + ", PW : " + pw + " =====");

        UserEntity result = userService.getUserById(uid);

        if (pw.equals(result.getPw())) {
            if (0 == result.getAuthority()){
                return showMessageAndRedirect("관리자 로그인 실패", "/admin", model);
            }
            System.out.println("===== Login Success =====");
            HttpSession session = request.getSession();
            session.setAttribute("uid", result.getUid());
            session.setAttribute("name", result.getName());
            session.setAttribute("pw", result.getPw());
            session.setAttribute("authority", result.getAuthority());
            model.addAttribute("uid", uid);

            System.out.println("===== Page Loading =====");

            return "AdminPage/userPage";
        } else {
            System.out.println("===== Login Fail =====");
            System.out.println("===== Page Loading =====");
            return showMessageAndRedirect("로그인 실패", "/admin", model);
        }
    }

    @RequestMapping("/loginCheckU")
    public String  loginCheckU(HttpServletRequest request, Model model) {
        System.out.println("===== Login Checking =====");

        String uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        System.out.println("===== ID : " + uid + ", PW : " + pw + " =====");

        UserEntity result = userService.getUserById(uid);

        if (pw.equals(result.getPw())) {
            if (1 == result.getAuthority()){
                return showMessageAndRedirect("유저 로그인 실패", "/mobile", model);
            }
            System.out.println("===== Login Success =====");
            HttpSession session = request.getSession();
            session.setAttribute("uid", result.getUid());
            session.setAttribute("name", result.getName());
            session.setAttribute("pw", result.getPw());
            session.setAttribute("authority", result.getAuthority());
            model.addAttribute("uid", uid);

            System.out.println("===== Page Loading =====");
            return "UserPage/mobileUserPage";
        } else {
            System.out.println("===== Login Fail =====");

            System.out.println("===== Page Loading =====");
            return showMessageAndRedirect("로그인 실패", "/mobile", model);
        }
    }

    private String showMessageAndRedirect(String message, String Uri, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("Uri", Uri);
        return "common/messageRedirect";
    }
}
