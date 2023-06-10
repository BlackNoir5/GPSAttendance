package CC.com.spring.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


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

    @RequestMapping(value = "/QRPage")
    protected String write(){
        return "AdminPage/qrPage.html";
    }

    @RequestMapping(value = "/Ahead")
    private String aHeader() { return "Header/header.html"; }

    @RequestMapping(value = "/Header")
    private String uHeader() { return "Header/mobileHeader.html"; }

    @RequestMapping(value = "/Footer")
    private String aFooter() { return "Footer/footer.html"; }

    @RequestMapping(value = "/mobileQR")
    private String mobileQR() { return "UserPage/mobileQRPage.html";}
}
