package pl.sda.racing.importer;

import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
@Builder
@Getter
public class ResultDTO {
    private Long id;
    private String identifier;
    private Duration time;


}
