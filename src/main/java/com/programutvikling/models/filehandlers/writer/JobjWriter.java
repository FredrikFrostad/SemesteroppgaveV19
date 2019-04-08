package com.programutvikling.models.filehandlers.writer;

import javafx.stage.FileChooser;

import java.io.*;

public class JobjWriter implements FileWriter {
    @Override
    public File getFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("jobj"));
        return fileChooser.showOpenDialog(null);
    }

    @Override
    public void writeDataToFile(File file, Object obj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(obj);
        objOut.flush();
        objOut.close();
    }
}
