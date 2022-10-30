package uk.co.kaichance.wordsquare.dao.wordgrid;

import uk.co.kaichance.wordsquare.dao.wordgrid.impl.WordGridArrayImpl;
import uk.co.kaichance.wordsquare.dao.wordgrid.impl.WordGridMapImpl;


/**
 * Factory class for returning WordGrid implementations
 */
public class WordGridFactory {

    private WordGridFactory() {};

    /**
     * WordGrid Factory Method
     * @param wordGridImplType Implementation Type
     * @param size size of output word
     * @return WordGrid impl of type defined (default ARRAY);
     */
    public static WordGrid getWordGridImpl(WordGridImplType wordGridImplType, int size) {
        switch(wordGridImplType) {
            case MAP:
                return new WordGridMapImpl(size);
            case ARRAY:
            default:
                return new WordGridArrayImpl(size);
        }
    }
}
