package mmtr.web.service.type;

import mmtr.web.db.entity.TypeEntity;

import java.util.List;

public interface TypeService {
    List<TypeEntity> getTypes();
}
