package mmtr.web.service.value;

import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ValueServiceImpl implements ValueService {
    private BaseRepository baseRepository;

    @Autowired
    public ValueServiceImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public ValueEntity addValue(String valueName) {
        ValueEntity valueEntity = new ValueEntity();
        valueEntity.setName(valueName);
        return baseRepository.create(valueEntity);
    }

    @Override
    public ValueEntity editValue(String valueName, UUID valueId) {
        ValueEntity valueEntity = baseRepository.getById(ValueEntity.class, valueId);
        valueEntity.setName(valueName);
        return baseRepository.update(valueEntity);
    }
}
