package org.example.structure;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrieTest {
    @Test
    void simpleTest() {
        Trie trie = new Trie();
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
        words.forEach(trie::insert);
        words.sort(String::compareTo);
        assertEquals(words, trie.searchByPrefix(""));
    }
}