package CC.com.spring.ex.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "user")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uid;

    @Column
    private String name;

    @Column
    private String pw;

    @Column
    private String dept;

    @Column
    private int authority;
}
