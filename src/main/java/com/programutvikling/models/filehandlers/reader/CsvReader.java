package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvReader extends FileReader{

    /**
     * Method for reading data from a CSV-file.
     * @param file Csv file to parse and read
     * @return An arraylist containing one string array pr line of the CSV file
     * @throws Exception
     */
    @Override
    public Object readDataFromFile(File file) throws IOException {
        BufferedReader reader = null;
        List<Object> objLst = new ArrayList<>();

        try {
            String line = "";
            reader = new BufferedReader(new java.io.FileReader(file));

            while ((line = reader.readLine()) != null) {
                String[] dataFields = line.split(",");
                objLst.add(dataFields);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        reader.close();

        return objLst;
    }


    @Override
    public Object readDataFromURL(String url) {
        return null;
    }
}
