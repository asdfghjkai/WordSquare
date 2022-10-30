package uk.co.kaichance.wordsquare.algorithm;

import lombok.Getter;
import uk.co.kaichance.wordsquare.dao.InputHandler;

import java.util.*;

public class WordSquareTestCases {

    public static final WordSquareTestCases POSITIVE_TEST_CASE_1 = new WordSquareTestCases("eeeeddoonnnsssrv", 4, Arrays.asList("rose", "oven", "send", "ends"));
    public static final WordSquareTestCases POSITIVE_TEST_CASE_2 = new WordSquareTestCases("aaccdeeeemmnnnoo", 4, Arrays.asList("moan", "once", "acme", "need"));
    public static final WordSquareTestCases POSITIVE_TEST_CASE_3 = new WordSquareTestCases("aaaeeeefhhmoonssrrrrttttw", 5, Arrays.asList("feast", "earth", "armer", "steno", "throw"));
    public static final WordSquareTestCases POSITIVE_TEST_CASE_4 = new WordSquareTestCases("aabbeeeeeeeehmosrrrruttvv", 5, Arrays.asList("heart", "ember", "above", "revue", "trees"));
    public static final WordSquareTestCases POSITIVE_TEST_CASE_5 = new WordSquareTestCases("aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy", 7, Arrays.asList("bravado", "renamed", "analogy", "valuers", "amoebas", "degrade", "odyssey"));
    public static final WordSquareTestCases NEGATIVE_TEST_CASE_1 = new WordSquareTestCases("a", 4, Collections.EMPTY_LIST);
    @Getter
    private String inputString;
    @Getter
    private int size;

    private InputHandler inputHandler;
    @Getter
    private List<String> solution;

    private WordSquareTestCases(String inputString, int size, List<String> solution) {
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
