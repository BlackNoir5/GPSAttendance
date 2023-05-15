package CC.com.spring.ex.dao;

import CC.com.spring.ex.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Mapper
@Service
public class UserDAO {

    @Autowired
    private SqlSession sqlSession;

    // User List
    public List<UserDTO> userList() {
        System.out.println("===== User List DAO =====");
        System.out.println(sqlSession.getConfiguration());
        System.out.println(sqlSession.getConnection());

        System.out.println(sqlSession.selectList("UserMapper.userList"));

        return sqlSession.selectList("UserMapper.userList");
    }

    // User Login
    public int userLogin(String u_id, String u_pw) {
        System.out.println("===== User Login DAO =====");
        System.out.println("===== ID : " + u_id + ", PW : " + u_pw + " =====");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("u_id", u_id);
        map.put("u_pw", u_pw);

        return sqlSession.selectOne("UserMapper.userLogin", map);
    }

    // User SignUp
//    public int userSignup(String uid, String pw, String name, boolean authority) {
//        System.out.println("===== User SignUp DAO =====");
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("uid", uid);
//        map.put("pw", pw);
//        map.put("name", name);
//        map.put("authority", authority);
//
//        return sqlSession.insert("UserMapper.userSignup", map);
//    }
}
