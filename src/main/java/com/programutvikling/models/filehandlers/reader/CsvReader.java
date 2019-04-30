package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CsvReader extends FileReader{

    public CsvReader() {}

    /**
     * Method for reading data from a CSV-file.
     * This method assumes that the first line in any csv-file is a header, this line will be skipped
     * @param file Csv file to parse and read
     * @return An arraylist containing one string array pr line of the CSV file
     * @throws Exception when reading of the file fails
     */
    @Override
    public ArrayList<String[]> readDataFromFile(File file) throws IOException {
        BufferedReader reader = null;
        ArrayList<String[]> objLst = new ArrayList<>();

        try {
            String line = "";
            reader = new BufferedReader(new java.io.FileReader(file));

            // Assuming the first line contains a header and skipping it
            reader.readLine();

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
}
