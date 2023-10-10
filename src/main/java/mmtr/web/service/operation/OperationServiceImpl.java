package mmtr.web.service.operation;

import mmtr.web.common.AddEntryDto;
import mmtr.web.common.AddValueDto;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OperationServiceImpl implements OperationService {
    private BaseRepository baseRepository;

    @Autowired
    public OperationServiceImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public boolean addEntry(AddEntryDto addEntryDto) {
        TypeEntity typeEntity = baseRepository.getById(TypeEntity.class, addEntryDto.getTypeId());

        if (!addEntryDto.getKey().matches(typeEntity.getRegex()))
            return false;

        KeyEntity keyEntity = new KeyEntity();
        keyEntity.setName(addEntryDto.getKey());
        keyEntity.setTypeId(addEntryDto.getTypeId());

        KeyEntity resultKeyEntity = baseRepository.create(keyEntity);
        if (resultKeyEntity == null)
            return false;

        ValueEntity valueEntity = new ValueEntity();
        valueEntity.setName(addEntryDto.getValue());
        valueEntity.setKeyId(resultKeyEntity.getId());

        ValueEntity resultValueEntity = baseRepository.create(valueEntity);
        return resultValueEntity != null;
    }

    @Override
    public boolean addValue(AddValueDto addValueDto) {
        ValueEntity valueEntity = new ValueEntity();
        valueEntity.setName(addValueDto.getValueName());
        valueEntity.setKeyId(addValueDto.getKeyId());

        ValueEntity resultValueEntity = baseRepository.create(valueEntity);
        return resultValueEntity != null;
    }

    @Override
    public boolean deleteValue(UUID valueId) {
        ValueEntity result = baseRepository.deleteById(ValueEntity.class, valueId);

        return result != null;
    }

    @Override
    public boolean editValue(UUID valueId, String valueName) {
        ValueEntity valueEntity = baseRepository.getById(ValueEntity.class, valueId);
        valueEntity.setName(valueName);

        ValueEntity resultValueEntity = baseRepository.update(valueEntity);

        return resultValueEntity != null;
    }

    @Override
    public boolean editKey(UUID keyId, String keyName) {
        KeyEntity keyEntity = baseRepository.getById(KeyEntity.class, keyId);

        if (!keyName.matches(baseRepository.getById(TypeEntity.class, keyEntity.getTypeId()).getRegex()))
            return false;

        keyEntity.setName(keyName);

        KeyEntity resultKeyEntity = baseRepository.update(keyEntity);

        return resultKeyEntity != null;
    }
}
