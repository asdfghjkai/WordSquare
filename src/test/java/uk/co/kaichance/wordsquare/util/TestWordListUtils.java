package uk.co.kaichance.wordsquare.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TestWordListUtils {

    @Test
    public void testValidFile() {
        List<String> list = WordListUtils.processList("enable1.txt", new HashSet<Character>(Arrays.asList('a', 'b', 'l')), 4);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list instanceof List);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void testValidFileInvalidParameters() {
        List<String> list = WordListUtils.processList("enable1.txt", new HashSet<>(Arrays.asList('a', 'b', 'l')), 0);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list instanceof List);
        Assertions.assertTrue(list.isEmpty());

        list = WordListUtils.processList("enable1.txt", new HashSet<>(Arrays.asList()), 4);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list instanceof List);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testInvalidFile() {
        List<String> list = WordListUtils.processList("balls.txt", new HashSet<>(Arrays.asList('a', 'b', 'l')), 4);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list instanceof List);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testEmptyFile() {
        List<String> list = WordListUtils.processList("emptyfile.txt", new HashSet<>(Arrays.asList('a', 'b', 'l')), 4);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list instanceof List);
        Assertions.assertTrue(list.isEmpty());
    }
}
