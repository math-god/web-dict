package mmtr.web.db.repo.type;

import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.repo.base.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeRepositoryImpl extends CrudRepository<TypeEntity> implements TypeRepository {

    private SessionFactory sessionFactory;

    public TypeRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    @Override
    public List<TypeEntity> getAllTypes() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        List<TypeEntity> types = session.createQuery("from TypeEntity", TypeEntity.class).getResultList();

        return types;
    }
}
