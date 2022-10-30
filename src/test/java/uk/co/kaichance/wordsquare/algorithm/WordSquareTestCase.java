package uk.co.kaichance.wordsquare.algorithm;

import lombok.Getter;
import uk.co.kaichance.wordsquare.dao.InputHandler;

import java.util.*;

public class WordSquareTestCase {

    public static final WordSquareTestCase POSITIVE_TEST_CASE_1 = new WordSquareTestCase("eeeeddoonnnsssrv", 4, Arrays.asList("rose", "oven", "send", "ends"));
    public static final WordSquareTestCase POSITIVE_TEST_CASE_2 = new WordSquareTestCase("aaccdeeeemmnnnoo", 4, Arrays.asList("moan", "once", "acme", "need"));
    public static final WordSquareTestCase POSITIVE_TEST_CASE_3 = new WordSquareTestCase("aaaeeeefhhmoonssrrrrttttw", 5, Arrays.asList("feast", "earth", "armer", "steno", "throw"));
    public static final WordSquareTestCase POSITIVE_TEST_CASE_4 = new WordSquareTestCase("aabbeeeeeeeehmosrrrruttvv", 5, Arrays.asList("heart", "ember", "above", "revue", "trees"));
    public static final WordSquareTestCase POSITIVE_TEST_CASE_5 = new WordSquareTestCase("aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy", 7, Arrays.asList("bravado", "renamed", "analogy", "valuers", "amoebas", "degrade", "odyssey"));
    public static final WordSquareTestCase NEGATIVE_TEST_CASE_1 = new WordSquareTestCase("a", 4, Collections.EMPTY_LIST);
    @Getter
    private String inputString;
    @Getter
    private int size;

    private InputHandler inputHandler;
    @Getter
    private List<String> solution;

    private WordSquareTestCase(String inputString, int size, List<String> solution) {
        this.inputString = inputString;
        this.size = size;
        this.solution = solution;
        this.inputHandler = new InputHandler(inputString);
    }

    public Set<Character> getUniqueCharacters() {
        return this.inputHandler.getCharacterCountMap().keySet();
    }

    public Map<Character, Integer> getCharacterCountMap() {
        return this.inputHandler.getCharacterCountMap();
    }

}
