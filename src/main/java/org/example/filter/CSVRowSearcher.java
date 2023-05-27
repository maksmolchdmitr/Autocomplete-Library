package org.example.filter;

import org.example.files.FileRowReader;
import org.example.structure.StringMapper;
import org.example.structure.TrieStringMapper;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.currentTimeMillis;
import static org.example.interact.CommandLineInteract.println;

public class CSVRowSearcher {
    private final StringMapper<Integer> rowStartPositions = new TrieStringMapper();
    private final FileRowReader rowReader;

    public CSVRowSearcher(Filterer filterer, FileRowReader rowReader) throws IOException {
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
                rowStartPositions.addMappedValue(values[1].replaceAll("\"", ""), currentPosition);
            }
            currentPosition = rowReader.getCurrentPosition();
            row = rowReader.readNextRow();
        }
        System.gc();
    }

    public void printRowsByPrefix(String prefix) {
        AtomicInteger countRows = new AtomicInteger();
        long startTime = currentTimeMillis();
        rowStartPositions.map(prefix).forEach(rowPosition -> {
            try {
                String row = rowReader.readRow(rowPosition);
                countRows.getAndIncrement();
                println(row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        System.gc();
        long endTime = currentTimeMillis();
        println(String.format("Количетсво найденных строк %d Время, затраченное на поиск: %d мс", countRows.get(), endTime - startTime));
    }
}
