package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileWriter {

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
     * Metode som skriver data til fil.
     * Exceptions skal ikke håndteres i denne klassen, men kastes til kallende metode/klasse
     * @throws IOException
     */
    public abstract void writeDataToFile(File file, Object obj) throws IOException;

    public abstract void writeDataToFile(File file, ArrayList<Kunde> list) throws IOException;
}
