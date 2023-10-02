package mmtr.web.db.repo.value;

import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ValueRepositoryImpl extends CrudRepository<ValueEntity> implements ValueRepository {

    private SessionFactory sessionFactory;

    public ValueRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<String> getValuesByKeyId(UUID keyId) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        List<String> result = session.createQuery("from EntryEntity join ValueEntity" +
                " on EntryEntity.valueId = ValueEntity.id where EntryEntity.keyId = :keyId")
                .setParameter("keyId", keyId)
                .getResultList();

        return result;
    }
}
