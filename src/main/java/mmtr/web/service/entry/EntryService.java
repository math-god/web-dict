package mmtr.web.service.entry;

import mmtr.web.common.AddEntryDto;
import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface EntryService {
    HashMap<TypeEntity, HashMap<KeyEntity, List<ValueEntity>>> getDataInTableFormat(UUID typeId);

    boolean addEntry(AddEntryDto addEntryDto);
}
