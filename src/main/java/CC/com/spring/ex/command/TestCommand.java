package CC.com.spring.ex.command;

import CC.com.spring.ex.dao.UserDAO;
import CC.com.spring.ex.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TestCommand {

    @Autowired
    private UserDAO dao;

    public List<UserDTO> execute(Model model) {
        System.out.println("===== TestCommand is Running =====");

        List<UserDTO> uList = dao.userList();

        if (uList.isEmpty()) {
            System.out.println("===== List is Empty =====");
        }
        System.out.println("===== List is Not Empty =====");
        model.addAttribute("userList", uList);

        return uList;
    }
}
