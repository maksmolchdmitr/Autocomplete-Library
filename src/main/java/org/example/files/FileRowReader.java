package org.example.files;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileRowReader implements Closeable {
    private final RandomAccessFile randomAccessFile;

    public FileRowReader(File file) throws IOException {
        randomAccessFile = new RandomAccessFile(file, "r");
    }

    public String readRow(int rowPosition) throws IOException {
        randomAccessFile.seek(rowPosition);
        return randomAccessFile.readLine();
    }

    public void setStartFile() throws IOException {
        randomAccessFile.seek(0);
    }

    public String readNextRow() throws IOException {
        return randomAccessFile.readLine();
    }

    public int getCurrentPosition() throws IOException {
        return (int) randomAccessFile.getFilePointer();
    }

    @Override
    public void close() throws IOException {
        this.randomAccessFile.close();
    }
}
