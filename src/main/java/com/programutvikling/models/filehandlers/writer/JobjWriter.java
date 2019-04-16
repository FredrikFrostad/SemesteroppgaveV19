package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import javafx.stage.FileChooser;

import java.io.*;

public class JobjWriter extends FileWriter {

    @Override
    public void writeDataToFile(File file, Object obj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(obj);
        objOut.flush();
        objOut.close();
    }
}
