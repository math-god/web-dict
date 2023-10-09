package mmtr.web.db.repo.entry;

import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.BaseEntity;
import mmtr.web.db.entity.EntryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EntryRepositoryImpl implements EntryRepository {

    private SessionFactory sessionFactory;

    public EntryRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    //TODO Сделать провеку типа словаря
    @Override
    public List<EntryEntity> getEntriesByTypeId(UUID typeId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<EntryEntity> result = session.createQuery("from EntryEntity where typeId = :typeId", EntryEntity.class)
                .setParameter("typeId", typeId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    @Override
    public List<EntryEntity> getEntriesByKeyId(UUID keyId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<EntryEntity> result = session.createQuery("from EntryEntity where keyId = :keyId", EntryEntity.class)
                .setParameter("keyId", keyId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    @Override
    public List<EntryEntity> getEntriesByValueId(UUID valueId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<EntryEntity> result = session.createQuery("from EntryEntity where valueId = :valueId", EntryEntity.class)
                .setParameter("valueId", valueId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return result;
    }
}
