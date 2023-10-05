package mmtr.web.service.key;

import mmtr.web.db.entity.KeyEntity;

import java.util.UUID;

public interface KeyService {
    KeyEntity addKey(String keyName, UUID typeId);

    KeyEntity editKey(String keyName, UUID keyId);
}
