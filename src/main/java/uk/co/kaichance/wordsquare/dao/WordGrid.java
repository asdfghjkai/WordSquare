package uk.co.kaichance.wordsquare.dao;

public interface WordGrid {

    void printFinalCharacterGrid();

    boolean insertToGrid(String chars, int row);

    boolean insertToGrid(char[] chars, int row);

    boolean validateRow(int row, char[] characters);

    void clearRow(int row);

}
