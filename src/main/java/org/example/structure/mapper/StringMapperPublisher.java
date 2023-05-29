package org.example.structure.mapper;

import java.util.function.Consumer;

public interface StringMapperPublisher<K> extends StringWithMappedValueAdder<K> {
    void produce(String word, Consumer<K> consumer);
}
