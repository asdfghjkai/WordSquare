package uk.co.kaichance.wordsquare.dao.wordgrid.impl;

import lombok.extern.slf4j.Slf4j;
import uk.co.kaichance.wordsquare.dao.wordgrid.WordGrid;

/**
 * An implementation of WordGrid, utilizing arrays as the underlying structures
 */
@Slf4j
public class WordGridArrayImpl implements WordGrid {
    private char[][] finalCharGrid;
    private static final char NULL_CHAR = '\u0000';


    public WordGridArrayImpl(int size) {
        log.debug("Assemble WordGrid");
        this.finalCharGrid = new char[size][size];
    }

    /**
     * Prints characters held within the internal grid, by iterating through
     * each entry of each array
     */
    public void printFinalCharacterGrid() {
        for (int i = 0; i < this.finalCharGrid.length; i++) {
            log.info(String.valueOf(this.finalCharGrid[i]).trim());
        }
    }

    /**
     * Insert the provided string into the internal arrays
     * @param chars Characters to place on row
     * @param row Desired row to place characters on
     * @return Success state
     */
    public boolean insertToGrid(String chars, int row) {
        return insertToGrid(chars.toCharArray(), row);
    }


    /**
     * Insert the provided characters into the internal array - utilizing {@link #validateRow(int, char[])}
     * @param chars Characters to place on row
     * @param row Desired row to place characters on
     * @return Success state
     */
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

    /**
     * Ensure that the characters given are able to be inserted into the desired row using the following rules
     * <ol>
     *     <li>The existing character in null</li>
     *     <li>The existing character is equal to that which is due to be placed</li>
     * </ol>
     * @param row desired target row
     * @param characters Character array to be placed
     * @return true if insertion would be valid, otherwise false
     */
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
     * Clear row using staircasing <code>[i][j] == [j][i]</code> rule
     *
     * @param row row to be cleared
     */
    public void clearRow(int row) {
        for (int j = row; j < this.finalCharGrid[row].length; j++) {
            this.finalCharGrid[row][j] = NULL_CHAR;
            this.finalCharGrid[j][row] = NULL_CHAR;
        }
    }

    public String getRowSoFar(int row) {
        return String.valueOf(this.finalCharGrid[row]).trim();
    }
}
