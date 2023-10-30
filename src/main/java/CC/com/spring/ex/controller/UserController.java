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
public class UserController {
    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private AttendService attendService;

    private String showMessageAndRedirect(String message, String Uri, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("Uri", Uri);
        return "common/messageRedirect";
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
            session.setAttribute("uid", result.getUid());
            session.setAttribute("name", result.getName());
            session.setAttribute("pw", result.getPw());
            session.setAttribute("authority", result.getAuthority());
            model.addAttribute("uid", uid);

            System.out.println("===== Page Loading =====");
            List<AttendEntity> attend = attendService.findByAttendEntityList(uid);
            model.addAttribute("attend", attend);
            mv = new ModelAndView("UserPage/mobileCheckAtt");
        } else {
            System.out.println("===== Login Fail =====");

            System.out.println("===== Page Loading =====");
            model.addAttribute("message", "로그인 실패");
            model.addAttribute("Uri", "/mobile");
            mv = new ModelAndView("Common/messageRedirect");
        }
        return mv;
    }

    @RequestMapping(value = "/userPage")
    private ModelAndView userPage(HttpServletRequest request, Model model){
        List<AttendEntity> attend = attendService.findByAttendEntityList(session.getAttribute("uid").toString());
        model.addAttribute("attend", attend);
        ModelAndView mv = new ModelAndView("UserPage/mobileCheckAtt");

        return mv;
    }

    @RequestMapping(value = "/pwSearchU")
    private String pwSearchU(HttpServletRequest request, Model model){
        String uid = request.getParameter("uid");

        if (userService.existsById(uid)) {
            UserEntity result = userService.getUserById(uid);
            System.out.println("===== ID Loading =====");
            return showMessageAndRedirect("패스워드는 " + result.getPw() + " 입니다.", "/mobile", model);
        } else {
            System.out.println("===== Search Fail =====");
            System.out.println("===== Page Loading =====");
            return showMessageAndRedirect("존재하지 않는 아이디입니다.", "/mobilePW", model);
        }
    }

    @RequestMapping("/suggestU")
    private ModelAndView suggestU(HttpServletRequest request, Model model) {
        System.out.println("===== suggest Checking =====");

        String week = request.getParameter("week");
        System.out.println("===== week : " + week + " =====");

        ModelAndView mv = new ModelAndView("mobileSuggest");
        model.addAttribute("week", week);

        return mv;
    }

}
