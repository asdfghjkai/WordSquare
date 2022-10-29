package uk.co.kaichance.wordsquare.algorithm;

import java.util.HashMap;
import java.util.Map;

public class WordGrid {

    public int[][] nums;
    public char[][] charGrid;

    public char[][] finalCharGrid;

    public Map<Integer, Integer> placeCount;


    public WordGrid(int size) {
        System.out.println("Assemble grid");
        nums = new int[size][size];
        charGrid = new char[size][size];
        finalCharGrid = new char[size][size];
        placeCount = new HashMap<>();
        int x = 1;
        for (int i = 1; i <= size; i++) {
            for (int j = i; j <= size; j++) {
                nums[i-1][j-1] = x;
                nums[j-1][i-1] = x;
                placeCount.merge(x,((i==j)?1:2),Integer::sum);
                x++;
            }
        }
        printArray();
        printPlaceCount();
    }

    public void printArray() {
        System.out.println("\n");
        for (int i = 0; i < nums.length; i++) {
            System.out.print("|");
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(String.format("%s\t|", nums[i][j]));
            }
            System.out.print("\n");
        }
    }

    public void printPlaceCount() {
        System.out.println("\n\n|Place:\t|Count:\t|");
        for (Integer i : this.placeCount.keySet()) {
            System.out.println(String.format("|%s\t\t|%s\t\t|", i, this.placeCount.get(i)));
        }
    }

    public void printFinalCharacterGrid() {
        for (int i = 0; i < finalCharGrid.length; i++) {
            System.out.print("|");
            for (int j = 0; j < finalCharGrid[0].length; j++) {
                System.out.print(finalCharGrid[i][j]);
            }
            System.out.print("\n");
        }
    }

    public boolean validate() {

        for (int i = 1; i <= nums.length - 1; i++) {
            for (int j = i; j <= nums[i].length-1; j++) {
                if (this.finalCharGrid[i][j] != this.finalCharGrid[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean insertToGrid(String chars, int row) {
        return insertToGrid(chars.toCharArray(), row);
    }

    public boolean insertToGrid(char[] chars, int row) {
        if (chars.length != this.finalCharGrid.length) {
            return false;
        }

        //get position row

        //check constraints
        for (int j = 0; j < chars.length; j++) {
            //row is i
            if (row == j) {
                //single entry, should be okay
            } else {
                //check existing places
                if (finalCharGrid[row][j] != '\u0000' && finalCharGrid[row][j] != chars[j]) {
                    return false; //chars in place, and do not match existing
                }
            }
        }
        //insert to the grid
        for (int j = 0; j < chars.length; j++) {
            finalCharGrid[row][j] = chars[j];
            finalCharGrid[j][row] = chars[j];
        }
        return true;
    }

    /**
     * Clear row using staircasing rule(?)
     * @param row
     */
    public void clearRow(int row) {
        for (int j = row; j < finalCharGrid[row].length; j++) {
            finalCharGrid[row][j] = '\u0000';
        }
    }
}
