package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class JobjWriter extends FileWriter {

    @Override
    public void writeDataToFile(File file, Object obj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(obj);
        objOut.flush();
        objOut.close();
        System.out.println("Data written to file, stream closed");
    }
}
