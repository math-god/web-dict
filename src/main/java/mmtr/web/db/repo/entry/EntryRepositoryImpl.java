package mmtr.web.db.repo.entry;

import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.EntryEntity;
import mmtr.web.db.repo.base.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Repository
public class EntryRepositoryImpl extends CrudRepository<EntryEntity> implements EntryRepository {

    private SessionFactory sessionFactory;

    public EntryRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<EntryEntity> getEntriesByType(String type) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        UUID typeId = (UUID) session.createQuery("select id from TypeEntity where name = :type")
                .setParameter("type", type)
                .getSingleResult();

        List<EntryEntity> result = session.createQuery("from EntryEntity where typeId = :typeId", EntryEntity.class)
                .setParameter("typeId", typeId)
                .getResultList();

        return result;
    }

    @Override
    public List<EntryEntity> getEntriesByKey(String key) {
        return null;
    }

    @Override
    public List<EntryEntity> getEntriesByValue(String value) {
        return null;
    }
}
