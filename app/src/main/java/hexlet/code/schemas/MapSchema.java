package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema shape(Map<String, BaseSchema> schemasMap) {
        addPredicate(o -> {
            var res = (HashMap<String, Object>) o;
            return res.entrySet().stream()
                    .allMatch(stringObjectEntry -> {
                        var key = stringObjectEntry.getKey();
                        var value = stringObjectEntry.getValue();
                        return schemasMap.containsKey(key) && schemasMap.get(key).isValid(value);
                    });
        });
        return this;
    }

    public MapSchema sizeof(int sizeof) {
        addPredicate(o -> {
            var map = (HashMap<String, Object>) o;
            return map.size() == sizeof;
        });
        return this;
    }

    @Override
    public MapSchema required() {
        addPredicate(o -> o instanceof Map);
        return this;
    }
}
