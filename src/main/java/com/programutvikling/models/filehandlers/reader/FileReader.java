package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.exceptions.InvalidFileFormatException;
import javafx.stage.FileChooser;

import java.io.File;

public abstract class FileReader {

    /**
     * Metode for å velge en filfra maskinens filsystem
     * Exceptions skal ikke håndteres i denne metoden, men kastes til kallende metode/klasse
     * @return File
     */
    public static File getFile() throws Exception {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter jobj = new FileChooser.ExtensionFilter("jobj files (*.jobj)", "*.jobj");
        FileChooser.ExtensionFilter csv = new FileChooser.ExtensionFilter("csv files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(jobj);
        fileChooser.getExtensionFilters().add(csv);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/" + MainApp.getPROJECTFOLDER()));
        return fileChooser.showOpenDialog(null);

    }

    /**
     * Method for providing the extension of a file.
     * @param file The file we need to get the extension of
     * @return A string containing the extension formatted as: .extension
     * @throws InvalidFileFormatException Error is thrown when the file has en illegal extension
     */
    public static String getExtension(File file) throws InvalidFileFormatException {
        String[] extSplt = file.getName().split("\\.");

        if (extSplt.length < 2) {
            throw new InvalidFileFormatException("Files must be in .jobj or .csv format");
        }

        String extension = "."+extSplt[extSplt.length - 1];
        if (!(extension.equals(".jobj") ^ extension.equals(".csv"))) {
            throw new InvalidFileFormatException("Files must be in .jobj or .csv format");
        }
        return extension;
    }


    /**
     * Metode som leser data fra en fil
     * @throws Exception
     */
    public abstract Object readDataFromFile(File file) throws Exception;

    /**
     * Metode som leser data fra en URL
     * @param url Url til data som skal leses
     */
    public abstract Object readDataFromURL(String url);
}
