package com.programutvikling.models.filehandlers.reader;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public interface FileReader {

    /**
     * Metode for å velge en filfra maskinens filsystem
     * Exceptions skal ikke håndteres i denne metoden, men kastes til kallende metode/klasse
     * @return File
     */
    public File getFile() throws Exception;


    /**
     * Metode som leser data fra en fil
     * @throws Exception
     */
    public Object readDataFromFile(File file) throws Exception;

    /**
     * Metode som leser data fra en URL
     * @param url Url til data som skal leses
     */
    public Object readDataFromURL(String url);
}
