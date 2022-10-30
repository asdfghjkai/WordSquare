package uk.co.kaichance.wordsquare.dao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WordGridArrayImpl implements WordGrid {
    private char[][] finalCharGrid;
    private static final char NULL_CHAR = '\u0000';


    public WordGridArrayImpl(int size) {
        log.debug("Assemble WordGrid");
        this.finalCharGrid = new char[size][size];
    }

    public void printFinalCharacterGrid() {
        for (int i = 0; i < this.finalCharGrid.length; i++) {
            String word = "";
            for (int j = 0; j < this.finalCharGrid[0].length; j++) {
                word += this.finalCharGrid[i][j];
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
                this.finalCharGrid[row][j] = chars[j];
                this.finalCharGrid[j][row] = chars[j];
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
                if (this.finalCharGrid[row][j] != NULL_CHAR && this.finalCharGrid[row][j] != characters[j]) {
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
        for (int j = row; j < this.finalCharGrid[row].length; j++) {
            this.finalCharGrid[row][j] = NULL_CHAR;
            this.finalCharGrid[j][row] = NULL_CHAR;
        }
    }
}
