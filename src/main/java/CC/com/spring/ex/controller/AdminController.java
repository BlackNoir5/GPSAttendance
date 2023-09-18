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
public class AdminController {
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

    @RequestMapping("logout")
    public String logout() {
        System.out.println("===== User LogOut =====");

        session.invalidate();

        return "index";
    }

    @RequestMapping("/loginCheckA")
    public ModelAndView loginCheckA(HttpServletRequest request, Model model) {
        System.out.println("===== Login Checking =====");

        String uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        System.out.println("===== ID : " + uid + ", PW : " + pw + " =====");

        UserEntity result = userService.getUserById(uid);
        ModelAndView mv;

        if (pw.equals(result.getPw())) {
            if (0 == result.getAuthority()){
                model.addAttribute("message", "관리자 로그인 실패");
                model.addAttribute("Uri", "/admin");
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
            List<UserEntity> users = userService.findByAuthority(0);
            List<AttendEntity> attend = attendService.findByAttendEntityList(users.get(0).getUid());
            model.addAttribute("attend", attend);
            model.addAttribute("id", users.get(0).getUid());
            model.addAttribute("name", users.get(0).getName());
            mv = new ModelAndView("AdminPage/adminuserPage");
        } else {
            System.out.println("===== Login Fail =====");
            System.out.println("===== Page Loading =====");
            model.addAttribute("message", "로그인 실패");
            model.addAttribute("Uri", "/admin");
            mv = new ModelAndView("Common/messageRedirect");
        }
        return mv;
    }

    @RequestMapping(value = "/pwSearchA")
    private String pwSearchA(HttpServletRequest request, Model model){
        String uid = request.getParameter("uid");

        if (userService.existsById(uid)) {
            UserEntity result = userService.getUserById(uid);
            System.out.println("===== ID Loading =====");
            return showMessageAndRedirect("패스워드는 " + result.getPw() + " 입니다.", "/admin", model);
        } else {
            System.out.println("===== Search Fail =====");
            System.out.println("===== Page Loading =====");
            return showMessageAndRedirect("존재하지 않는 아이디입니다.", "/adminPW", model);
        }
    }

    @RequestMapping("searchUser")
    private ModelAndView searchUser(HttpServletRequest request, Model model){
        String uid = request.getParameter("id");
        ModelAndView mv;

        System.out.println("===== Page Loading =====");
        List<AttendEntity> attend = attendService.findByAttendEntityList(uid);
        UserEntity usr = userService.getUserById(uid);
        model.addAttribute("attend", attend);
        model.addAttribute("name", usr.getName());
        mv = new ModelAndView("AdminPage/adminuserPage");
        return mv;
    }

    @RequestMapping("/changeAttend")
    private ModelAndView changeAttend(HttpServletRequest request, Model model) {

        String uid = request.getParameter("uid");
        int week = Integer.parseInt(request.getParameter("week"));
        int attend = Integer.parseInt(request.getParameter("attend"));

        ModelAndView mv;
        int result = attendService.updateByUid(uid, week, attend);

        if (result == 1){
            UserEntity user = userService.getUserById(uid);
            List<AttendEntity> attends = attendService.findByAttendEntityList(uid);
            model.addAttribute("attend", attends);
            model.addAttribute("id", uid);
            model.addAttribute("name", user.getName());
            mv = new ModelAndView("AdminPage/userPage");
        }else {
            model.addAttribute("message", "업데이트 실패");
            model.addAttribute("Uri", "AdminPage/userPage");
            mv = new ModelAndView("Common/messageRedirect");
        }
        return mv;
    }

}
