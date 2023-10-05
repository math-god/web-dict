package mmtr.web.service.entry;

import mmtr.web.common.AddEntryDto;
import mmtr.web.common.GetEntriesDto;

import java.util.List;
import java.util.UUID;

public interface EntryService {
    boolean addEntry(AddEntryDto addEntryDto);

    GetEntriesDto getEntriesByTypeId(UUID typeId);

    GetEntriesDto getEntriesByKeyIdAndTypeId(UUID keyId, UUID typeId);

    List<GetEntriesDto> getEntriesByKeyId(UUID keyId);

    GetEntriesDto getEntriesByValueIdAndTypeId(UUID valueId, UUID typeId);

    List<GetEntriesDto> getEntriesByValueId(UUID valueId);
}
