package org.example.structure.mapper.realization;

import org.example.structure.mapper.StringMapperPublisher;
import org.example.structure.trie.realization.TriePublisher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StringMapperTriePublisher implements StringMapperPublisher<Integer> {
    TriePublisher triePublisher = new TriePublisher();
    Map<String, Integer> map = new HashMap<>();

    @Override
    public void produce(String word, Consumer<Integer> consumer) {
        triePublisher.produceStringsByPrefix(word, s -> consumer.accept(map.get(s)));
    }

    @Override
    public void addMappedValue(String str, Integer value) {
        triePublisher.insert(str);
        map.put(str, value);
    }
}
