package pl.sda.racing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class Application {
    private final SessionFactory sessionFactory;
    private final String filepath;

    public Application(String filepath) {
        this.filepath = filepath;
      sessionFactory=  new Configuration().configure().buildSessionFactory();
    }
//for test only
    Application(SessionFactory sessionFactory, String filepath) {
        this.sessionFactory = sessionFactory;
        this.filepath = filepath;
    }

    public void init() throws IOException {

        RaceDataReader readerFile = new RaceDataReader(filepath);

        List<Pigeon> pigeons = readerFile.getAllPigeons();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        for (Pigeon pigeon : pigeons
        ) {
            session.save(pigeon);
        }
        transaction.commit();
        session.close();
    }

    public static void main(String[] args) throws IOException {
        String filepath = "src/main/resources/hibernate.cfg.xml";
        new Application(filepath).init();
    }
}
