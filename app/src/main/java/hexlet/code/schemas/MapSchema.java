package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<?, ?>> {

    public MapSchema<T> shape(Map<String, BaseSchema> schemasMap) {
        addPredicate(o -> o == null || (
                schemasMap.entrySet().stream().allMatch(e -> e.getValue().isValid(o.get(e.getKey()))))
        );
        return this;
    }

    public MapSchema<T> sizeof(int sizeof) {
        addPredicate(o -> o == null || new HashMap<>(o).size() == sizeof);
        return this;
    }
}
