package org.example;

import org.example.files.FileRowReader;
import org.example.filter.CSVRowSearcher;
import org.example.filter.CSVRowsFilterer;
import org.example.filter.Filterer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.example.interact.CommandLineInteract.getString;

public class Main {
    private static final String EXIT_PROGRAM_PREFIX_QUERY_STRING = "!quit";
    private static final File CSVFile = Paths.get("src/main/resources/airports.csv").toFile();
    private static final String PRINT_PREFIX_TEXT = "Print prefix string > ";

    public static void main(String[] args) throws IOException {
        String filterText = getString("Print your filter > ");
        Filterer filterer = new CSVRowsFilterer(filterText);
        CSVRowSearcher searcher = new CSVRowSearcher(filterer, new FileRowReader(CSVFile));
        String prefix = getString(PRINT_PREFIX_TEXT);
        while (!prefix.equals(EXIT_PROGRAM_PREFIX_QUERY_STRING)) {
            searcher.printRowsByPrefix(prefix);
            prefix = getString(PRINT_PREFIX_TEXT);
        }
    }
}