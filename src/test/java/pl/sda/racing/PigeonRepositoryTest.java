package pl.sda.racing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.sda.racing.importer.RaceDataReader;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PigeonRepositoryTest {
    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure()
            .buildSessionFactory();
    PigeonRepository pigeonRepository = new PigeonRepository(SESSION_FACTORY);
@BeforeAll
public static void initializeDatabase() throws IOException {
    String filepath = "src/test/resources/pigeon-racing.csv";
    RaceDataReader reader = new RaceDataReader(filepath);
    Session session = SESSION_FACTORY.openSession();
    Transaction transaction = session.beginTransaction();
    reader.getAllPigeons().forEach(session::save);
    transaction.commit();
    session.close();
}
    @Test
    void shouldFindByBirdId() throws IOException {
        //when
        Optional<Pigeon> pigeon = pigeonRepository.getPigeonByBirdID("19633-AU15-FOYS");
        //then
        assertTrue(pigeon.isPresent());
        assertEquals(pigeon.get().getBirdId(), "19633-AU15-FOYS");
    }

    @Test
    void shouldNotFindBirdByID() {
        //when
        Optional<Pigeon> pigeon = pigeonRepository.getPigeonByBirdID("nie_istniejace_id");
        //then
        assertTrue(pigeon.isEmpty());
    }
}