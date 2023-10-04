package mmtr.web.service.entry;

import mmtr.web.common.AddEntryDto;
import mmtr.web.db.entity.EntryEntity;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;
import mmtr.web.db.repo.entry.EntryRepository;
import mmtr.web.db.repo.type.TypeRepository;
import mmtr.web.db.repo.value.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class EntryServiceImpl implements EntryService {

    private EntryRepository entryRepository;
    private ValueRepository valueRepository;
    private BaseRepository baseRepository;
    private TypeRepository typeRepository;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository, ValueRepository valueRepository, BaseRepository baseRepository, TypeRepository typeRepository) {
        this.entryRepository = entryRepository;
        this.valueRepository = valueRepository;
        this.baseRepository = baseRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public HashMap<TypeEntity, HashMap<KeyEntity, List<ValueEntity>>> getDataInTableFormat(UUID typeId) {
        List<EntryEntity> entryEntities = entryRepository.getEntriesByTypeId(typeId);

        if (entryEntities == null)
            return null;

        HashMap<TypeEntity, HashMap<KeyEntity, List<ValueEntity>>> tableData = new HashMap<>();
        HashMap<KeyEntity, List<ValueEntity>> pairs = new HashMap<>();

        for (EntryEntity entryEntity : entryEntities) {
            pairs.put(baseRepository.getById(KeyEntity.class, entryEntity.getKeyId()),
                    valueRepository.getValuesByKeyId(entryEntity.getKeyId()));
        }

        tableData.put(baseRepository.getById(TypeEntity.class, typeId), pairs);

        return tableData;
    }

    @Override
    public boolean addEntry(AddEntryDto addEntryDto) {
        if (!addEntryDto.getKey().matches(baseRepository.getById(TypeEntity.class, addEntryDto.getTypeId()).getRegex()))
            return false;


        KeyEntity keyEntity = new KeyEntity();
        keyEntity.setName(addEntryDto.getKey());
        KeyEntity resultKeyEntity = baseRepository.create(keyEntity);

        List<ValueEntity> resultValueEntities = new ArrayList<>();
        for (String value : addEntryDto.getValues()) {
            ValueEntity valueEntity = new ValueEntity();
            valueEntity.setName(value);
            ValueEntity resultValueEntity = baseRepository.create(valueEntity);
            resultValueEntities.add(resultValueEntity);
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

    private List<String> fromValueListToStringList(List<ValueEntity> list) {
        List<String> stringList = new ArrayList<>();

        for (ValueEntity item : list) {
            stringList.add(item.getName());
        }

        return stringList;
    }
}
