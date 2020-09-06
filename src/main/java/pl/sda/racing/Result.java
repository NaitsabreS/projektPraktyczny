package pl.sda.racing;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Data
public class Result {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Pigeon pigeon;
    @ManyToOne()
    //@JoinColumn(name = "race_id")
    private Race race;

    //private Duration time;



    


}
