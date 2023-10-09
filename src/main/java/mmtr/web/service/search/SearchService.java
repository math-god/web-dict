package mmtr.web.service.search;

import mmtr.web.common.DictionaryDto;
import mmtr.web.common.SearchDto;
import mmtr.web.db.entity.TypeEntity;

import java.util.List;
import java.util.UUID;

public interface SearchService {
    List<DictionaryDto> getEntriesByValue(UUID typeId, String valueName);
    List<DictionaryDto> getEntriesByKey(UUID typeId, String keyName);

    DictionaryDto getDictionaryByTypeId(UUID typeId);

    List<TypeEntity> getTypes();

}
