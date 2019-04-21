package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.IllegalFileNameException;
import com.programutvikling.models.filehandlers.FileHandler;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileWriter extends FileHandler {

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

        String filePath = fileChooser.showSaveDialog(null).getAbsolutePath();
        String[] splitExt = filePath.split("\\.");
        System.out.println("Length" + splitExt.length);
        System.out.println(fileChooser.getSelectedExtensionFilter().getDescription());

        if (splitExt.length < 2) {
            filePath += fileChooser.getSelectedExtensionFilter().getDescription();
        }

        System.out.println("NEW FILE" + filePath);
        return new File(filePath);
    }


    /**
     * Metode som skriver data til fil.
     * Exceptions skal ikke håndteres i denne klassen, men kastes til kallende metode/klasse
     * @throws IOException
     */
    public abstract void writeDataToFile(File file, Object obj) throws IOException;

    //public abstract void writeDataToFile(File file, ArrayList<Kunde> list) throws IOException;

}
