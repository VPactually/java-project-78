package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema implements BaseSchema {
    private boolean required = false;
    private boolean isSizeof;
    private boolean isSchemas;
    private int sizeof;
    private Map<String, BaseSchema> schemas = new HashMap<>();

    @Override
    public boolean isValid(Object obj) {
        if (!required) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        Map<String, Object> map = (HashMap<String, Object>) obj;
        System.out.println(schemas);
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
                    System.out.println(key + value);
                    return schemas.containsKey(key) && schemas.get(key).isValid(value);
                });
    }

    public BaseSchema shape(Map<String, BaseSchema> stringBaseSchemaMap) {
        if (stringBaseSchemaMap != null && !stringBaseSchemaMap.isEmpty()) {
            isSchemas = true;
            this.schemas = new HashMap<>(stringBaseSchemaMap);
        }
        return this;
    }

    public BaseSchema sizeof(int size) {
        isSizeof = true;
        sizeof = size;
        return this;
    }

    @Override
    public BaseSchema required() {
        required = true;
        return this;
    }
}
