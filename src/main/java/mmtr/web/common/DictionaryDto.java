package mmtr.web.common;

import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.entity.ValueEntity;

import java.util.HashMap;
import java.util.List;

public class DictionaryDto {
    private TypeEntity typeEntity;

    private HashMap<KeyEntity, List<ValueEntity>> pairs;

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    public HashMap<KeyEntity, List<ValueEntity>> getPairs() {
        return pairs;
    }

    public void setPairs(HashMap<KeyEntity, List<ValueEntity>> pairs) {
        this.pairs = pairs;
    }
}
