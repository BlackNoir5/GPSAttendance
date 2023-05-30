package CC.com.spring.ex.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "attendance")
@NoArgsConstructor
public class AttendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;

    @ManyToOne
    @JoinColumn(name = "uid")
    private UserEntity user;

    @Column
    private int week;

    @Column
    private Integer attend;
}
