package uk.co.kaichance.wordsquare.algorithm;

import uk.co.kaichance.wordsquare.dao.WordGrid;
import uk.co.kaichance.wordsquare.util.MapUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CoreAlgorithm {

    public static boolean solveWordsquare(WordGrid wg, List<String> words, int maxDepth, Map<Character, Integer> remainingCharacters) {
        return solveWordsquare(wg, words, 0, maxDepth, remainingCharacters);
    }

    private static boolean solveWordsquare(WordGrid wg, List<String> words, int currentDepth, int maxDepth, Map<Character, Integer> remainingCharacters) {
        if (currentDepth == maxDepth) {
            return true; // Close the 'loop'
        }
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        List<String> depthWords = words.stream().filter(word -> currentDepthFilter(word, remainingCharacters))
                .sorted()
                .collect(Collectors.toList());

        List<String> currentDepthWords;
        if (currentDepth != 0) {
            currentDepthWords = depthWords.stream().filter(word -> wg.validateRow(currentDepth, word.toCharArray())).collect(Collectors.toList());
        } else {
            currentDepthWords = new ArrayList<>();
            currentDepthWords.addAll(depthWords);
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
                done = solveWordsquare(wg, depthWords, currentDepth + 1, maxDepth, nextDepthRemainingCharacters);
            }
            if (done) return true;
        }
        wg.clearRow(currentDepth);
        return false;
    }

    private static boolean currentDepthFilter(String word, Map<Character, Integer> remainingCharacters) {
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        for (char c : word.toCharArray()) {
            if (!remainingCharacters.containsKey(c)) {
                return false;
            }
            characterIntegerMap.merge(c, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : characterIntegerMap.entrySet()) {
            if (characterIntegerMap.get(entry.getKey()) < entry.getValue()) { //wg.placeCount.get(row[i])
                return false;
            }
        }
        return true;
    }
}
