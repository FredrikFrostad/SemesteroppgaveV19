package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import javafx.stage.FileChooser;

import java.io.*;

public class JobjWriter implements FileWriter {
    @Override
    public File getFile() throws IOException {
        //String usrHome = System.getProperty("user.home");
        File userDir = new File(System.getProperty("user.home"));
        FileChooser fileChooser = new FileChooser();

        //Set extensionfilter for jobj files
        FileChooser.ExtensionFilter extFltr = new FileChooser.ExtensionFilter("jobj files (*.jobj)", "*.jobj");
        fileChooser.getExtensionFilters().add(extFltr);
        fileChooser.setInitialDirectory(userDir);
        fileChooser.setInitialFileName("newClient.jobj");
        return fileChooser.showSaveDialog(null);
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
