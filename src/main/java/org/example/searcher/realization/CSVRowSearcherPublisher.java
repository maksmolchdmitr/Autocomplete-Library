package org.example.searcher.realization;

import org.example.files.FileRowReader;
import org.example.filter.Filterer;
import org.example.searcher.AbstractRowSearcher;
import org.example.structure.mapper.StringMapperPublisher;
import org.example.structure.mapper.realization.StringMapperTriePublisher;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.currentTimeMillis;
import static org.example.interact.CommandLineInteract.println;

public class CSVRowSearcherPublisher extends AbstractRowSearcher {
    private static final StringMapperPublisher<Integer> stringMapperPublisher = new StringMapperTriePublisher();

    public CSVRowSearcherPublisher(Filterer filterer, FileRowReader rowReader) throws IOException {
        super(filterer, stringMapperPublisher, rowReader);
    }

    @Override
    public void printRowsByPrefix(String prefix) {
        AtomicInteger countRows = new AtomicInteger();
        long startTime = currentTimeMillis();
        stringMapperPublisher.produce(prefix, rowPosition -> {
            try {
                String row = rowReader.readRow(rowPosition);
                println(row);
                countRows.getAndIncrement();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        long endTime = currentTimeMillis();
        println(String.format("Количетсво найденных строк %d Время, затраченное на поиск: %d мс", countRows.get(), endTime - startTime));
        System.gc();
    }
}
