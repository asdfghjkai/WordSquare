package uk.co.kaichance.wordsquare.algorithm;

import org.apache.commons.lang3.StringUtils;
import uk.co.kaichance.wordsquare.dao.WordGrid;
import uk.co.kaichance.wordsquare.util.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoreAlgorithm {

    private CoreAlgorithm() {
    } //Private Constructor

    /**
     * Primary static method for solving wordsquare puzzles. Calls a private method by the same name, with current depth as zero to start the solve
     *
     * @param wg
     * @param words
     * @param maxDepth
     * @param remainingCharacters
     * @return
     */
    public static boolean solveWordsquare(WordGrid wg, List<String> words, int maxDepth, Map<Character, Integer> remainingCharacters) {
        return solveWordsquare(wg, words, 0, maxDepth, remainingCharacters);
    }

    private static boolean solveWordsquare(WordGrid wg, List<String> words, int currentDepth, int maxDepth, Map<Character, Integer> remainingCharacters) {
        if (currentDepth == maxDepth) {
            return true; // Close the 'loop'
        }

        List<String> validWordsAtRemainingDepths = words.parallelStream()
                .filter(word -> remainingCharactersFilter(word, remainingCharacters))
                .collect(Collectors.toList());


        List<String> currentDepthWords;
        if (currentDepth != 0) {
            currentDepthWords = validWordsAtRemainingDepths.parallelStream()
                    .filter(word -> wg.validateRow(currentDepth, word.toCharArray()))
                    .collect(Collectors.toList());
        } else {
            currentDepthWords = new ArrayList<>();
            currentDepthWords.addAll(validWordsAtRemainingDepths);
        }

        for (String word : currentDepthWords) {
            wg.clearRow(currentDepth);
            Map<Character, Integer> nextDepthRemainingCharacters = MapUtils.deepCloneMap(remainingCharacters); //impove perf
            boolean done = false;
            if (wg.insertToGrid(word, currentDepth)) {
                for (char c : word.toCharArray()) {
                    int remainingCount = nextDepthRemainingCharacters.get(c) - 1;
                    if (remainingCount == 0) {
                        nextDepthRemainingCharacters.remove(c);
                    }
                    nextDepthRemainingCharacters.put(c, remainingCount);
                }
                done = solveWordsquare(wg, validWordsAtRemainingDepths, currentDepth + 1, maxDepth, nextDepthRemainingCharacters);
            }
            if (done) return true;
        }
        wg.clearRow(currentDepth);
        return false;
    }

    private static boolean remainingCharactersFilter(String word, Map<Character, Integer> remainingCharacters) {
        for (Map.Entry<Character, Integer> entry : remainingCharacters.entrySet()) {
            if (StringUtils.countMatches(word, entry.getKey()) > entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
