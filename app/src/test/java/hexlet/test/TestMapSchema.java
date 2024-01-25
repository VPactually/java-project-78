package hexlet.test;

import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestMapSchema {

    private MapSchema v;
    private Map<String, Object> data;

    private Map<String, BaseSchema> schemas;

    @BeforeEach
    public void beforeEach() {
        v = new Validator().map();
        data = new HashMap<>();
        data.put("name", null);
    }

    @Test
    public void testRequired() {
        assertTrue(v.isValid(null));
        assertTrue(v.isValid(data));
        v.required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid(data));

    }

    @Test
    public void testSizeof() {
        v.sizeof(2);
        assertFalse(v.isValid(data));
        data.put("age", 56);
        assertTrue(v.isValid(data));
        data.put("weight", 89);
        assertFalse(v.isValid(data));
    }

    @Test
    public void testShape() {
        schemas = new HashMap<>();

        schemas.put("name", new Validator().string().required());
        schemas.put("age", new Validator().number().positive());

        v.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(v.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(v.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", "not null");
        assertFalse(v.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(v.isValid(human4));

    }

    @Test
    public void testRequiredSizeofShape() {
        assertTrue(v.isValid(null));

        v.required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid(data));

        v.sizeof(2);
        assertFalse(v.isValid(data));
        data.put("age", 12);
        assertTrue(v.isValid(data));

        schemas = new HashMap<>();
        schemas.put("name", new Validator().string().required());
        schemas.put("age", new Validator().number().positive());

        v.shape(schemas);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(v.isValid(human2));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(v.isValid(human4));
    }

    @Test
    public void testWrongType() {
        v.required();
        assertFalse(v.isValid(data.toString()));
        assertFalse(v.isValid(13));
        assertFalse(v.isValid(true));
    }
}
