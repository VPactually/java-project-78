package hexlet.code.schemas;

import java.util.HashMap;

public class MapScheme implements BaseSchema {
    private boolean required = false;
    private boolean isSizeof;
    private int sizeof;

    @Override
    public boolean isValid(Object obj) {
        if (!required) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(HashMap.class)) {
            return false;
        } else {
            HashMap<String, String> map = (HashMap<String, String>) obj;
            return !isSizeof || map.size() == sizeof;
        }
    }

    public BaseSchema sizeof(int size) {
        sizeof = size;
        isSizeof = true;
        return this;
    }

    @Override
    public BaseSchema required() {
        required = true;
        return this;
    }
}
