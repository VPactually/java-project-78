package hexlet.test;

import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    private Validator v;
    private StringSchema stringSchema;
    private NumberSchema numberSchema;
    private MapSchema mapSchema;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();

    }
    @Test
    public void testValidatorString() {
        stringSchema = (StringSchema) v.string();

        assertThat(stringSchema.isValid("")).isTrue();
        assertThat(stringSchema.isValid(null)).isTrue();

        stringSchema.required();

        assertThat(stringSchema.isValid(null)).isFalse();
        assertThat(stringSchema.isValid("")).isFalse();

        assertThat(stringSchema.required().minLength(5).contains("dog").isValid("what does the dog say")).isTrue();
        assertThat(stringSchema.isValid("what does the fox say")).isFalse();
    }

    @Test
    public void testValidatorNumber() {
        numberSchema = (NumberSchema) v.number();

        assertThat(numberSchema.isValid(5)).isTrue();
        assertThat(numberSchema.isValid(null)).isTrue();

        numberSchema.required();

        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid("5")).isFalse();
        assertThat(numberSchema.isValid(10)).isTrue();
        assertThat(numberSchema.positive().isValid(-5)).isFalse();
        assertThat(numberSchema.isValid(10)).isTrue();
        assertThat(numberSchema.isValid(0)).isFalse();

        numberSchema.range(2, 10);

        assertThat(numberSchema.isValid(5)).isTrue();
        assertThat(numberSchema.isValid(10)).isTrue();
        assertThat(numberSchema.isValid(1)).isFalse();
        assertThat(numberSchema.isValid(11)).isFalse();
    }

    @Test
    public void testValidatorMap() {
        mapSchema = (MapSchema) v.map();
        var data = new HashMap<>(Map.of("key1", "value1"));

        assertThat(mapSchema.isValid(null)).isTrue();

        mapSchema.required();

        assertThat(mapSchema.isValid(null)).isFalse();
        assertThat(mapSchema.isValid(data)).isTrue();

        mapSchema.sizeof(2);

        assertThat(mapSchema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(mapSchema.isValid(data)).isTrue();
    }

    @Test
    public void testValidatorNestedValidation() {
        mapSchema = (MapSchema) v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();

        var nameVal = new Validator().string();
        var ageVal = (NumberSchema) new Validator().number();

        schemas.put("name", nameVal.required());
        schemas.put("age", ageVal.positive());

        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(mapSchema.isValid(human1)) ;

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(mapSchema.isValid(human2)) ;

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(mapSchema.isValid(human3)).isFalse();
//
//        Map<String, Object> human4 = new HashMap<>();
//        human4.put("name", "Valya");
//        human4.put("age", -5);
//        assertThat(mapScheme.isValid(human4)).isFalse();
    }

}
