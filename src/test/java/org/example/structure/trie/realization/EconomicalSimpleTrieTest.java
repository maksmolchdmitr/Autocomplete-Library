package org.example.structure.trie.realization;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EconomicalSimpleTrieTest {
    @Test
    void simpleTest() {
        EconomicalTrie trie = new EconomicalTrie();
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