package uk.co.kaichance.wordsquare.dao.wordgrid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.co.kaichance.wordsquare.dao.wordgrid.impl.WordGridArrayImpl;
import uk.co.kaichance.wordsquare.dao.wordgrid.impl.WordGridMapImpl;

public class TestWordGridFactory {

    @Test
    public void testFactoryMapImpl() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.MAP, 1);
        Assertions.assertTrue(wg instanceof WordGridMapImpl);
    }

    @Test
    public void testFactoryArrayImpl() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.ARRAY, 1);
        Assertions.assertTrue(wg instanceof WordGridArrayImpl);
    }

    @Test
    public void testFactoryDefaultImpl() {
        WordGrid wg = WordGridFactory.getWordGridImpl(WordGridImplType.DEFAULT, 1);
        Assertions.assertTrue(wg instanceof WordGridArrayImpl);
    }
}
