package mmtr.web.service.operation;

import mmtr.web.common.AddEntryDto;
import mmtr.web.common.AddValueDto;

import java.util.UUID;

public interface OperationService {
    boolean addEntry(AddEntryDto addEntryDto);

    boolean addValue(AddValueDto addValueDto);

    boolean deleteValue(UUID valueId);

    boolean editValue(UUID valueId, String valueName);

    boolean editKey(UUID keyId, String keyName);
}
