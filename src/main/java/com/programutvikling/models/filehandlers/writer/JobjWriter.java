package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class JobjWriter extends FileWriter implements Runnable{

    File file;
    Object objectArray[];

    public JobjWriter() {
    }

    public JobjWriter(File file, Object[] objectArray) {
        this.file = file;
        this.objectArray = objectArray;
    }

    @Override
    public void writeObjectDataToFile(File file, Object obj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(obj);
        objOut.flush();
        objOut.close();
        System.out.println("Data written to file, stream closed");
    }

    @Override
    public void writeDatabaseToFile(File file, ArrayList<Object> list) throws IOException {

    }

    @Override
    public void run() {

    }
}
