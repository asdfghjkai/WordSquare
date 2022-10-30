package uk.co.kaichance.wordsquare.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class TestMapUtils {

    @Test
    void testDeepCopy() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(1, map.size());

        Map<Character, Integer> deepCopiedMap = MapUtils.deepCloneMap(map);
        Assertions.assertFalse(deepCopiedMap.isEmpty());
        Assertions.assertEquals(1, deepCopiedMap.size());

        map.put('b', 2);
        map.merge('a', 1, Integer::sum);
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(2, map.size());

        Assertions.assertEquals(2, map.get('a'));
        Assertions.assertEquals(1, deepCopiedMap.get('a'));
    }
}
