package CC.com.spring.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping(value = "/")
    private String main() { return "UserPage/mobileLogin.html";}

    @RequestMapping(value = "/admin")
    private String admin() { return "AdminPage/login.html"; }


    @RequestMapping(value = "/adminPW")
    private String adminPW() { return "AdminPage/findPW.html"; }

    @RequestMapping(value = "/mobilePW")
    private String mobilePW() { return "UserPage/mobileFindPW.html"; }

    @RequestMapping(value = "/QRPage")
    protected String write(){
        return "AdminPage/adminqrPage.html";
    }

    @RequestMapping(value = "/Ahead")
    private String aHeader() { return "Header/header.html"; }

    @RequestMapping(value = "/Header")
    private String uHeader() { return "Header/mobileHeader.html"; }

    @RequestMapping(value = "/mobileQR")
    private String mobileQR() { return "UserPage/mobileAttendance.html";}

    @RequestMapping(value = "/test")
    private String test() { return "UserPage/ttest.html";}
}
