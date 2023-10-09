package mmtr.web.db.repo.entry;

import mmtr.web.db.entity.BaseEntity;
import mmtr.web.db.entity.EntryEntity;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface EntryRepository {
    List<EntryEntity> getEntriesByTypeId(UUID typeId);
    List<EntryEntity> getEntriesByKeyId(UUID keyId);
    List<EntryEntity> getEntriesByValueId(UUID valueId);
}
