package uk.co.kaichance.wordsquare.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for handling dictionary inputs
 */
@Slf4j
public class WordListUtils {

    private WordListUtils() {
    }

    /**
     * Attempts to resolve the file provided, and if successful, streams and filters the contents in line with the constraints provided.
     *
     * @param filePath filename
     * @param chars    Filter to ensure words only contain characters provided
     * @param length   Filter to ensure words are only of this length
     * @return List of words. Empty if an error is encountered.
     */
    public static List<String> processList(String filePath, Set<Character> chars, int length) {
        log.debug("Using Dictionary {}", filePath);
        Path path = Path.of(filePath);
        if (Files.exists(path)) {
            try (Stream<String> fileStream = Files.lines(path)) {
                return fileStream
                        .map(String::trim)
                        .filter(s -> s.length() == length)
                        .map(String::toLowerCase)
                        .filter(line -> {
                            for (Character c : line.toCharArray()) {
                                if (!chars.contains(c)) {
                                    return false;
                                }
                            }
                            return true;
                        })
                        .sorted()
                        .collect(Collectors.toList());
            } catch (IOException e) {
                log.error("Exception thrown when reading file {} - {}", filePath, e);
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }
}
