package com.programutvikling.models.filehandlers.reader;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class CsvReader extends FileReader{

    @Override
    public File getFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("csv"));
        return fileChooser.showOpenDialog(null);
    }

}
