package uk.co.kaichance.wordsquare;

import org.apache.commons.lang3.SerializationUtils;
import uk.co.kaichance.wordsquare.algorithm.Input;
import uk.co.kaichance.wordsquare.algorithm.WordGrid;
import uk.co.kaichance.wordsquare.algorithm.WordList;
import uk.co.kaichance.wordsquare.util.MapUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello world!");

        //Launch WordSquare Functionality
        WordGrid wg = new WordGrid(4);
        //Print out words
        String x = "eeeeddoonnnsssrv";

        Input input = new Input(x.toLowerCase());
        input.printMap();
        Set<String> words = WordList.processList("enable1.txt", input.characterIntegerMap.keySet(), 4);

        //process

        //firstWords = words.stream().filter(word -> )
        String[] chosenOnes = new String[4];
        System.out.println("");

        Map<Character, Integer> characterIntegerMap = MapUtils.deepCopyMap(input.characterIntegerMap); //deep clone
        boolean success = getWordThatFits(wg, words, 4, 0, chosenOnes, characterIntegerMap);
        if (success) {
            wg.printFinalCharacterGrid();
        } else {
            System.out.println("no bueno");
            wg.printFinalCharacterGrid();
        }
    }

    public static boolean getWordThatFits(WordGrid wg, Set<String> words, int maxDepth, int currentDepth, String[] chosenOnes, Map<Character, Integer> remainingCharacters) {
        if (currentDepth == maxDepth) {
            return true; //gone too far
        }

        Set<String> depthWords;
        int[] row = wg.nums[currentDepth];
        depthWords = words.stream().filter(word ->
        {
            HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
            for (char c : word.toCharArray()) {
                characterIntegerHashMap.merge(c, 1, Integer::sum);
            }
            for (Map.Entry<Character, Integer> entry : characterIntegerHashMap.entrySet()) {
                if (remainingCharacters.get(entry.getKey()) < entry.getValue()) { //wg.placeCount.get(row[i])
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toSet());



        for (String word : depthWords) {
            wg.clearRow(currentDepth); //clear the current row
            Map<Character, Integer> remainingCharacters2 = MapUtils.deepCopyMap(remainingCharacters);
            boolean done = false;
            if (wg.insertToGrid(word, currentDepth)) {
                for (char c : word.toCharArray()) {
                    remainingCharacters2.put(c, remainingCharacters2.get(c)-1);
                }
                done = getWordThatFits(wg, depthWords, maxDepth, currentDepth+1, chosenOnes, remainingCharacters2);
            }
            if (done) return true;
        }
        return false;
    }
}