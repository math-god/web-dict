package mmtr.web.db.repo.key;

import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;

public interface KeyRepository {
    KeyEntity getKeyByName(String name);
}
