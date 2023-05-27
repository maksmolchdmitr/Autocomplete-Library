package org.example.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EconomicalTrieStringMapper implements StringMapper<Integer> {
    private final EconomicalTrie trie = new EconomicalTrie();
    private final Map<String, Integer> map = new HashMap<>(8000);

    @Override
    public List<Integer> map(String str) {
        return trie.searchByPrefix(str)
                .stream().map(map::get)
                .collect(Collectors.toList());
    }

    @Override
    public void addMappedValue(String str, Integer value) {
        trie.insert(str);
        map.put(str, value);
    }
}
