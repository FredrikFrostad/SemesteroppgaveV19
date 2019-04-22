package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class JobjReader extends FileReader implements Runnable{

    File file;
    Object objectArray[];

    public JobjReader(File file, Object[] objectArray){
        this.file = file;
        this.objectArray = objectArray;

    }

    @Override
    public void run() {
        try {
            this.objectArray[0] = readDataFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object readDataFromFile(File file) throws Exception {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Object loadObject = objIn.readObject();

        // Storing the objects filepath as instance variable for easy access later
        setObjectFilePath(loadObject, file);

        objIn.close();

        System.out.println("Data loaded from: " + file.getAbsolutePath() + loadObject.toString());

        return loadObject;
    }

    private void setObjectFilePath(Object obj, File file) {
        if (obj instanceof Kunde) {
            ((Kunde) obj).setFilePath(file.getPath());
        } else if(obj instanceof Forsikring) {
            ((Forsikring) obj).setFilePath(file.getPath());
        } else {
            System.out.println("Error: could not set filePath as instance variable");
        }
    }

    @Override
    public Object readDataFromURL(String url) {
        return null;
    }


}
