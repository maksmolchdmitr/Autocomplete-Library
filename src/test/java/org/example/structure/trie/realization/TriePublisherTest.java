package org.example.structure.trie.realization;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriePublisherTest {
    @Test
    void simpleTest() {
        TriePublisher triePublisher = new TriePublisher();
        List<String> words = new java.util.ArrayList<>(List.of(
                "Sun",
                "Tree",
                "Cat",
                "House",
                "Car",
                "Sea",
                "Guitar",
                "Bird",
                "Snow"
        ));
        words = words.stream().map(String::toLowerCase).collect(Collectors.toList());
        words.forEach(triePublisher::insert);
        words.sort(String::compareTo);

        List<String> resultWords = new ArrayList<>();
        triePublisher.produceStringsByPrefix("", resultWords::add);
        assertEquals(words, resultWords);

        List<String> mustBeEmptyResult = new ArrayList<>();
        triePublisher.produceStringsByPrefix("Jakarta", mustBeEmptyResult::add);
        assertEquals(List.of(), mustBeEmptyResult);
    }
}