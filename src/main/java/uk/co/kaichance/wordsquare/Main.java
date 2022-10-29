package uk.co.kaichance.wordsquare;

import lombok.extern.slf4j.Slf4j;
import uk.co.kaichance.wordsquare.algorithm.CoreAlgorithm;
import uk.co.kaichance.wordsquare.dao.Input;
import uk.co.kaichance.wordsquare.dao.WordGrid;
import uk.co.kaichance.wordsquare.util.ArgumentValidation;
import uk.co.kaichance.wordsquare.util.MapUtils;
import uk.co.kaichance.wordsquare.util.WordListUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Slf4j
public class Main {
    private static final String DEFAULT_FILENAME = "enable1.txt";

    public static void main(String[] args) throws IOException {
        log.debug("Begin WordSquare App");
        if (!ArgumentValidation.validateArgs(args)) {
            log.error("Could not validate args - exiting");
            System.exit(-1);
        } else {


            int wordSize = Integer.parseInt(args[0]);
            String inputString = args[1];

            //Launch WordSquare Functionality
            WordGrid wg = new WordGrid(wordSize);
            Input input = new Input(inputString.toLowerCase());
            String dictionaryFile = args.length > 2 ? args[2] : DEFAULT_FILENAME;
            Set<String> words = WordListUtils.processList(dictionaryFile, input.getCharacterIntegerMap().keySet(), wordSize);
            Map<Character, Integer> characterIntegerMap = MapUtils.deepCloneMap(input.getCharacterIntegerMap()); //deep clone

            if (CoreAlgorithm.solveWordsquare(wg, words, wordSize, characterIntegerMap)) {
                log.debug("WordSquare Successfully Solved");
            } else {
                log.error("WordSquare was unable to solve using the character provided: {}", input);
            }
            wg.printFinalCharacterGrid();
        }
    }
}