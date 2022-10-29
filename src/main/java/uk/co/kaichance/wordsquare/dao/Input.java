package uk.co.kaichance.wordsquare.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Input {

    @Getter
    private Map<Character, Integer> characterIntegerMap;

    public Input(String inputString) {
        this.characterIntegerMap = new HashMap<>();
        for (Character c : inputString.toCharArray()) {
            this.characterIntegerMap.merge(c, 1, Integer::sum);
        }
    }

    public void printMap() {
        log.trace("|Char:\t|Count:\t|");
        for (Map.Entry<Character, Integer> entry : this.characterIntegerMap.entrySet()) {
            log.trace("|{}\t\t|{}\t\t|", entry.getKey(), entry.getValue());
        }
    }
}
