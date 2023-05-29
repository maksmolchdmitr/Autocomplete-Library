package org.example.structure.mapper;

import java.util.List;

public interface StringMapper<K> extends StringWithMappedValueAdder<K> {
    List<K> map(String str);
}
