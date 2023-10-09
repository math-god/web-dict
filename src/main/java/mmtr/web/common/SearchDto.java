package mmtr.web.common;

import java.util.UUID;

public class SearchDto {
    private UUID typeId;

    private String kindOfInput;
    private String input;

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getKindOfInput() {
        return kindOfInput;
    }

    public void setKindOfInput(String kindOfInput) {
        this.kindOfInput = kindOfInput;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
