package CC.com.spring.ex.controller;

import CC.com.spring.ex.Entity.SuggestEntity;
import CC.com.spring.ex.Service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    private String showMessageAndRedirect(String message, String Uri, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("Uri", Uri);
        return "common/messageRedirect";
    }

    @RequestMapping("/suggest")
    private String suggest(HttpServletRequest request, Model model){
        System.out.println("===== suggest start =====");

        String uid = request.getParameter("uid");
        int week = Integer.parseInt(request.getParameter("week"));
        String suggest = request.getParameter("suggest");
        String file = request.getParameter("file");

        System.out.println(uid + week + suggest + file);

        SuggestEntity entity = new SuggestEntity(uid, week, suggest, file, 0);

        SuggestEntity result = suggestService.save(entity);
        if(null != result){
            return  showMessageAndRedirect("신청했습니다.", "/userPage", model);
        }else{
            return showMessageAndRedirect("신청을 실패했습니다.", "/userPage", model);
        }
    }
}
