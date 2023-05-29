package org.example.structure.mapper.realization;

import org.example.structure.mapper.StringMapperPublisher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StringMapperTriePublisherTest {
    @Test
    void simpleTest() {
        StringMapperPublisher<Integer> stringMapperPublisher = new StringMapperTriePublisher();
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
        Collections.sort(words);
        AtomicInteger ind = new AtomicInteger();
        words.forEach(s -> stringMapperPublisher.addMappedValue(s, ind.getAndIncrement()));
        List<Integer> answer = new ArrayList<>();
        IntStream.range(0, words.size()).forEach(answer::add);
        List<Integer> result = new ArrayList<>();
        stringMapperPublisher.produce("", result::add);
        assertEquals(answer, result);
    }
}