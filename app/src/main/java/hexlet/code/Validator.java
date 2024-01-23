package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    public BaseSchema string() {
        return new StringSchema();
    }

    public BaseSchema number() {
        return new NumberSchema();
    }

    public BaseSchema map() {
        return new MapSchema();
    }
}
