package CC.com.spring.ex.controller;

import CC.com.spring.ex.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
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

    @RequestMapping("/loginCheck")
    public String  loginCheck(HttpServletRequest request, Model model) {
        System.out.println("===== Login Checking =====");

        String uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        System.out.println("===== ID : " + uid + ", PW : " + pw + " =====");

        int result = userService.login(request);
        if (1 == result) {
            System.out.println("===== Login Success =====");
            userService.findName(request);
            model.addAttribute("uid", uid);

            System.out.println("===== Page Loading =====");
            return "UserPage/userPage";
        } else {
            System.out.println("===== Login Fail =====");

            System.out.println("===== Page Loading =====");
            return "UserPage/test";
        }
    }
}
