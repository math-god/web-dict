package mmtr.web.db.repo.base;

import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CrudRepository<TModel extends BaseEntity> {

    private SessionFactory sessionFactory;

    public CrudRepository() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public TModel create(TModel model) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.persist(model);

        session.getTransaction().commit();

        return model;
    }


    public TModel update(TModel model) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.merge(model);

        session.getTransaction().commit();

        return model;
    }


    public TModel getById(UUID id) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        BaseEntity result = session.find(BaseEntity.class, id);

        TModel model = (TModel) result;

        session.getTransaction().commit();

        return model;
    }

    public TModel deleteById(UUID id) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        TModel model = getById(id);
        session.remove(model);

        session.getTransaction().commit();

        return model;
    }
}
