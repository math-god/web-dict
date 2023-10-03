package mmtr.web.db.repo.entry;

import mmtr.web.db.entity.EntryEntity;
import mmtr.web.db.repo.base.BaseRepository;

import java.util.List;

public interface EntryRepository {
    List<EntryEntity> getEntriesByType(String type);

    List<EntryEntity> getEntriesByKey(String key);

    List<EntryEntity> getEntriesByValue(String value);
}
