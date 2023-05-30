package CC.com.spring.ex.controller;

import CC.com.spring.ex.Entity.AttendEntity;
import CC.com.spring.ex.Entity.UserEntity;
import CC.com.spring.ex.Service.AttendService;
import CC.com.spring.ex.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class JPAController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendService attendService;

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
    public ModelAndView loginCheckU(HttpServletRequest request, Model model) {
        System.out.println("===== Login Checking =====");

        String uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        System.out.println("===== ID : " + uid + ", PW : " + pw + " =====");

        UserEntity result = userService.getUserById(uid);
        ModelAndView mv;
        if (pw.equals(result.getPw())) {
            if (1 == result.getAuthority()){
                model.addAttribute("message", "유저 로그인 실패");
                model.addAttribute("Uri", "/mobile");
                mv = new ModelAndView("Common/messageRedirect");
                return mv;
            }
            System.out.println("===== Login Success =====");
            HttpSession session = request.getSession();
            session.setAttribute("uid", result.getUid());
            session.setAttribute("name", result.getName());
            session.setAttribute("pw", result.getPw());
            session.setAttribute("authority", result.getAuthority());
            model.addAttribute("uid", uid);

            System.out.println("===== Page Loading =====");
            List<AttendEntity> attend = attendService.findByAttendEntityList(uid);
            model.addAttribute("attend", attend);
            mv = new ModelAndView("UserPage/mobileUserPage");
        } else {
            System.out.println("===== Login Fail =====");

            System.out.println("===== Page Loading =====");
            model.addAttribute("message", "로그인 실패");
            model.addAttribute("Uri", "/mobile");
            mv = new ModelAndView("Common/messageRedirect");
        }
        return mv;
    }

    private String showMessageAndRedirect(String message, String Uri, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("Uri", Uri);
        return "common/messageRedirect";
    }
}