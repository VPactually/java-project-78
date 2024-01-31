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

    private Map<String, BaseSchema> schemas;

    @BeforeEach
    public void beforeEach() {
        v = new Validator().map();
    }

    @Test
    public void testRequired() {
        assertTrue(v.isValid(null));
        assertTrue(v.isValid(Map.of("name", "John")));
        v.required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid(Map.of("name", 27)));

    }

    @Test
    public void testSizeof() {
        v.sizeof(2);
        assertTrue(v.isValid(null));
        assertFalse(v.isValid(Map.of("name", "John")));
        assertTrue(v.isValid(Map.of("name", "John", "age", 56)));
        assertFalse(v.isValid(Map.of("name", "John", "age", 56, "weight", 89)));
    }

    @Test
    public void testShape() {
        schemas = new HashMap<>();

        schemas.put("name", new Validator().string().required());
        schemas.put("age", new Validator().number().positive());

        v.shape(schemas);
        assertTrue(v.isValid(null));

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
        human4.put("name", "Victor");
        human4.put("age", -5);
        assertFalse(v.isValid(human4));

    }

    @Test
    public void testChain() {
        assertTrue(v.isValid(null));

        v.required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid(Map.of("name", "John")));

        v.sizeof(2);
        assertTrue(v.isValid(Map.of("name", "John", "age", 56)));

        schemas = new HashMap<>(Map.of(
                "name", new Validator().string().required(),
                "age", new Validator().number().positive())
        );

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
}
