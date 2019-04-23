package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.exceptions.InvalidFileFormatException;
import com.programutvikling.models.filehandlers.FileHandler;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class FileReader extends FileHandler {

    /**
     * Metode for å velge en filfra maskinens filsystem
     * Exceptions skal ikke håndteres i denne metoden, men kastes til kallende metode/klasse
     * @return File
     */
    public static File getFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter jobj = new FileChooser.ExtensionFilter("jobj files (*.jobj)", "*.jobj");
        FileChooser.ExtensionFilter csv = new FileChooser.ExtensionFilter("csv files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(jobj);
        fileChooser.getExtensionFilters().add(csv);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/" + MainApp.getPROJECTFOLDER()));

        return fileChooser.showOpenDialog(null);
    }




    /**
     * Metode som leser data fra en fil
     * @throws Exception
     */
    public abstract Object readDataFromFile(File file) throws Exception;

}
