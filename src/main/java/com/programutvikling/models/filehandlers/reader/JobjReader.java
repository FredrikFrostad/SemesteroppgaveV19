package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * @author 576
 */
public class JobjReader extends FileReader{

    /**
     * Reads a serialized object from file and returns the deserialized object
     * @param file Serialized file to be read
     * @return Deserialized object
     * @throws Exception When deserialisation fails
     */
    @Override
    public Object readDataFromFile(File file) throws Exception {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Object loadObject = objIn.readObject();
        objIn.close();

        return loadObject;
    }
}
