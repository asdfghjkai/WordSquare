package uk.co.kaichance.wordsquare.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGrid;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGridFactory;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGridImplType;
import uk.co.kaichance.wordsquare.util.WordListUtils;

import java.util.List;


public class TestCoreAlgorithm {

    private static final String DICTIONARY_FILE = "enable1.txt";

    @Test
    public void testCoreAlgorithmValidInputTestCase1() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCase.POSITIVE_TEST_CASE_1.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCase.POSITIVE_TEST_CASE_1.getUniqueCharacters(), WordSquareTestCase.POSITIVE_TEST_CASE_1.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCase.POSITIVE_TEST_CASE_1.getSize(), WordSquareTestCase.POSITIVE_TEST_CASE_1.getCharacterCountMap()));
        for (int row = 0; row < 4; row ++) {
            Assertions.assertEquals(WordSquareTestCase.POSITIVE_TEST_CASE_1.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    public void testCoreAlgorithmValidInputTestCase1Array() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.ARRAY, WordSquareTestCase.POSITIVE_TEST_CASE_1.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCase.POSITIVE_TEST_CASE_1.getUniqueCharacters(), WordSquareTestCase.POSITIVE_TEST_CASE_1.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCase.POSITIVE_TEST_CASE_1.getSize(), WordSquareTestCase.POSITIVE_TEST_CASE_1.getCharacterCountMap()));
        for (int row = 0; row < 4; row ++) {
            Assertions.assertEquals(WordSquareTestCase.POSITIVE_TEST_CASE_1.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    public void testCoreAlgorithmValidInputTestCase2() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCase.POSITIVE_TEST_CASE_2.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCase.POSITIVE_TEST_CASE_2.getUniqueCharacters(), WordSquareTestCase.POSITIVE_TEST_CASE_2.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCase.POSITIVE_TEST_CASE_2.getSize(), WordSquareTestCase.POSITIVE_TEST_CASE_2.getCharacterCountMap()));
        for (int row = 0; row < 4; row ++) {
            Assertions.assertEquals(WordSquareTestCase.POSITIVE_TEST_CASE_2.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    public void testCoreAlgorithmValidInputTestCase3() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCase.POSITIVE_TEST_CASE_3.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCase.POSITIVE_TEST_CASE_3.getUniqueCharacters(), WordSquareTestCase.POSITIVE_TEST_CASE_3.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCase.POSITIVE_TEST_CASE_3.getSize(), WordSquareTestCase.POSITIVE_TEST_CASE_3.getCharacterCountMap()));
        for (int row = 0; row < 4; row ++) {
            Assertions.assertEquals(WordSquareTestCase.POSITIVE_TEST_CASE_3.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    public void testCoreAlgorithmValidInputTestCase4() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCase.POSITIVE_TEST_CASE_4.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCase.POSITIVE_TEST_CASE_4.getUniqueCharacters(), WordSquareTestCase.POSITIVE_TEST_CASE_4.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCase.POSITIVE_TEST_CASE_4.getSize(), WordSquareTestCase.POSITIVE_TEST_CASE_4.getCharacterCountMap()));
        for (int row = 0; row < 4; row ++) {
            Assertions.assertEquals(WordSquareTestCase.POSITIVE_TEST_CASE_4.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    public void testCoreAlgorithmValidInputTestCase5() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCase.POSITIVE_TEST_CASE_5.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCase.POSITIVE_TEST_CASE_5.getUniqueCharacters(), WordSquareTestCase.POSITIVE_TEST_CASE_5.getSize());

        Assertions.assertTrue(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCase.POSITIVE_TEST_CASE_5.getSize(), WordSquareTestCase.POSITIVE_TEST_CASE_5.getCharacterCountMap()));
        for (int row = 0; row < 4; row ++) {
            Assertions.assertEquals(WordSquareTestCase.POSITIVE_TEST_CASE_5.getSolution().get(row), wg.getRowSoFar(row));
        }
    }

    @Test
    public void testCoreAlgorithmNegativeTestCase1() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, WordSquareTestCase.NEGATIVE_TEST_CASE_1.getSize());
        List<String> words = WordListUtils.processList(DICTIONARY_FILE, WordSquareTestCase.NEGATIVE_TEST_CASE_1.getUniqueCharacters(), WordSquareTestCase.NEGATIVE_TEST_CASE_1.getSize());

        Assertions.assertFalse(CoreAlgorithm.solveWordsquare(wg, words, WordSquareTestCase.NEGATIVE_TEST_CASE_1.getSize(), WordSquareTestCase.NEGATIVE_TEST_CASE_1.getCharacterCountMap()));
    }
}
