package uk.co.kaichance.wordsquare.algorithm;

public class WordGrid {

    public int[][] nums;
    public char[][] charGrid;
    public WordGrid(int size) {
        System.out.println("Assemble grid");
        nums = new int[size][size];
        charGrid = new char[size][size];
        int x = 1;
        for (int i = 1; i <= size; i++) {
            for (int j = i; j <= size; j++) {
                nums[i-1][j-1] = x;
                nums[j-1][i-1] = x;
                x++;
            }
        }
        printArray();
    }

    public void printArray() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print("|");
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(String.format("%s\t|", nums[i][j]));
            }
            System.out.print("\n");
        }
    }
}
