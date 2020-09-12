package pl.sda.racing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    @Id
    @GeneratedValue
        private Long id;

        @ManyToOne
        private Pigeon pigeon;
        @ManyToOne()
        //@JoinColumn(name = "race_id")
        private Race race;

        private Duration time;


}
