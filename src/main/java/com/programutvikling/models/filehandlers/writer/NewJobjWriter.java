package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.models.filehandlers.reader.FileReader;

import java.io.File;

public class NewJobjWriter extends FileReader implements Runnable {
    @Override
    public Object readDataFromFile(File file) throws Exception {
        return null;
    }

    @Override
    public Object readDataFromURL(String url) {
        return null;
    }

    @Override
    public void run() {

    }
}
