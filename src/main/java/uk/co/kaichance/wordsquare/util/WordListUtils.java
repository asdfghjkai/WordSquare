package uk.co.kaichance.wordsquare.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class WordListUtils {
    public static Set<String> processList(String filePath, Set<Character> chars, int length) throws IOException {
        log.debug("Using Dictionary {}", filePath);
        Set<String> words = Files.lines(Path.of(filePath))
                .filter(s -> s.length()==length).filter(line -> {
            for (Character c : line.toCharArray()) {
                if (!chars.contains(c)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toSet());
        return words;
    }
}
