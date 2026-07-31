package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import utils.Validator;

public class ValidatorTest {
    private final Validator validator = new Validator();

    @Test(expected = IllegalArgumentException.class)
    public void testValidateNonEmptyStringEmpty() {
        validator.validateNonEmptyString("   ", "TestField");
    }

    @Test
    public void testValidateNonEmptyStringValid() {
        String result = validator.validateNonEmptyString("  Hello  ", "TestField");
        assertEquals("Hello", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateLongInvalidInput() {
        validator.validateLong("abc", "LongField", 100L);
    }

    @Test
    public void testValidateLongValid() {
        Long result = validator.validateLong("50", "LongField", 100L);
        assertEquals(Long.valueOf(50), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateLongExceedsMax() {
        validator.validateLong("150", "LongField", 100L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateIntegerInvalid() {
        validator.validateInteger("xyz", "IntegerField");
    }

    @Test
    public void testValidateIntegerValid() {
        Integer result = validator.validateInteger("123", "IntegerField");
        assertEquals(Integer.valueOf(123), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateFloatInvalid() {
        validator.validateFloat("notANumber", "FloatField", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateFloatNegativeWhenPositiveRequired() {
        validator.validateFloat("-10", "FloatField", true);
    }

    @Test
    public void testValidateFloatValid() {
        Float result = validator.validateFloat("175.5", "FloatField", true);
        assertEquals(Float.valueOf(175.5f), result);
    }
}
