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

public class CsvReader extends FileReader implements Runnable{

    /**
     * Method for reading data from a CSV-file.
     * This method assumes that the first line in any csv-file is a header, this line will be skipped
     * @param file Csv file to parse and read
     * @return An arraylist containing one string array pr line of the CSV file
     * @throws Exception
     */
    //TODO: skriv dokumentasjon på tråd håndtering
    /**
     *
     */

    public  File file;
    private List<Object> returnValue = new ArrayList<>();

    public CsvReader(File file){
        this.file = file;
    }

    @Override
    public Object readDataFromFile(File file) throws IOException {
        BufferedReader reader = null;
        List<Object> objLst = new ArrayList<>();

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
        returnValue = new ArrayList<>(objLst);
        return objLst;
    }
    public void setNewFile(File file){
        this.file = file;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    @Override
    public void run() {
        try {
            readDataFromFile(file);

            System.out.println("thread for cvs read from file" );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
