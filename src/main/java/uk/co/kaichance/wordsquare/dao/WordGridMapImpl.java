package uk.co.kaichance.wordsquare.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of WordGrid, utilizing Maps, with immutable Pair tuples in i,j form, as the key
 */
@Slf4j
public class WordGridMapImpl implements WordGrid {
    private Map<Pair<Integer, Integer>, Character> characterMap;
    private int size;

    public WordGridMapImpl(int size) {
        log.debug("Assemble WordGrid");
        this.size = size;
        this.characterMap = new HashMap<>(size * size);
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.characterMap.put(new ImmutablePair<>(i, j), NULL_CHAR);
            }
        }
    }

    /**
     * Prints characters held within the internal grid, by iterating through
     * each entry of each array
     */
    public void printFinalCharacterGrid() {
        for (int i = 0; i < this.size; i++) {
            String word = "";
            for (int j = 0; j < this.size; j++) {
                word += this.characterMap.get(new ImmutablePair<>(i, j));
            }
            log.info(word);
        }
    }

    /**
     * Insert the provided string into the internal map
     * @param chars Characters to place on row
     * @param row Desired row to place characters on
     * @return Success state
     */
    public boolean insertToGrid(String chars, int row) {
        return insertToGrid(chars.toCharArray(), row);
    }

    /**
     * Insert the provided characters into the internal map - utilizing {@link #validateRow(int, char[])}
     * @param chars Characters to place on row
     * @param row Desired row to place characters on
     * @return Success state
     */
    public boolean insertToGrid(char[] chars, int row) {
        if (chars.length != this.size) {
            return false;
        }

        //check constraints
        if (validateRow(row, chars)) {
            //insert to the grid
            for (int j = 0; j < chars.length; j++) {
                this.characterMap.put(new ImmutablePair<>(row, j), chars[j]);
                this.characterMap.put(new ImmutablePair<>(j, row), chars[j]);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ensure that the characters given are able to be inserted into the desired row using the following rules
     * <ol>
     *     <li>The existing character in null</li>
     *     <li>The existing character is equal to that which is due to be placed</li>
     * </ol>
     * @param row desired target row
     * @param characters Character array to be placed
     * @return
     */
    public boolean validateRow(int row, char[] characters) {
        for (int j = 0; j < characters.length; j++) {
            if (row != j) {
                //check existing places
                char c = this.characterMap.get(new ImmutablePair<>(row, j));
                if (c != NULL_CHAR && c != characters[j]) {
                    return false; //chars in place, and do not match existing
                }
            }
        }
        return true;
    }

    /**
     * Clear row using staircasing <code>[i][j] == [j][i]</code> rule
     *
     * @param row
     */
    public void clearRow(int row) {
        for (int j = row; j < this.size; j++) {
            this.characterMap.put(new ImmutablePair<>(row, j), NULL_CHAR);
            this.characterMap.put(new ImmutablePair<>(j, row), NULL_CHAR);
        }
    }
}
