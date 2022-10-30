package uk.co.kaichance.wordsquare;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import uk.co.kaichance.wordsquare.algorithm.CoreAlgorithm;
import uk.co.kaichance.wordsquare.dao.InputHandler;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGrid;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGridFactory;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGridImplType;
import uk.co.kaichance.wordsquare.util.ArgumentValidation;
import uk.co.kaichance.wordsquare.util.MapUtils;
import uk.co.kaichance.wordsquare.util.WordListUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * WordSquare solver application
 * <br>
 * Assumes the following defaults
 * <ul>
 *     <li>enable1.txt exists on path</li>
 * </ul>
 * <p>
 * Requires the following parameters
 * <ul>
 *     <li><code>n abcdefghijklmnop [dictionary.txt] [array]</code></li>
 * </ul>
 * <p>
 * Optional args do the following, and must be placed sequentially if required
 * <ul>
 *     <li>[dictionary.txt] - allows the specificaiton of a custom dictionary file</li>
 *     <li>[array] - allows the use of the WordGridArrayImpl</li>
 * </ul>
 */
@Slf4j
public class Main {
    private static final String DEFAULT_FILENAME = "enable1.txt";
    private static final String PARAMETER_ARRAY = "array";

    public static void main(String[] args) throws IOException {
        log.debug("Begin WordSquare App");
        if (!ArgumentValidation.validateArgs(args)) {
            log.error("Could not validate args - exiting");
            System.exit(-1);
        } else {


            int wordSize = Integer.parseInt(args[0]);
            String inputString = args[1];
            WordGrid wg;
            if (args.length > 3 && args[3].equalsIgnoreCase(PARAMETER_ARRAY)) {
                wg = WordGridFactory.getWordGridImpl(WordGridImplType.ARRAY, wordSize);
            } else {
                wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, wordSize);
            }

            InputHandler input = new InputHandler(inputString.toLowerCase());
            String dictionaryFile = args.length > 2 ? args[2] : DEFAULT_FILENAME;
            List<String> words = WordListUtils.processList(dictionaryFile, input.getCharacterCountMap().keySet(), wordSize);
            if (CollectionUtils.isEmpty(words)) {
                log.error("No word provided - check source and criterion");
            } else {
                Map<Character, Integer> characterIntegerMap = MapUtils.deepCloneMap(input.getCharacterCountMap()); //deep clone

                if (CoreAlgorithm.solveWordsquare(wg, words, wordSize, characterIntegerMap)) {
                    log.debug("WordSquare Successfully Solved");
                } else {
                    log.error("WordSquare was unable to solve using the character provided: {}", input);
                }
                wg.printFinalCharacterGrid();
            }
        }
    }
}