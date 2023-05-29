package org.example;

import org.example.files.FileRowReader;
import org.example.filter.*;
import org.example.searcher.AbstractRowSearcher;
import org.example.searcher.realization.CSVRowSearcherPublisher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.example.interact.CommandLineInteract.getString;

public class Main {
    private static final String EXIT_PROGRAM_PREFIX_QUERY_STRING = "!quit";
    private static final File CSVFile = Paths.get("src/main/resources/airports.csv").toFile();
    private static final String PRINT_PREFIX_TEXT = "Print prefix string > ";

    public static void main(String[] args) throws IOException {
        System.gc();
        String filterText = getString("Print your filter > ");
        Filterer filterer = new CSVRowsFilterer(filterText);
        AbstractRowSearcher searcher = new CSVRowSearcherPublisher(filterer, new FileRowReader(CSVFile));
        String prefix = getString(PRINT_PREFIX_TEXT);
        while (!prefix.equals(EXIT_PROGRAM_PREFIX_QUERY_STRING)) {
            System.gc();
            searcher.printRowsByPrefix(prefix);
            System.gc();
            prefix = getString(PRINT_PREFIX_TEXT);
        }
    }
}