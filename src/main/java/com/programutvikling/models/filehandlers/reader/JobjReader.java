package com.programutvikling.models.filehandlers.reader;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class JobjReader implements FileReader {
    @Override
    //TODO: Se på unntak i denne metoden, tror ikke det kastes noen av Filechooser, mulig vi må implementere egne...
    public File getFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("jobj"));
        return fileChooser.showOpenDialog(null);
    }

    @Override
    public Object readDataFromFile(File file) throws Exception {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Object loadObject = objIn.readObject();
        objIn.close();

        System.out.println("Data loaded from: " + file.getAbsolutePath() + loadObject.toString());

        return loadObject;
    }

    @Override
    public Object readDataFromURL(String url) {
        return null;
    }

}
