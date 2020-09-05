package pl.sda.racing;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Race {
    @Id
            @GeneratedValue
     private Long id;


    private  List<Pigeon> pigeons;
}
