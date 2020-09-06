package pl.sda.racing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
  static SessionFactory sessionFactoryForTest = new Configuration().configure().buildSessionFactory();

    @AfterAll
    public static void closingFactory() {
        sessionFactoryForTest.close();
    }
    /*@BeforeAll
    public void cleanDatabaseBeforeTest() {
       Session session= sessionFactoryForTest.openSession();
       session.

    }*/

    @Test
    public void shouldSavePigeon() {
        //given
        Pigeon pigeon = new Pigeon();
        pigeon.setName("Bielik");
        pigeon.setOwner("Janusz");

        //when
        Session session = sessionFactoryForTest.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(pigeon);

        transaction.commit();
        session.close();

        Session querySession = sessionFactoryForTest.openSession();
        CriteriaQuery<Pigeon> cr = querySession.getCriteriaBuilder().createQuery(Pigeon.class);
        Root<Pigeon> root = cr.from(Pigeon.class);


        Query<Pigeon> query = querySession.createQuery(cr);
        List<Pigeon> result = query.getResultList();
        querySession.close();

        //then
        assertEquals(1, result.size());
        assertEquals("Bielik", result.get(0).getName());

    }

    @Test
    void shouldSavePigeonsInDatabase() throws IOException {
        //given
        String filepath = "src/test/resources/pigeon-racing.csv";
        Application application = new Application(sessionFactoryForTest, filepath);
        //when
        application.init();
        //then
        Session querySession = sessionFactoryForTest.openSession();
        CriteriaQuery<Pigeon> cr = querySession.getCriteriaBuilder().createQuery(Pigeon.class);
        Root<Pigeon> root = cr.from(Pigeon.class);

        Query<Pigeon> query = querySession.createQuery(cr);
        List<Pigeon> result = query.getResultList();
        querySession.close();

        assertEquals(400, result.size());

    }
}