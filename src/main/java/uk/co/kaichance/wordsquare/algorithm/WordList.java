package uk.co.kaichance.wordsquare.algorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class WordList {
    public static Set<String> processList(String filePath, Set<Character> chars, int length) throws IOException {
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
