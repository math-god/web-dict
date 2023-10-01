package mmtr.web.db.repo.base;

import mmtr.web.db.entity.BaseEntity;

import java.util.UUID;

public interface BaseRepository<TModel extends BaseEntity> {
    TModel create(TModel model);

    TModel update(TModel model);

    TModel getById(UUID id);

    TModel deleteById(UUID id);

}
