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

    @Column
    private String uid;

    @Column
    private int week;

    @Column
    private String suggest;

    @Column
    private String file;


    public SuggestEntity( String uid, int week, String suggest, String file) {
        this.uid = uid;
        this.week = week;
        this.suggest = suggest;
        this.file = file;
    }
}
