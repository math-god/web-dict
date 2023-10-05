package mmtr.web.service.value;

import mmtr.web.db.entity.ValueEntity;

import java.util.UUID;

public interface ValueService {
    ValueEntity addValue(String valueName);
    ValueEntity editValue(String valueName, UUID valueId);
}
