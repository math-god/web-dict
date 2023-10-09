package mmtr.web.db.repo.value;

import mmtr.web.db.entity.ValueEntity;

import java.util.List;
import java.util.UUID;

public interface ValueRepository {
    List<ValueEntity> getValuesByKeyId(UUID keyId);
    ValueEntity getValueByName(String name);
}
