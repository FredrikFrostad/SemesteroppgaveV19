package com.programutvikling.models.filehandlers.reader;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public abstract class FileReader {

    /**
     * Metode for å velge en filfra maskinens filsystem
     * Exceptions skal ikke håndteres i denne metoden, men kastes til kallende metode/klasse
     * @return File
     */
    public File getFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(null);
    }


    /**
     * Metode som leser data fra en fil
     * @throws Exception
     */
    public void readDataFromFile(File file) throws Exception {

    }

    /**
     * Metode som leser data fra en URL
     * @param url Url til data som skal leses
     */
    public void readDataFromURL(String url) {

    }
}
