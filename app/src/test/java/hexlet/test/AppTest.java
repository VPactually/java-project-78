package hexlet.test;

import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
public class AppTest {
    private Validator v;
    private StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.string();
    }
    @Test
    public void testValidator1() {

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();

        assertThat(schema.required().minLength(5).contains("dog").isValid("what does the dog say")).isTrue();
        assertThat(schema.isValid("what does the fox say")).isFalse();
    }

    @Test
    public void testValidator2() {
        schema.required();
        assertThat(schema.contains("fox").isValid("what does the fox say")).isTrue();
    }
}
