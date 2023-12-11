package CC.com.spring.ex.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "times")
@NoArgsConstructor
public class TimeEntity {

    @Id
    private String dept;

    @Column
    private int times;
}
