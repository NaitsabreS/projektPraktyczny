package pl.sda.racing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class PigeonRepository {
   private final SessionFactory sessionFactory;

    public PigeonRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Pigeon> getPigeonByBirdID(String birdId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Pigeon> cr = session
                .getCriteriaBuilder()
                .createQuery(Pigeon.class);
        Root<Pigeon> root = cr.from(Pigeon.class);
        cr.select(root).where(builder.equal(root.get("birdId"), birdId));

        Query<Pigeon> query = session.createQuery(cr);
        return query.getResultStream()
                .findFirst();
    }
}
