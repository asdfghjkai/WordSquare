package uk.co.kaichance.wordsquare.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    /**
     * Create a deep copy of a map of types given
     * @param inputMap
     * @return
     */
    public static Map<Character, Integer> deepCloneMap(Map<Character, Integer> inputMap) {
        Map<Character, Integer> newMap = new HashMap<>(inputMap.size());
        for (Map.Entry<Character, Integer> entry : inputMap.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue().intValue());
        }
        return newMap;
    }
}
