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
        var keyName = "name";
        var keyAge = "age";

        StringSchema schForName = (StringSchema) schemas.get(keyName);
        NumberSchema schForAge = (NumberSchema) schemas.get(keyAge);

        schForAge.printFlags();
        System.out.println(map.get(keyAge));
        System.out.println(schForAge.isValid(map.get(keyAge)));

        return schForName.isValid(map.get(keyName)) && schForAge.isValid(map.get(keyAge));


//        return map.entrySet()
//                .stream()
//                .allMatch(entry -> {
//                    var key = entry.getKey();
//                    var value = entry.getValue();
//                    return schemas.containsKey(key) && map.get(key).isValid(value);
//                });
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
