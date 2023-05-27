package org.example.structure;

import java.util.List;

public interface StringMapper<K> {
    List<K> map(String str);
    void addMappedValue(String str, K value);
}
