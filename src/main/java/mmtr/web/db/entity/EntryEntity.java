package mmtr.web.db.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "entry", schema = "public", catalog = "dict")
public class EntryEntity extends BaseEntity{
    @Basic
    @Column(name = "key_id")
    private UUID keyId;
    @Basic
    @Column(name = "value_id")
    private UUID valueId;
    @Basic
    @Column(name = "type_id")
    private UUID typeId;

    public UUID getKeyId() {
        return keyId;
    }

    public void setKeyId(UUID keyId) {
        this.keyId = keyId;
    }

    public UUID getValueId() {
        return valueId;
    }

    public void setValueId(UUID valueId) {
        this.valueId = valueId;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryEntity that = (EntryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (keyId != null ? !keyId.equals(that.keyId) : that.keyId != null) return false;
        if (valueId != null ? !valueId.equals(that.valueId) : that.valueId != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (keyId != null ? keyId.hashCode() : 0);
        result = 31 * result + (valueId != null ? valueId.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        return result;
    }
}
