package mmtr.web.db.repo.value;

import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ValueRepository extends BaseRepository<ValueEntity> {
    List<String> getValuesByKeyId(UUID keyId);
}
