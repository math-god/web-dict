package mmtr.web.db.repo.value;

import jakarta.persistence.NoResultException;
import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.ValueEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ValueRepositoryImpl implements ValueRepository {

    private SessionFactory sessionFactory;

    public ValueRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<ValueEntity> getValuesByKeyId(UUID keyId) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        List<ValueEntity> result = session.createQuery("from ValueEntity where keyId = :keyId", ValueEntity.class)
                .setParameter("keyId", keyId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    @Override
    public List<ValueEntity> getValuesByName(String name) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        List<ValueEntity> result;
        try {
            result = session.createQuery("from ValueEntity where name like :name",
                    ValueEntity.class).setParameter("name", "%" + name + "%").getResultList();
        } catch (NoResultException ex) {
            result = null;
        }

        session.getTransaction().commit();
        session.close();

        return result;
    }
}