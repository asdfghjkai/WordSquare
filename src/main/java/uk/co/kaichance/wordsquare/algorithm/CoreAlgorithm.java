package uk.co.kaichance.wordsquare.algorithm;

import uk.co.kaichance.wordsquare.dao.WordGrid;
import uk.co.kaichance.wordsquare.util.MapUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CoreAlgorithm {

    public static boolean solveWordsquare(WordGrid wg, Set<String> words, int maxDepth, Map<Character, Integer> remainingCharacters) {
        return solveWordsquare(wg, words, 0, maxDepth, remainingCharacters);
    }

    private static boolean solveWordsquare(WordGrid wg, Set<String> words, int currentDepth, int maxDepth, Map<Character, Integer> remainingCharacters) {
        if (currentDepth == maxDepth) {
            return true; // Close the 'loop'
        }
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        Set<String> depthWords = words.stream().filter(word ->
                {
                    characterIntegerHashMap.clear();
                    for (char c : word.toCharArray()) {
                        characterIntegerHashMap.merge(c, 1, Integer::sum);
                    }
                    for (Map.Entry<Character, Integer> entry : characterIntegerHashMap.entrySet()) {
                        if (remainingCharacters.get(entry.getKey()) < entry.getValue()) { //wg.placeCount.get(row[i])
                            return false;
                        }

                    }
                    return true;
                })
                .collect(Collectors.toSet());

        Set<String> currentDepthWords = depthWords.stream().filter(word -> wg.validateRow(currentDepth, word.toCharArray())).collect(Collectors.toSet());

        for (String word : currentDepthWords) {
            wg.clearRow(currentDepth);
            Map<Character, Integer> nextDepthRemainingCharacters = MapUtils.deepCloneMap(remainingCharacters); //impove perf
            boolean done = false;
            if (wg.insertToGrid(word, currentDepth)) {
                for (char c : word.toCharArray()) {
                    nextDepthRemainingCharacters.put(c, nextDepthRemainingCharacters.get(c) - 1);
                }
                done = solveWordsquare(wg, depthWords, currentDepth + 1, maxDepth, nextDepthRemainingCharacters);
            }
            if (done) return true;
        }
        wg.clearRow(currentDepth);
        return false;
    }
}
