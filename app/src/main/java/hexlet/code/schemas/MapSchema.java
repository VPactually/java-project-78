package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {

    public MapSchema shape(Map<String, ? extends BaseSchema> schemasMap) {
        addPredicate(o -> o == null || (new HashMap<>(o)).entrySet()
                .stream()
                .allMatch(stringObjectEntry -> {
                    var key = stringObjectEntry.getKey();
                    var value = stringObjectEntry.getValue();
                    return schemasMap.get(key).isValid(value);
                }));
        return this;
    }

    public MapSchema sizeof(int sizeof) {
        addPredicate(o -> o == null || new HashMap<>(o).size() == sizeof);
        return this;
    }
}
