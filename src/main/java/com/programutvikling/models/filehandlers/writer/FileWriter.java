package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.filehandlers.ExtensionHandler;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileWriter extends ExtensionHandler {

    /**
     * Metode for å velge en filfra maskinens filsystem
     * Exceptions skal ikke håndteres i denne metoden, men kastes til kallende metode/klasse
     * @return File
     */
    public static File getFile() throws Exception {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter jobj = new FileChooser.ExtensionFilter(".jobj", "*.jobj");
        FileChooser.ExtensionFilter csv = new FileChooser.ExtensionFilter(".csv", "*.csv");
        fileChooser.getExtensionFilters().add(jobj);
        fileChooser.getExtensionFilters().add(csv);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/" + MainApp.getPROJECTFOLDER()));

        // Dersom ingen fileextension er satt, generers denne utifra valt extensionfilter
        String filePath = fileChooser.showSaveDialog(null).getAbsolutePath();
        String[] splitExt = filePath.split("\\.");

        if (splitExt.length < 2) {
            filePath += fileChooser.getSelectedExtensionFilter().getDescription();
        }

        return new File(filePath);
    }


    /**
     * Metode som skriver et enkelt objekts data til fil.
     * Exceptions skal ikke håndteres i denne klassen, men kastes til kallende metode/klasse
     * @throws IOException
     */
    public abstract void writeObjectDataToFile(File file, Object obj) throws IOException;

    //public abstract void writeObjectDataToFile(File file, ArrayList<Kunde> list) throws IOException;

    public abstract void writeDatabaseToFile(File file, ArrayList<Object> list) throws IOException;

}
