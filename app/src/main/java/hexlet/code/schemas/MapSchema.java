package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {

    public MapSchema<T> shape(Map<String, ? extends BaseSchema<T>> schemasMap) {
        addPredicate(o -> o == null || (new HashMap<>(o)).entrySet()
                .stream()
                .allMatch(stringObjectEntry -> {
                    var key = stringObjectEntry.getKey();
                    var value = stringObjectEntry.getValue();
                    return schemasMap.get(key).isValid(value);
                }));
        return this;
    }

    public MapSchema<T> sizeof(int sizeof) {
        addPredicate(o -> o == null || new HashMap<>(o).size() == sizeof);
        return this;
    }
}
