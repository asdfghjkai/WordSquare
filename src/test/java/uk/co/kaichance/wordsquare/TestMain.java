package uk.co.kaichance.wordsquare;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import uk.co.kaichance.wordsquare.algorithm.WordSquareTestCases;
import uk.co.kaichance.wordsquare.dao.wordgrid.impl.WordGridMapImpl;

public class TestMain {

    @Test
    void testMainWithPositiveTestCase1() {
        Logger logger = (Logger) LoggerFactory.getLogger(WordGridMapImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        Main.main(new String[]{String.valueOf(WordSquareTestCases.POSITIVE_TEST_CASE_1.getSize()), WordSquareTestCases.POSITIVE_TEST_CASE_1.getInputString()});
        listAppender.stop();
        for (String solution : WordSquareTestCases.POSITIVE_TEST_CASE_1.getSolution()) {
            Assertions.assertTrue(listAppender.list.stream().anyMatch(log -> log.getMessage().equalsIgnoreCase(solution)));
        }
    }
}
