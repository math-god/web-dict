package mmtr.web.db.repo.base;

import mmtr.web.db.HibernateUtil;
import mmtr.web.db.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CrudRepository implements BaseRepository {
    private SessionFactory sessionFactory;

    public CrudRepository() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public <TModel extends BaseEntity> TModel create(TModel model) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.persist(model);

        session.getTransaction().commit();
        session.close();

        return model;
    }

    @Override
    public <TModel extends BaseEntity> TModel update(TModel model) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.merge(model);

        session.getTransaction().commit();
        session.close();

        return model;
    }

    @Override
    public <TModel extends BaseEntity> TModel getById(Class<TModel> modelClass, UUID id) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        TModel model = session.find(modelClass, id);

        session.getTransaction().commit();
        session.close();

        return model;
    }

    @Override
    public <TModel extends BaseEntity> TModel deleteById(Class<TModel> modelClass, UUID id) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        TModel model = getById(modelClass, id);
        session.remove(model);

        session.getTransaction().commit();
        session.close();

        return model;
    }
}
