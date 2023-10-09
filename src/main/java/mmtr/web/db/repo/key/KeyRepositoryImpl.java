package mmtr.web.db.repo.key;

import jakarta.persistence.NoResultException;
import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.KeyEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class KeyRepositoryImpl implements KeyRepository {
    private SessionFactory sessionFactory;

    public KeyRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<KeyEntity> getKeysByName(String name) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        List<KeyEntity> result;
        try {
            result = session.createQuery("from KeyEntity where name like :name",
                    KeyEntity.class).setParameter("name", "%" + name + "%").getResultList();
        } catch (NoResultException ex) {
            result = null;
        }

        session.getTransaction().commit();
        session.close();

        return result;
    }

    @Override
    public List<KeyEntity> getKeysByTypeId(UUID typeId) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        List<KeyEntity> result;
        try {
            result = session.createQuery("from KeyEntity where typeId = :typeId",
                    KeyEntity.class).setParameter("typeId", typeId).getResultList();
        } catch (NoResultException ex) {
            result = null;
        }

        session.getTransaction().commit();
        session.close();

        return result;
    }
}