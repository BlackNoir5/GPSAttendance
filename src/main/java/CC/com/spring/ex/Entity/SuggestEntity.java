package CC.com.spring.ex.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "suggest")
@NoArgsConstructor
public class SuggestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private UserEntity user;

    @Column
    private int week;

    @Column
    private String suggest;

    @Column
    private String file;

    @Column
    private int process;


    public SuggestEntity(String uid, int week, String suggest, String file, int process) {
        this.user = new UserEntity();
        user.setUid(uid);
        this.week = week;
        this.suggest = suggest;
        this.file = file;
        this.process = process;
    }
}
