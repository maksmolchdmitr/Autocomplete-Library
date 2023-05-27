package org.example.structure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrieStringMapperTest {
    @Test
    void simpleTest() {
        StringMapper<Integer> mapper = new TrieStringMapper();
        List<String> words = new ArrayList<>(List.of(
                "car",
                "book",
                "milk",
                "hippopotamus",
                "computer",
                "t-shirt",
                "rain",
                "sun",
                "cat",
                "cookie"
        ));
        words.forEach(s -> mapper.addMappedValue(s, 1));
        List<Integer> answer = new ArrayList<>();
        IntStream.range(0, words.size()).forEach(i -> answer.add(1));
        assertEquals(answer, mapper.map(""));
    }

}