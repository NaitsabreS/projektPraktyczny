package pl.sda.racing.importer;

import org.hibernate.SessionFactory;
import pl.sda.racing.Pigeon;
import pl.sda.racing.PigeonRepository;
import pl.sda.racing.Result;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class ResultDTOConverter {
    private final SessionFactory sessionFactory;
    private final PigeonRepository pigeonRepository;

    public ResultDTOConverter(SessionFactory sessionFactory, PigeonRepository pigeonRepository) {
        this.sessionFactory = sessionFactory;
        this.pigeonRepository = pigeonRepository;
    }
    public List<Result> covnertAll() {

    }
    private  Result resultOf (ResultDTO resultDTO) {
        String identifier = resultDTO.getIdentifier();
        Optional<Pigeon> pigeon = pigeonRepository.getPigeonByBirdID(resultDTO.getIdentifier());
        Duration duration = resultDTO.getTime().plus();
        return Result.builder().

    }
}
