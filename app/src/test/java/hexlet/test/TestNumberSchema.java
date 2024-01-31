package hexlet.test;

import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestNumberSchema {
    private NumberSchema v;

    @BeforeEach
    public void beforeEach() {
        v = new Validator().number();
    }

    @Test
    public void testRequired() {
        assertTrue(v.isValid(null));
        assertTrue(v.isValid(-1000));
        v.required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid(-1000));
    }

    @Test
    public void testPositive() {
        v.positive();
        assertTrue(v.isValid(null));
        assertTrue(v.isValid(1));
        assertFalse(v.isValid(-5));
        assertFalse(v.isValid(0));
    }

    @Test
    public void testRange() {
        v.range(5, 20);
        assertTrue(v.isValid(null));

        assertTrue(v.isValid(5));
        assertTrue(v.isValid(20));
        assertTrue(v.isValid(13));
        assertFalse(v.isValid(4));
        assertFalse(v.isValid(21));
    }
    @Test
    public void testChain() {

        v.required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid(-16));

        v.positive();
        assertFalse(v.isValid(-16));
        assertTrue(v.isValid(16));

        v.range(5, 15);
        assertFalse(v.isValid(16));
        assertTrue(v.isValid(5));
    }

}
