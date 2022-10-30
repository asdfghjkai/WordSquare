package uk.co.kaichance.wordsquare.dao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WordGrid {
    private char[][] finalCharGrid;

    private static final char NULL_CHAR = '\u0000';


    public WordGrid(int size) {
        log.debug("Assemble WordGrid");
        finalCharGrid = new char[size][size];
    }

    public void printFinalCharacterGrid() {
        for (int i = 0; i < finalCharGrid.length; i++) {
            String word = "";
            for (int j = 0; j < finalCharGrid[0].length; j++) {
                word += finalCharGrid[i][j];
            }
            log.info(word);
        }
    }

    public boolean insertToGrid(String chars, int row) {
        return insertToGrid(chars.toCharArray(), row);
    }

    public boolean insertToGrid(char[] chars, int row) {
        if (chars.length != this.finalCharGrid.length) {
            return false;
        }

        //check constraints
        if (validateRow(row, chars)) {
            //insert to the grid
            for (int j = 0; j < chars.length; j++) {
                finalCharGrid[row][j] = chars[j];
                finalCharGrid[j][row] = chars[j];
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
                if (finalCharGrid[row][j] != NULL_CHAR && finalCharGrid[row][j] != characters[j]) {
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
        for (int j = row; j < finalCharGrid[row].length; j++) {
            finalCharGrid[row][j] = NULL_CHAR;
            finalCharGrid[j][row] = NULL_CHAR;
        }
    }
}
