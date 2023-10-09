package mmtr.web.db.repo.key;

import jakarta.persistence.NoResultException;
import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.security.Key;

@Repository
public class KeyRepositoryImpl implements KeyRepository {
    private SessionFactory sessionFactory;

    public KeyRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public KeyEntity getKeyByName(String name) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        KeyEntity result;
        try {
            result = session.createQuery("from KeyEntity where name like :name",
                    KeyEntity.class).setParameter("name", "%" + name + "%").getSingleResult();
        } catch (NoResultException ex) {
            result = null;
        }

        session.getTransaction().commit();
        session.close();

        return result;
    }
}
