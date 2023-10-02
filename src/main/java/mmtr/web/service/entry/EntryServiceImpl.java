package mmtr.web.service.entry;

import mmtr.web.common.EntryDto;
import mmtr.web.db.entity.EntryEntity;
import mmtr.web.db.repo.entry.EntryRepository;
import mmtr.web.db.repo.key.KeyRepository;
import mmtr.web.db.repo.type.TypeRepository;
import mmtr.web.db.repo.value.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    private EntryRepository entryRepository;
    private KeyRepository keyRepository;
    private ValueRepository valueRepository;
    private TypeRepository typeRepository;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository, KeyRepository keyRepository,
                            ValueRepository valueRepository, TypeRepository typeRepository) {
        this.entryRepository = entryRepository;
        this.keyRepository = keyRepository;
        this.valueRepository = valueRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public List<EntryDto> getEntriesByType(String type) {
        List<EntryEntity> entryEntities = entryRepository.getEntriesByType(type);

        if (entryEntities == null)
            return null;

        List<EntryDto> entryDtos = new ArrayList<>();

        for (EntryEntity entryEntity : entryEntities) {
            EntryDto entryDto = new EntryDto();

            entryDto.setKey(keyRepository.getById(entryEntity.getKeyId()).getName());
            entryDto.setValues(valueRepository.getValuesByKeyId(entryEntity.getKeyId()).toString());
            entryDto.setType(typeRepository.getById(entryEntity.getTypeId()).getName());

            entryDtos.add(entryDto);
        }

        return entryDtos;
    }
}
