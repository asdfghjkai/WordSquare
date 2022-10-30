package uk.co.kaichance.wordsquare.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGrid;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGridFactory;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGridImplType;
import uk.co.kaichance.wordsquare.util.WordListUtils;

import java.util.List;


class TestCoreAlgorithm {

    private static final String DICTIONARY_FILE = "enable1.txt";

    @Test
    void testCoreAlgorithmValidInputTestCase1() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCases.POSITIVE_TEST_CASE_1.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCases.POSITIVE_TEST_CASE_1.getUniqueCharacters(), WordSquareTestCases.POSITIVE_TEST_CASE_1.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCases.POSITIVE_TEST_CASE_1.getSize(), WordSquareTestCases.POSITIVE_TEST_CASE_1.getCharacterCountMap()));
        for (int row = 0; row < 4; row++) {
            Assertions.assertEquals(WordSquareTestCases.POSITIVE_TEST_CASE_1.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    void testCoreAlgorithmValidInputTestCase1Array() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.ARRAY, WordSquareTestCases.POSITIVE_TEST_CASE_1.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCases.POSITIVE_TEST_CASE_1.getUniqueCharacters(), WordSquareTestCases.POSITIVE_TEST_CASE_1.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCases.POSITIVE_TEST_CASE_1.getSize(), WordSquareTestCases.POSITIVE_TEST_CASE_1.getCharacterCountMap()));
        for (int row = 0; row < 4; row++) {
            Assertions.assertEquals(WordSquareTestCases.POSITIVE_TEST_CASE_1.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    void testCoreAlgorithmValidInputTestCase2() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCases.POSITIVE_TEST_CASE_2.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCases.POSITIVE_TEST_CASE_2.getUniqueCharacters(), WordSquareTestCases.POSITIVE_TEST_CASE_2.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCases.POSITIVE_TEST_CASE_2.getSize(), WordSquareTestCases.POSITIVE_TEST_CASE_2.getCharacterCountMap()));
        for (int row = 0; row < 4; row++) {
            Assertions.assertEquals(WordSquareTestCases.POSITIVE_TEST_CASE_2.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    void testCoreAlgorithmValidInputTestCase3() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCases.POSITIVE_TEST_CASE_3.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCases.POSITIVE_TEST_CASE_3.getUniqueCharacters(), WordSquareTestCases.POSITIVE_TEST_CASE_3.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCases.POSITIVE_TEST_CASE_3.getSize(), WordSquareTestCases.POSITIVE_TEST_CASE_3.getCharacterCountMap()));
        for (int row = 0; row < 4; row++) {
            Assertions.assertEquals(WordSquareTestCases.POSITIVE_TEST_CASE_3.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    void testCoreAlgorithmValidInputTestCase4() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCases.POSITIVE_TEST_CASE_4.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCases.POSITIVE_TEST_CASE_4.getUniqueCharacters(), WordSquareTestCases.POSITIVE_TEST_CASE_4.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCases.POSITIVE_TEST_CASE_4.getSize(), WordSquareTestCases.POSITIVE_TEST_CASE_4.getCharacterCountMap()));
        for (int row = 0; row < 4; row++) {
            Assertions.assertEquals(WordSquareTestCases.POSITIVE_TEST_CASE_4.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    void testCoreAlgorithmValidInputTestCase5() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCases.POSITIVE_TEST_CASE_5.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCases.POSITIVE_TEST_CASE_5.getUniqueCharacters(), WordSquareTestCases.POSITIVE_TEST_CASE_5.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCases.POSITIVE_TEST_CASE_5.getSize(), WordSquareTestCases.POSITIVE_TEST_CASE_5.getCharacterCountMap()));
        for (int row = 0; row < 4; row++) {
            Assertions.assertEquals(WordSquareTestCases.POSITIVE_TEST_CASE_5.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    void testCoreAlgorithmNegativeTestCase1() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCases.NEGATIVE_TEST_CASE_1.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCases.NEGATIVE_TEST_CASE_1.getUniqueCharacters(), WordSquareTestCases.NEGATIVE_TEST_CASE_1.getSize());

        Assertions.assertFalse(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCases.NEGATIVE_TEST_CASE_1.getSize(), WordSquareTestCases.NEGATIVE_TEST_CASE_1.getCharacterCountMap()));
    }
}
