package CC.com.spring.ex.controller;

import CC.com.spring.ex.Entity.*;
import CC.com.spring.ex.Repository.SuggestRepository;
import CC.com.spring.ex.Service.AttendService;
import CC.com.spring.ex.Service.SuggestService;
import CC.com.spring.ex.Service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private AttendService attendService;

    @Autowired
    private SuggestService suggestService;

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
            if (0 == result.getAuthority()) {
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
            model.addAttribute("Uri", "/stat");
            mv = new ModelAndView("Common/messageRedirect");
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
    private String pwSearchA(HttpServletRequest request, Model model) {
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

    @RequestMapping("/qrPage.do")
    public ModelAndView qrWrite(HttpServletRequest request, Model model) {
        System.out.println("===== Login Checking =====");

        String uid = request.getParameter("uid");
        String pw = request.getParameter("pw");
        System.out.println("===== ID : " + uid + ", PW : " + pw + " =====");

        UserEntity result = userService.getUserById(uid);
        ModelAndView mv;

        if (pw.equals(result.getPw())) {
            if (0 == result.getAuthority()) {
                model.addAttribute("message", "관리자 로그인 실패");
                model.addAttribute("Uri", "/admin");
                mv = new ModelAndView("Common/messageRedirect");
                return mv;
            }
            System.out.println("===== Login Success =====");
            System.out.println("===== Page Loading =====");
            mv = new ModelAndView("AdminPage/adminqrPage");
        } else {
            System.out.println("===== Login Fail =====");
            System.out.println("===== Page Loading =====");
            model.addAttribute("message", "로그인 실패");
            model.addAttribute("Uri", "/admin");
            mv = new ModelAndView("Common/messageRedirect");
        }
        return mv;
    }

    @RequestMapping("/checkAttend")
    public ModelAndView checkAttend(HttpServletRequest request, Model model) {
        System.out.println("===== Page Loading =====");
        List<UserEntity> users = userService.findByAuthority(0);
        List<AttendEntity> attend = attendService.findByAttendEntityList(users.get(0).getUid());
        model.addAttribute("attend", attend);
        model.addAttribute("id", users.get(0).getUid());
        model.addAttribute("name", users.get(0).getName());
        ModelAndView mv = new ModelAndView("AdminPage/adminuserPage");
        return mv;
    }

    @RequestMapping("/manageSuggest")
    public ModelAndView manageSuggest(HttpServletRequest request, Model model) {
        System.out.println("===== Page Loading =====");
        List<SuggestEntity> suggest = suggestService.findByProcess(0);
        model.addAttribute("suggest", suggest);
        ModelAndView mv = new ModelAndView("AdminPage/manageAtt");
        return mv;
    }

    @RequestMapping("/viewSuggest")
    public ModelAndView viewSuggest(HttpServletRequest request, Model model) {
        System.out.println("===== Page Loading =====");
        int num = Integer.parseInt(request.getParameter("num"));
        SuggestEntity suggest = suggestService.findByNum(num);
        model.addAttribute("suggest", suggest);
        ModelAndView mv = new ModelAndView("AdminPage/viewSuggest");
        return mv;
    }

    @RequestMapping("/searchUser")
    private ModelAndView searchUser(HttpServletRequest request, Model model) {
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

        if (result == 1) {
            UserEntity user = userService.getUserById(uid);
            List<AttendEntity> attends = attendService.findByAttendEntityList(uid);
            model.addAttribute("attend", attends);
            model.addAttribute("id", uid);
            model.addAttribute("name", user.getName());
            mv = new ModelAndView("AdminPage/adminuserPage");
        } else {
            model.addAttribute("message", "업데이트 실패");
            model.addAttribute("Uri", "AdminPage/userPage");
            mv = new ModelAndView("Common/messageRedirect");
        }
        return mv;
    }

    @RequestMapping("/stat")
    private ModelAndView stat(HttpServletRequest request, Model model){

        int week = 1;
        int times = 0;
        StatEntity result = null;

        if (request.getParameter("week") != null){
            week = Integer.parseInt(request.getParameter("week"));
            result = attendService.statisticWeek(week);
        }

        if (request.getParameter("times") != null){
            times = Integer.parseInt(request.getParameter("times"));
            result = attendService.StatisticTime(week, times);
        }

        ModelAndView mv;

        if (null == result) {
            result = attendService.statisticWeek(week);
        }

        model.addAttribute("attend", result.getAttend());
        model.addAttribute("all", result.getAll());
        model.addAttribute("week", week);
        model.addAttribute("times", times);

        mv = new ModelAndView("AdminPage/statisticsPage");
        return mv;
    }

    @RequestMapping("/excel")
    public void excelDownload(HttpServletResponse response) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // Header
        List<String> rows = new ArrayList<String>();
        rows.add("교시");
        rows.add("학과");
        rows.add("학번");
        rows.add("이름");
        for (int i=1; i<=16; i++){
            rows.add(i+"주차");
        }
        rows.add("성적");

        row = sheet.createRow(rowNum++);
        for (int i=0; i<rows.size(); i++){
            cell = row.createCell(i);
            cell.setCellValue(rows.get(i));
        }

        // Body
        List<ExcelEntity> excels = attendService.excel();

        for (int i=0; i<excels.size(); i++) {
            row = sheet.createRow(rowNum++);
            ExcelEntity val = excels.get(i);
            String[] value =
                    {Integer.toString(val.getTimes()),
                    val.getDept(),val.getUid(), val.getName(),
                    val.getWeek1(), val.getWeek2(), val.getWeek3(),
                    val.getWeek4(), val.getWeek5(), val.getWeek6(),
                    val.getWeek7(), val.getWeek8(), val.getWeek9(),
                    val.getWeek10(), val.getWeek11(), val.getWeek12(),
                    val.getWeek13(), val.getWeek14(), val.getWeek15(),
                    val.getWeek16(), val.getResult()};
            for (int j=0; j<4; j++){
                cell = row.createCell(j);
                cell.setCellValue(value[j]);
            }
            for (int j=4; j<20; j++){
                cell = row.createCell(j);
                if (value[j].equals("1")) {
                    cell.setCellValue("O");
                }else {
                    cell.setCellValue("X");
                }
            }
            for (int j=20; j<value.length; j++){
                cell = row.createCell(j);
                if (Integer.parseInt(value[j]) < 3){
                    cell.setCellValue("P");
                }else {
                    cell.setCellValue("NP");
                }
            }

        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}
