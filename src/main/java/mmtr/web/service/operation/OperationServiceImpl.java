package mmtr.web.service.operation;

import mmtr.web.common.AddEntryDto;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (resultValueEntity == null)
            return false;

        return true;
    }
}
