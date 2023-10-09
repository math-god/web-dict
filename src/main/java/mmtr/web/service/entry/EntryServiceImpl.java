/*
package mmtr.web.service.entry;

import mmtr.web.common.AddEntryDto;
import mmtr.web.common.DictionaryDto;
import mmtr.web.common.SearchDto;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;
import mmtr.web.db.repo.key.KeyRepository;
import mmtr.web.db.repo.type.TypeRepository;
import mmtr.web.db.repo.value.ValueRepository;
import mmtr.web.service.key.KeyService;
import mmtr.web.service.value.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EntryServiceImpl implements EntryService {
    private ValueRepository valueRepository;
    private BaseRepository baseRepository;
    private TypeRepository typeRepository;
    private KeyRepository keyRepository;
    private KeyService keyService;
    private ValueService valueService;

    @Autowired
    public EntryServiceImpl(ValueRepository valueRepository, BaseRepository baseRepository, TypeRepository typeRepository, KeyRepository keyRepository, KeyService keyService, ValueService valueService) {
        this.valueRepository = valueRepository;
        this.baseRepository = baseRepository;
        this.typeRepository = typeRepository;
        this.keyRepository = keyRepository;
        this.keyService = keyService;
        this.valueService = valueService;
    }

    @Override
    public DictionaryDto getEntriesByTypeId(UUID typeId) {
        List<EntryEntity> entryEntities = entryRepository.getEntriesByTypeId(typeId);

        if (entryEntities == null)
            return null;

        HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();
        for (EntryEntity entryEntity : entryEntities) {
            pairs.put(baseRepository.getById(KeyEntity.class, entryEntity.getKeyId()),
                    valueRepository.getValuesByKeyId(entryEntity.getKeyId()));
        }

        DictionaryDto dictionaryDto = new DictionaryDto();

        dictionaryDto.setTypeEntity(baseRepository.getById(TypeEntity.class, typeId));
        dictionaryDto.setPairs(pairs);

        return dictionaryDto;
    }

    @Override
    public DictionaryDto getEntriesByKeyIdAndTypeId(UUID keyId, UUID typeId) {
        DictionaryDto dictionaryDto = getEntriesByTypeId(typeId);

        HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();
        for (Map.Entry<KeyEntity, List<ValueEntity>> pair : dictionaryDto.getPairs().entrySet()) {
            if (pair.getKey().getId().equals(keyId))
                pairs.put(pair.getKey(), pair.getValue());
        }

        DictionaryDto result = new DictionaryDto();
        result.setTypeEntity(dictionaryDto.getTypeEntity());
        result.setPairs(pairs);

        return result;
    }

    @Override
    public List<DictionaryDto> getEntriesByKeyId(UUID keyId) {
        return null;
    }

    @Override
    public DictionaryDto getEntriesByValueIdAndTypeId(UUID valueId, UUID typeId) {
        return null;
    }

    @Override
    public List<DictionaryDto> getEntriesByValueId(UUID valueId) {
        return null;
    }

    @Override
    public boolean addEntry(AddEntryDto addEntryDto) {
        KeyEntity keyEntity = new KeyEntity();
        keyEntity.setName(addEntryDto.getKey());
        keyEntity.setTypeId(addEntryDto.getTypeId());

        KeyEntity resultKeyEntity = baseRepository.create(keyEntity);
        if (resultKeyEntity == null)
            return false;



        return true;
    }

}

    @Override
    public List<DictionaryDto> getEntriesByFilter(SearchDto searchDto) {
        List<DictionaryDto> dictionaryDtos = new ArrayList<>();
        if (searchDto.getKindOfInput().equals("value")) {
            ValueEntity valueEntity = valueRepository.getValueByName(searchDto.getInput());
            if (valueEntity == null)
                return null;

            List<EntryEntity> entryEntities = entryRepository.getEntriesByValueId(valueEntity.getId());

            for (EntryEntity entryEntity : entryEntities) {
                dictionaryDtos.add(getEntriesDtoFromEntryEntities(entryEntity));
            }
        }

        if (searchDto.getKindOfInput().equals("key")) {
            KeyEntity keyEntity = keyRepository.getKeysByName(searchDto.getInput());
            if (keyEntity == null)
                return null;

            List<EntryEntity> entryEntities = entryRepository.getEntriesByKeyId(keyEntity.getId());

            for (EntryEntity entryEntity : entryEntities) {
                dictionaryDtos.add(getEntriesDtoFromEntryEntities(entryEntity));
            }
        }

        if (searchDto.getTypeId() == null)
            return dictionaryDtos;

        return dictionaryDtos.stream().filter(c -> c.getTypeEntity().getId() == searchDto.getTypeId()).toList();
    }

    private DictionaryDto getEntriesDtoFromEntryEntities(List<EntryEntity> entryEntities) {
        if (entryEntities == null)
            return null;

        List<DictionaryDto> dictionaryDtos = new ArrayList<>();
        for (EntryEntity entryEntity : entryEntities) {
            DictionaryDto newDictionaryDto = new DictionaryDto();
            HashMap<KeyEntity, List<ValueEntity>> pair = new HashMap<>();
            for (DictionaryDto dictionaryDto : dictionaryDtos) {
                if (dictionaryDto.getTypeEntity().getId() != entryEntity.getTypeId()) {
                    newDictionaryDto.setTypeEntity(baseRepository.getById(TypeEntity.class, entryEntity.getTypeId()));
                }
                if (!dictionaryDto.getPairs().containsKey(baseRepository.getById(KeyEntity.class, entryEntity.getKeyId()))) {

                }

            }
        }

        HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();
        List<ValueEntity> valueEntities = new ArrayList<>();
        for (EntryEntity entryEntity : entryEntities) {
            valueEntities.add(baseRepository.getById(ValueEntity.class, entryEntity.getValueId()))
        }

        pairs.put(baseRepository.getById(KeyEntity.class, entryEntity.getKeyId()),
                valueRepository.getValuesByKeyId(entryEntity.getKeyId()));

        DictionaryDto dictionaryDto = new DictionaryDto();

        dictionaryDto.setTypeEntity(baseRepository.getById(TypeEntity.class, entryEntity.getTypeId()));
        dictionaryDto.setPairs(pairs);

        return dictionaryDto;
    }
}
*/
