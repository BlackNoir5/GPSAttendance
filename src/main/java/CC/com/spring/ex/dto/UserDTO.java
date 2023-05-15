package CC.com.spring.ex.dto;

public class UserDTO {

    private String uid;
    private String pw;
    private String name;
    private boolean authority;

    public UserDTO(String uid, String name, String pw, boolean authority) {
        this.uid = uid;
        this.name = name;
        this.pw = pw;
        this.authority = authority;
    }

    public String getU_id() {
        return uid;
    }

    public void setU_id(String u_id) {
        this.uid = uid;
    }

    public String getU_pw() {
        return pw;
    }

    public void setU_pw(String u_pw) {
        this.pw = u_pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }
}
