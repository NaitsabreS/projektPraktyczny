package pl.sda.racing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "gołomp")
@Builder
@AllArgsConstructor
public class Pigeon {
@Id
@GeneratedValue
    private Long id;

    private String name;
    private String owner;
    private String birdId;

    public Pigeon() {
    }

    public Pigeon(String name, String birdId, String owner) {
        this.name = name;
        this.birdId = birdId;
        this.owner = owner;
    }
}
