package mmtr.web.db.repo.type;

import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.repo.base.BaseRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface TypeRepository extends BaseRepository<TypeEntity> {
    List<TypeEntity> getAllTypes();
}
