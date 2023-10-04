package mmtr.web.service.entry;

import mmtr.web.common.EntryDto;
import mmtr.web.db.entity.EntryEntity;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;
import mmtr.web.db.repo.base.BaseRepository;
import mmtr.web.db.repo.entry.EntryRepository;
import mmtr.web.db.repo.key.KeyRepository;
import mmtr.web.db.repo.type.TypeRepository;
import mmtr.web.db.repo.value.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {

    private EntryRepository entryRepository;
    private KeyRepository keyRepository;
    private ValueRepository valueRepository;
    private TypeRepository typeRepository;
    private BaseRepository baseRepository;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository, KeyRepository keyRepository,
                            ValueRepository valueRepository, TypeRepository typeRepository, BaseRepository baseRepository) {
        this.entryRepository = entryRepository;
        this.keyRepository = keyRepository;
        this.valueRepository = valueRepository;
        this.typeRepository = typeRepository;
        this.baseRepository = baseRepository;
    }

    @Override
    public HashMap<String, HashMap<String, List<String>>> getDataInTableFormat(String type) {
        List<EntryEntity> entryEntities = entryRepository.getEntriesByType(type);

        if (entryEntities == null)
            return null;

        HashSet<UUID> keys = new HashSet<>();
        for (EntryEntity item : entryEntities)
            keys.add(item.getKeyId());

        HashMap<String, HashMap<String, List<String>>> tableData = new HashMap<>();
        HashMap<String, List<String>> pairs = new HashMap<>();

        for (UUID key : keys)
            pairs.put(baseRepository.getById(KeyEntity.class, key).getName(),
                    fromValueListToStringList(valueRepository.getValuesByKeyId(key)));

        tableData.put(type, pairs);

        return tableData;
    }

    private List<String> fromValueListToStringList(List<ValueEntity> list) {
        List<String> stringList = new ArrayList<>();

        for (ValueEntity item : list) {
            stringList.add(item.getName());
        }

        return stringList;
    }
}
