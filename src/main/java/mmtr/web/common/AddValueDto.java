package mmtr.web.common;

import java.util.UUID;

public class AddValueDto {
    private String valueName;
    private UUID keyId;

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public UUID getKeyId() {
        return keyId;
    }

    public void setKeyId(UUID keyId) {
        this.keyId = keyId;
    }
}
