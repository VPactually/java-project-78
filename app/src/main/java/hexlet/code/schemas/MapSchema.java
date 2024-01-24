package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema implements BaseSchema {
    private boolean required = false;
    private boolean isSizeof;
    private boolean isSchemas;
    private int sizeof;
    private Map<String, BaseSchema> schemas;

    public MapSchema() {
        this.schemas = new HashMap<>();
    }

    public void shape(Map<String, BaseSchema> schemasMap) {
        this.isSchemas = true;
        this.schemas.putAll(schemasMap);
    }

    @Override
    public boolean isValid(Object obj) {
        if (!required) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        var map = new HashMap<>((Map<String, Object>) obj);
        boolean sizeResult = true;
        boolean schemasResult = true;
        if (isSizeof) {
            sizeResult = map.size() == sizeof;
        }
        if (isSchemas) {
            schemasResult = isMapValid(map);
        }

        return sizeResult && schemasResult;
    }

    public boolean isMapValid(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .allMatch(entry -> {
                    var key = entry.getKey();
                    var value = entry.getValue();
                    return schemas.containsKey(key) && schemas.get(key).isValid(value);
                });
    }


    public BaseSchema sizeof(int size) {
        isSizeof = true;
        sizeof = size;
        return this;
    }

    @Override
    public MapSchema required() {
        required = true;
        return this;
    }

}
