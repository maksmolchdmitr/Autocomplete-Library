package org.example.filter;

public class CSVRowsFilterer implements Filterer{
    private final String filterText;

    public CSVRowsFilterer(String filterText) {
        this.filterText = filterText;
    }

    @Override
    public boolean matches(Object[] args) {
        return true;
    }
}
