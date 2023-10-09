package mmtr.web.service.search;

import mmtr.web.common.DictionaryDto;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;
import mmtr.web.db.repo.key.KeyRepository;
import mmtr.web.db.repo.type.TypeRepository;
import mmtr.web.db.repo.value.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class SearchServiceImpl implements SearchService {

    private BaseRepository baseRepository;
    private KeyRepository keyRepository;
    private ValueRepository valueRepository;
    private TypeRepository typeRepository;


    @Autowired
    public SearchServiceImpl(BaseRepository baseRepository, KeyRepository keyRepository, ValueRepository valueRepository, TypeRepository typeRepository) {
        this.baseRepository = baseRepository;
        this.keyRepository = keyRepository;
        this.valueRepository = valueRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public List<DictionaryDto> getEntriesByValue(UUID typeId, String valueName) {
        List<ValueEntity> valueEntities = valueRepository.getValuesByName(valueName);

        if (valueEntities == null)
            return null;

        List<DictionaryDto> dictionaryDtos = new ArrayList<>();

        for (ValueEntity value : valueEntities) {
            DictionaryDto dictionaryDto = new DictionaryDto();
            HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();

            KeyEntity keyEntity = baseRepository.getById(KeyEntity.class, value.getKeyId());

            dictionaryDto.setTypeEntity(baseRepository.getById(TypeEntity.class, keyEntity.getTypeId()));

            pairs.put(keyEntity, valueRepository.getValuesByKeyId(keyEntity.getId()));
            dictionaryDto.setPairs(pairs);

            dictionaryDtos.add(dictionaryDto);
        }

        if (typeId == null)
            return dictionaryDtos;

        return dictionaryDtos.stream().filter(c -> c.getTypeEntity().getId() == typeId).toList();

    }

    @Override
    public List<DictionaryDto> getEntriesByKey(UUID typeId, String keyName) {
        List<KeyEntity> keyEntities = keyRepository.getKeysByName(keyName);

        if (keyEntities == null)
            return null;

        List<DictionaryDto> dictionaryDtos = new ArrayList<>();

        for (KeyEntity key : keyEntities) {
            DictionaryDto dictionaryDto = new DictionaryDto();
            HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();

            dictionaryDto.setTypeEntity(baseRepository.getById(TypeEntity.class, key.getTypeId()));
            pairs.put(key, valueRepository.getValuesByKeyId(key.getId()));
            dictionaryDto.setPairs(pairs);

            dictionaryDtos.add(dictionaryDto);
        }

        if (typeId == null)
            return dictionaryDtos;

        return dictionaryDtos.stream().filter(c -> c.getTypeEntity().getId() == typeId).toList();
    }

    @Override
    public DictionaryDto getDictionaryByTypeId(UUID typeId) {
        List<KeyEntity> keyEntities = keyRepository.getKeysByTypeId(typeId);
        if (keyEntities == null)
            return null;

        HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();
        for (KeyEntity keyEntity : keyEntities) {
            pairs.put(keyEntity, valueRepository.getValuesByKeyId(keyEntity.getId()));
        }

        DictionaryDto dictionaryDto = new DictionaryDto();

        dictionaryDto.setTypeEntity(baseRepository.getById(TypeEntity.class, typeId));
        dictionaryDto.setPairs(pairs);

        return dictionaryDto;
    }

    @Override
    public List<TypeEntity> getTypes() {
        return typeRepository.getAllTypes();
    }
}
