package uk.co.kaichance.wordsquare.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler class for managing inputs, and mapping them to character count maps.
 */
@Slf4j
public class InputHandler {

    @Getter
    private Map<Character, Integer> characterCountMap;

    /**
     * Public constructor for InputHandler, maps characters in a provided string into counts, and places
     * into a map, which can be accessed by {@link #getCharacterCountMap()}
     *
     * @param inputString String of characters
     */
    public InputHandler(String inputString) {
        this.characterCountMap = new HashMap<>();
        for (Character c : inputString.toCharArray()) {
            this.characterCountMap.merge(c, 1, Integer::sum);
        }
        printMap();
    }

    /**
     * Tracer method to print the characters within a string,
     * once processed, for diagnostic purposes
     */
    public void printMap() {
        log.trace("|Char:\t|Count:\t|");
        for (Map.Entry<Character, Integer> entry : this.characterCountMap.entrySet()) {
            log.trace("|{}\t\t|{}\t\t|", entry.getKey(), entry.getValue());
        }
    }
}
