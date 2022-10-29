package uk.co.kaichance.wordsquare.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WordGrid {

    @Getter
    private int[][] nums;
    private char[][] finalCharGrid;
    private Map<Integer, Integer> placeCount;


    public WordGrid(int size) {
        log.debug("Assemble WordGrid");
        nums = new int[size][size];
        finalCharGrid = new char[size][size];
        placeCount = new HashMap<>();
        int x = 1;
        for (int i = 1; i <= size; i++) {
            for (int j = i; j <= size; j++) {
                nums[i - 1][j - 1] = x;
                nums[j - 1][i - 1] = x;
                placeCount.merge(x, ((i == j) ? 1 : 2), Integer::sum);
                x++;
            }
        }
        printArray();
        printPlaceCount();
    }

    public void printArray() {
        for (int i = 0; i < nums.length; i++) {
            String outputString = "|";
            for (int j = 0; j < nums[0].length; j++) {
                outputString.concat(nums[i][j] + "\t|");
            }
            log.trace(outputString);
        }
    }

    public void printPlaceCount() {
        log.trace("\n\n|Place:\t|Count:\t|");
        for (Integer i : this.placeCount.keySet()) {
            log.trace("|{}\t\t|{}\t\t|", i, this.placeCount.get(i));
        }
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
            //row is i
            if (row == j) {
                //single entry, should be okay
            } else {
                //check existing places
                if (finalCharGrid[row][j] != '\u0000' && finalCharGrid[row][j] != characters[j]) {
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
            finalCharGrid[row][j] = '\u0000';
            finalCharGrid[j][row] = '\u0000';
        }
    }
}
