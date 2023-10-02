package mmtr.web.service.entry;

import mmtr.web.common.EntryDto;

import java.util.List;

public interface EntryService {
    List<EntryDto> getEntriesByType(String type);
}
