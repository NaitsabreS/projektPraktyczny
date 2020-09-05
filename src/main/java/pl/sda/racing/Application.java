package pl.sda.racing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.List;

public class    Application {
    public static void main(String[] args) {
        Pigeon pigeon = new Pigeon();
        pigeon.setName("Bialy");
        pigeon.setOwner("Janusz");

        SessionFactory sessionFactory= new Configuration()
                .configure(new File("resources/hibernate.cfg.xml")).buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(pigeon);

        transaction.commit();
        session.close();

        Session newSession=sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder=newSession.getCriteriaBuilder();
        CriteriaQuery<Pigeon> cr = criteriaBuilder.createQuery(Pigeon.class);
        Root<Pigeon> root = cr.from(Pigeon.class);
        cr.select(root);

        Query<Pigeon> query = newSession.createQuery(cr);
        List<Pigeon> results = query.getResultList();
        for (Pigeon result : results) {
            System.out.println(result);
        }
        sessionFactory.close();

        System.out.println();
    }
}
