package pl.sda.racing;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Race {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "race")
    private List<Result> results;
}
