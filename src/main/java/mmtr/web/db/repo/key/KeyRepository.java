package mmtr.web.db.repo.key;

import mmtr.web.db.entity.KeyEntity;

import java.util.List;
import java.util.UUID;

public interface KeyRepository {
    List<KeyEntity> getKeysByName(String name);
    List<KeyEntity> getKeysByTypeId(UUID typeId);
}
