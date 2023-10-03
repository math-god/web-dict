package mmtr.web.db.repo.value;

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

        List<ValueEntity> result = session.createQuery("from ValueEntity as value inner join EntryEntity as entry on " +
                        "entry.valueId = value.id where entry.keyId = :keyId", ValueEntity.class)
                .setParameter("keyId", keyId)
                .getResultList();

        return result;
    }
}
