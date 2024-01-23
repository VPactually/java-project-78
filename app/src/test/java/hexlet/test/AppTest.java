package hexlet.test;

import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.MapScheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
public class AppTest {
    private Validator v;
    private StringSchema stringSchema;
    private NumberSchema numberSchema;
    private MapScheme mapScheme;

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
        mapScheme = (MapScheme) v.map();
        var data = new HashMap<>(Map.of("key1", "value1"));

        assertThat(mapScheme.isValid(null)).isTrue();

        mapScheme.required();

        assertThat(mapScheme.isValid(null)).isFalse();
        assertThat(mapScheme.isValid(data)).isTrue();

        mapScheme.sizeof(2);

        assertThat(mapScheme.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(mapScheme.isValid(data)).isTrue();
    }

}
