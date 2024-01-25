package hexlet.test;

import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestStringSchema {

    private StringSchema v;

    @BeforeEach
    public void beforeEach() {
        v = new Validator().string();
    }

    @Test
    public void testRequired() {
        assertTrue(v.isValid(""));
        assertTrue(v.isValid(null));
        v.required();
        assertFalse(v.isValid(""));
        assertFalse(v.isValid(null));
        assertTrue(v.isValid("string"));
    }

    @Test
    public void testLength() {
        v.minLength(5).required();
        assertFalse(v.isValid("true"));
        assertTrue(v.isValid("string"));
    }

    @Test
    public void testContains() {
        assertTrue(v.isValid(null));
        v.contains("ring").required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid("string"));
    }

    @Test
    public void testRequiredLengthContains() {
        v.required();
        assertFalse(v.isValid(null));
        v.minLength(6);
        assertFalse(v.isValid("!null"));
        v.contains("not");
        assertTrue(v.isValid("not null"));
    }

    @Test
    public void testWrongType() {
        v.required();
        assertFalse(v.isValid(13));
        assertFalse(v.isValid(true));
        assertFalse(v.isValid(Map.of()));
    }

}
