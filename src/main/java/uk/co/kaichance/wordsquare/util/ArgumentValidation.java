package uk.co.kaichance.wordsquare.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArgumentValidation {

    public static boolean validateArgs(String[] args) {
        return checkArgLength(args) && ensureWordLengthCanBeParsed(args) && correctCharactersProvided(args);
    }

    /**
     * Assert argument lengths. There should be two, however if there are more, than 2, or there is 1, the method will return true to allow further methods to assert validity.
     *
     * @param args
     * @return
     */
    private static boolean checkArgLength(String[] args) {
        if (args.length > 2) {
            log.warn("Too many args provided - will attempt regardless");
            return true;
        } else if (args.length == 1) {
            log.warn("Not enough args provided");
            return true;
        } else if (args.length == 0) {
            log.error("No args provided");
            return false;
        } else {
            return true;
        }
    }

    private static boolean ensureWordLengthCanBeParsed(String[] args) {
        try {
            Integer.parseInt(args[0]);
            return true;
        } catch (NumberFormatException e) {
            log.error("Word Length {} cannot be parsed", args[0]);
            return false;
        }
    }

    /**
     * Ensure n^2 characters are provided to the application
     *
     * @param args
     * @return
     */
    private static boolean correctCharactersProvided(String[] args) {
        int wordLength = Integer.parseInt(args[0]);
        int characterLength = args[1].length();
        return (characterLength == (wordLength * wordLength));
    }

}
