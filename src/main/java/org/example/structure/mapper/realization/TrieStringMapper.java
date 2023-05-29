package org.example.structure.mapper.realization;

import org.example.structure.mapper.StringMapper;
import org.example.structure.trie.realization.SimpleTrie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrieStringMapper implements StringMapper<Integer> {
    private final SimpleTrie trie = new SimpleTrie();
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
