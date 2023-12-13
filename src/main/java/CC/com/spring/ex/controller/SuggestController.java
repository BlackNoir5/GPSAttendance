package CC.com.spring.ex.controller;

import CC.com.spring.ex.Entity.SuggestEntity;
import CC.com.spring.ex.Service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    private String showMessageAndRedirect(String message, String Uri, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("Uri", Uri);
        return "Common/messageRedirect";
    }

    @RequestMapping("/suggest")
    private String suggest(@RequestParam("file")MultipartFile file, HttpServletRequest request, Model model){
        System.out.println("===== suggest start =====");

        String uid = request.getParameter("uid");
        int week = Integer.parseInt(request.getParameter("week"));
        String suggest = request.getParameter("suggest");

        if (file.isEmpty()){
            return showMessageAndRedirect("파일이 없습니다.", "/userPage", model);
        }else {
            String fileName = file.getOriginalFilename();
            String fileFolder = "/home/server1/CAS/build/libs/" + uid + "/" + week;
            String fileRoot = "주차_" + fileName;

            System.out.println(uid + week + suggest + fileName + fileRoot);

            SuggestEntity entity = new SuggestEntity(uid, week, suggest, fileFolder + fileRoot, 0);

            File folder = new File(uid);
            if (!folder.exists()) {
                try{
                    folder.mkdirs();
                    System.out.println("폴더생성");
                }catch (Exception e) {
                    e.getStackTrace();
                }
            }
            File path = new File(fileFolder, fileRoot);
            try{
                file.transferTo(path);
                SuggestEntity result = suggestService.save(entity);
                if(null != result){
                    return  showMessageAndRedirect("신청했습니다.", "/userPage", model);
                }else{
                    return showMessageAndRedirect("신청을 실패했습니다.", "/userPage", model);
                }
            }catch (IllegalStateException | IOException e){
                e.printStackTrace();
                return showMessageAndRedirect("업로드를 실패했습니다.", "/userPage", model);
            }
        }
    }
}
