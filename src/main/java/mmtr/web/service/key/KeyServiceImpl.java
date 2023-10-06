package mmtr.web.service.key;

import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.repo.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.UUID;

@Service
public class KeyServiceImpl implements KeyService {

    private BaseRepository baseRepository;

    @Autowired
    public KeyServiceImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public KeyEntity addKey(String keyName, UUID typeId) {
        if (!keyName.matches(baseRepository.getById(TypeEntity.class, typeId).getRegex()))
            return null;

        KeyEntity keyEntity = new KeyEntity();
        keyEntity.setName(keyName);
        return baseRepository.create(keyEntity);
    }

    @Deprecated
    @Override
    public KeyEntity editKey(String keyName, UUID keyId) {
       /* if (!keyName.matches(baseRepository.getById(TypeEntity.class, typeId).getRegex()))
            return null;*/

        KeyEntity keyEntity = baseRepository.getById(KeyEntity.class, keyId);

        keyEntity.setName(keyName);

        return baseRepository.update(keyEntity);
    }

    @Override
    public KeyEntity deleteKey(UUID keyId) {
        return baseRepository.deleteById(KeyEntity.class, keyId);
    }
}
