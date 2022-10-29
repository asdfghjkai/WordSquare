package uk.co.kaichance.wordsquare.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestArgumentValidation {

    private static String[] VALID_ARGUMENTS = {"4", "aaccdeeeemmnnnoo"};
    private static String[] VALID_EXTENDED_ARGUMENTS = {"4", "aaccdeeeemmnnnoo", "xyz"};
    private static String[] INVALID_SIZE_ARGS = {"n", "aaccdeeeemmnnnoo"};
    private static String[] INVALID_CHAR_COUNT_ARGS = {"4", "aaccdeeeemmnnno"};

    @Test
    public void testValidArguments() {
        Assertions.assertTrue(ArgumentValidation.validateArgs(VALID_ARGUMENTS));
        Assertions.assertTrue(ArgumentValidation.validateArgs(VALID_EXTENDED_ARGUMENTS));
    }

    @Test
    public void testInvalidSizeArgument() {
        Assertions.assertFalse(ArgumentValidation.validateArgs(INVALID_SIZE_ARGS));
    }

    @Test
    public void testInvalidCharCountArgument() {
        Assertions.assertFalse(ArgumentValidation.validateArgs(INVALID_CHAR_COUNT_ARGS));
    }
}
