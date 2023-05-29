package org.example.searcher;

import org.example.files.FileRowReader;
import org.example.filter.Filterer;
import org.example.structure.mapper.StringWithMappedValueAdder;

import java.io.IOException;

public abstract class AbstractRowSearcher {
    private final StringWithMappedValueAdder<Integer> stringWithMappedValueAdder;
    protected final FileRowReader rowReader;

    public AbstractRowSearcher(
            Filterer filterer,
            StringWithMappedValueAdder<Integer> stringWithMappedValueAdder,
            FileRowReader rowReader
    ) throws IOException {
        this.stringWithMappedValueAdder = stringWithMappedValueAdder;
        this.rowReader = rowReader;
        readFile(filterer);
    }

    private void readFile(Filterer filterer) throws IOException {
        rowReader.setStartFile();
        String row = rowReader.readNextRow();
        String[] values;
        int currentPosition = 0;
        while (row != null) {
            values = row.split(",");
            if (filterer.matches(values)) {
                stringWithMappedValueAdder.addMappedValue(
                        values[1].replaceAll("\"", "").toLowerCase(),
                        currentPosition
                );
            }
            currentPosition = rowReader.getCurrentPosition();
            row = rowReader.readNextRow();
        }
        System.gc();
    }

    public abstract void printRowsByPrefix(String prefix);
}
