package uk.co.kaichance.wordsquare.algorithm;

import org.apache.commons.lang3.StringUtils;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGrid;
import uk.co.kaichance.wordsquare.util.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class responsible for holding the core logic to solve WordSquare puzzles.
 *
 * Utilizes a depth-first recursive solving algorithm, to identify WordSquare solutions,
 * using an ever decreasing dictionary of words based on criterion determined by
 * previously 'solved' rows
 */
public class CoreAlgorithm {

    private CoreAlgorithm() {
    } //Private Constructor

    /**
     * Primary static overloaded method for solving WordSquare puzzles.
     * Calls a private method by the same name, with current depth as zero to start the solve
     *
     * @param wg WordGrid - an implementation of the WordGrid interface
     * @param words A list of words to solve the puzzle, a dictionary in the desired language
     * @param maxDepth Maximum number of levels to descent (equal to the length of the desired words)
     * @param remainingCharacters Map containing characters given in the input string, and quantities
     * @return true if successful, and values are placed within wg. Otherwise, false.
     */
    public static boolean solveWordsquare(WordGrid wg, List<String> words, int maxDepth, Map<Character, Integer> remainingCharacters) {
        return solveWordsquare(wg, words, 0, maxDepth, remainingCharacters);
    }

    /**
     * Core worker method for solving WordSquare Puzzles recursively
     * @param wg WordGrid - an implementation of the WordGrid interface
     * @param words A list of words to solve the puzzle, a dictionary in the desired language - size decreases as depth increases
     * @param currentDepth Current recursive depth
     * @param maxDepth Maximum number of levels to descent (equal to the length of the desired words)
     * @param remainingCharacters Map containing characters given in the input string, and quantities
     * @return true if successful, and values are placed within wg. Otherwise, false.
     */
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

    /**
     * Filter used for determining words which remain valid at the remaining depths,
     * based on characters which have not been utilized previously
     * @param word Word to assert characters against
     * @param remainingCharacters Map of Character, and Count of Integer for number remaining
     * @return
     */
    private static boolean remainingCharactersFilter(String word, Map<Character, Integer> remainingCharacters) {
        for (Map.Entry<Character, Integer> entry : remainingCharacters.entrySet()) {
            if (StringUtils.countMatches(word, entry.getKey()) > entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
