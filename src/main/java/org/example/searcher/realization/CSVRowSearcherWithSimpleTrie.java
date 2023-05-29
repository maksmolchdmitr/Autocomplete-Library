package org.example.searcher.realization;

import org.example.files.FileRowReader;
import org.example.filter.Filterer;
import org.example.searcher.AbstractRowSearcher;
import org.example.structure.mapper.StringMapper;
import org.example.structure.mapper.realization.TrieStringMapper;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.currentTimeMillis;
import static org.example.interact.CommandLineInteract.println;

public class CSVRowSearcherWithSimpleTrie extends AbstractRowSearcher {
    private static final StringMapper<Integer> rowStartPositions = new TrieStringMapper();

    public CSVRowSearcherWithSimpleTrie(Filterer filterer, FileRowReader rowReader) throws IOException {
        super(filterer, rowStartPositions, rowReader);
    }

    public void printRowsByPrefix(String prefix) {
        AtomicInteger countRows = new AtomicInteger();
        long startTime = currentTimeMillis();
        rowStartPositions.map(prefix.toLowerCase()).forEach(rowPosition -> {
            try {
                String row = rowReader.readRow(rowPosition);
                countRows.getAndIncrement();
                println(row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        long endTime = currentTimeMillis();
        println(String.format("Количетсво найденных строк %d Время, затраченное на поиск: %d мс", countRows.get(), endTime - startTime));
        System.gc();
    }
}
