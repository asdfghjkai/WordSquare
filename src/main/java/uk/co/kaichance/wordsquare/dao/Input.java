package uk.co.kaichance.wordsquare.dao;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println("|Char:\t|Count:\t|");
        for (Character c : characterIntegerMap.keySet()) {
            System.out.println(String.format("|%s\t\t|%s\t\t|", c, this.characterIntegerMap.get(c)));
        }
    }
}
