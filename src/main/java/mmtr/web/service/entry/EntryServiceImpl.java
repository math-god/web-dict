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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public List<EntryDto> getEntriesByType(String type) {
        List<EntryEntity> entryEntities = entryRepository.getEntriesByType(type);

        if (entryEntities == null)
            return null;

        List<EntryDto> entryDtos = new ArrayList<>();

        for (EntryEntity entryEntity : entryEntities) {
            EntryDto entryDto = new EntryDto();

            entryDto.setKey(baseRepository.getById(KeyEntity.class, entryEntity.getKeyId()).getName());
            entryDto.setValues(fromValueListToStringList(valueRepository.getValuesByKeyId(entryEntity.getKeyId())));
            entryDto.setType(baseRepository.getById(TypeEntity.class, entryEntity.getTypeId()).getName());

            entryDtos.add(entryDto);
        }

        Map<String, List<EntryDto>> map = entryDtos.stream()
                .collect(Collectors.groupingBy(EntryDto::getKey));

        return entryDtos;
    }

    private List<String> fromValueListToStringList(List<ValueEntity> list) {
        List<String> stringList = new ArrayList<>();

        for (ValueEntity item : list) {
            stringList.add(item.getName());
        }

        return stringList;
    }
}
