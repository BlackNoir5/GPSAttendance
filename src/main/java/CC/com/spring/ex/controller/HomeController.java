package CC.com.spring.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @RequestMapping(value = "/mobile")
    protected String test(){
        return "UserPage/mobileLogin.html";
    }

    @RequestMapping(value = "/write")
    protected String write(){
        return "AdminPage/qrWrite.html";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(HttpServletRequest request, Model model) {
        System.out.println("===== Login Checking =====");

        String uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        System.out.println("===== ID : " + uid + ", PW : " + pw + " =====");

        int result = 0;
        if (1 == result) {
            System.out.println("===== Login Success =====");
            model.addAttribute("uid", uid);

            System.out.println("===== Page Loading =====");
            return "testLoginSuccess";
        } else {
            System.out.println("===== Login Fail =====");

            System.out.println("===== Page Loading =====");
            return "testLoginFail";
        }
    }
}
