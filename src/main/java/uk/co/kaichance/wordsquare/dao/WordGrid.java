package uk.co.kaichance.wordsquare.dao;

/**
 * Interface class for WordGrid solvers, to allow polymorphism around implementation at runtime
 */
public interface WordGrid {

    char NULL_CHAR = '\u0000';

    /**
     * The implementing method should output the contents of the grid, using Slf4j log methods
     */
    void printFinalCharacterGrid();

    /**
     * The implementing method should call the {@link #insertToGrid(char[], int)} method
     * @param chars Characters to place on row
     * @param row Desired row to place characters on
     * @return Success state
     */
    boolean insertToGrid(String chars, int row);

    /**
     * The implementing method should attempt to insert the supplied characters, to the
     * desired row, applying the [i][j] and [j][i] rule, returning a success state
     * @param chars Characters to place on row
     * @param row Desired row to place characters on
     * @return Success state
     */
    boolean insertToGrid(char[] chars, int row);

    /**
     * The implementing method should validate that the characters supplied can be
     * validly placed on the row desired
     * @param row
     * @param characters
     * @return
     */
    boolean validateRow(int row, char[] characters);

    /**
     * The implementing method should clear the row provided, taking note of that [i][j] and [j][i] should BOTH be cleared.
     * @param row
     */
    void clearRow(int row);

}
