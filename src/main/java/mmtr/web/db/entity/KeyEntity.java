package mmtr.web.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "key", schema = "public", catalog = "dict")
public class KeyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Object id;
    @Basic
    @Column(name = "name")
    private String name;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyEntity keyEntity = (KeyEntity) o;

        if (id != null ? !id.equals(keyEntity.id) : keyEntity.id != null) return false;
        if (name != null ? !name.equals(keyEntity.name) : keyEntity.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
