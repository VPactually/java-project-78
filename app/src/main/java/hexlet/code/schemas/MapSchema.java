package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addPredicate(o -> o instanceof Map<?, ?> || o == null);
    }

    public MapSchema shape(Map<String, BaseSchema> schemasMap) {
        addPredicate(o -> o == null || (new HashMap<>((HashMap<String, Object>) o)).entrySet()
                .stream()
                .allMatch(stringObjectEntry -> {
                    var key = stringObjectEntry.getKey();
                    var value = stringObjectEntry.getValue();
                    return schemasMap.get(key).isValid(value);
                }));
        return this;
    }

    public MapSchema sizeof(int sizeof) {
        addPredicate(o -> o == null || (new HashMap<>((Map<String, Object>) o).size() == sizeof));
        return this;
    }
}
