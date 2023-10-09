package mmtr.web.service.entry;

import mmtr.web.common.AddEntryDto;
import mmtr.web.common.GetEntriesDto;
import mmtr.web.common.SearchDto;
import mmtr.web.db.entity.EntryEntity;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;
import mmtr.web.db.repo.entry.EntryRepository;
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

    private EntryRepository entryRepository;
    private ValueRepository valueRepository;
    private BaseRepository baseRepository;
    private TypeRepository typeRepository;
    private KeyRepository keyRepository;
    private KeyService keyService;
    private ValueService valueService;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository, ValueRepository valueRepository, BaseRepository baseRepository, TypeRepository typeRepository, KeyRepository keyRepository, KeyService keyService, ValueService valueService) {
        this.entryRepository = entryRepository;
        this.valueRepository = valueRepository;
        this.baseRepository = baseRepository;
        this.typeRepository = typeRepository;
        this.keyRepository = keyRepository;
        this.keyService = keyService;
        this.valueService = valueService;
    }

    @Override
    public GetEntriesDto getEntriesByTypeId(UUID typeId) {
        List<EntryEntity> entryEntities = entryRepository.getEntriesByTypeId(typeId);

        if (entryEntities == null)
            return null;

        HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();
        for (EntryEntity entryEntity : entryEntities) {
            pairs.put(baseRepository.getById(KeyEntity.class, entryEntity.getKeyId()),
                    valueRepository.getValuesByKeyId(entryEntity.getKeyId()));
        }

        GetEntriesDto getEntriesDto = new GetEntriesDto();

        getEntriesDto.setTypeEntity(baseRepository.getById(TypeEntity.class, typeId));
        getEntriesDto.setPairs(pairs);

        return getEntriesDto;
    }

    @Override
    public GetEntriesDto getEntriesByKeyIdAndTypeId(UUID keyId, UUID typeId) {
        GetEntriesDto getEntriesDto = getEntriesByTypeId(typeId);

        HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();
        for (Map.Entry<KeyEntity, List<ValueEntity>> pair : getEntriesDto.getPairs().entrySet()) {
            if (pair.getKey().getId().equals(keyId))
                pairs.put(pair.getKey(), pair.getValue());
        }

        GetEntriesDto result = new GetEntriesDto();
        result.setTypeEntity(getEntriesDto.getTypeEntity());
        result.setPairs(pairs);

        return result;
    }

    @Override
    public List<GetEntriesDto> getEntriesByKeyId(UUID keyId) {
        return null;
    }

    @Override
    public GetEntriesDto getEntriesByValueIdAndTypeId(UUID valueId, UUID typeId) {
        return null;
    }

    @Override
    public List<GetEntriesDto> getEntriesByValueId(UUID valueId) {
        return null;
    }

    @Override
    public boolean addEntry(AddEntryDto addEntryDto) {
        KeyEntity resultKeyEntity = keyService.addKey(addEntryDto.getKey(), addEntryDto.getTypeId());
        if (resultKeyEntity == null)
            return false;

        List<ValueEntity> resultValueEntities = new ArrayList<>();
        for (String valueName : addEntryDto.getValues()) {
            resultValueEntities.add(valueService.addValue(valueName));
        }

        for (ValueEntity valueEntity : resultValueEntities) {
            EntryEntity entryEntity = new EntryEntity();
            entryEntity.setKeyId(resultKeyEntity.getId());
            entryEntity.setValueId(valueEntity.getId());
            entryEntity.setTypeId(addEntryDto.getTypeId());
            baseRepository.create(entryEntity);
        }

        return true;
    }

    @Override
    public List<GetEntriesDto> getEntriesByFilter(SearchDto searchDto) {
        List<GetEntriesDto> getEntriesDtos = new ArrayList<>();
        if (searchDto.getKindOfInput().equals("value")) {
            ValueEntity valueEntity = valueRepository.getValueByName(searchDto.getInput());
            if (valueEntity == null)
                return null;

            List<EntryEntity> entryEntities = entryRepository.getEntriesByValueId(valueEntity.getId());

            for (EntryEntity entryEntity : entryEntities) {
                getEntriesDtos.add(getEntriesDtoFromEntryEntities(entryEntity));
            }
        }

        if (searchDto.getKindOfInput().equals("key")) {
            KeyEntity keyEntity = keyRepository.getKeyByName(searchDto.getInput());
            if (keyEntity == null)
                return null;

            List<EntryEntity> entryEntities = entryRepository.getEntriesByKeyId(keyEntity.getId());

            for (EntryEntity entryEntity : entryEntities) {
                getEntriesDtos.add(getEntriesDtoFromEntryEntities(entryEntity));
            }
        }

        if (searchDto.getTypeId() == null)
            return getEntriesDtos;

        return getEntriesDtos.stream().filter(c -> c.getTypeEntity().getId() == searchDto.getTypeId()).toList();
    }

    private GetEntriesDto getEntriesDtoFromEntryEntities(EntryEntity entryEntity) {
        if (entryEntity == null)
            return null;

        HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();
        pairs.put(baseRepository.getById(KeyEntity.class, entryEntity.getKeyId()),
                valueRepository.getValuesByKeyId(entryEntity.getKeyId()));

        GetEntriesDto getEntriesDto = new GetEntriesDto();

        getEntriesDto.setTypeEntity(baseRepository.getById(TypeEntity.class, entryEntity.getTypeId()));
        getEntriesDto.setPairs(pairs);

        return getEntriesDto;
    }
}
