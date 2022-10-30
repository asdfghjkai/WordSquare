package uk.co.kaichance.wordsquare.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WordGridMapImpl implements WordGrid {
    private Map<Pair<Integer, Integer>, Character> characterMap;
    private int size;
    private static final char NULL_CHAR = '\u0000';


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

    public void printFinalCharacterGrid() {
        for (int i = 0; i < this.size; i++) {
            String word = "";
            for (int j = 0; j < this.size; j++) {
                word += this.characterMap.get(new ImmutablePair<>(i, j));
            }
            log.info(word);
        }
    }

    public boolean insertToGrid(String chars, int row) {
        return insertToGrid(chars.toCharArray(), row);
    }

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
     * Clear row using staircasing rule(?)
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
