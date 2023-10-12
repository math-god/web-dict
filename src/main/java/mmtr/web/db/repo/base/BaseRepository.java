package mmtr.web.db.repo.base;

import mmtr.web.db.entity.BaseEntity;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface BaseRepository {
    <TModel extends BaseEntity> TModel create(TModel model);

    <TModel extends BaseEntity> TModel update(TModel model);

    <TModel extends BaseEntity> TModel getById(Class<TModel> modelClass, UUID id);

    <TModel extends BaseEntity> TModel deleteById(Class<TModel> modelClass, UUID id);
}
