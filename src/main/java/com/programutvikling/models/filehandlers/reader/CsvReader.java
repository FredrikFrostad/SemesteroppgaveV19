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

    @Override
    public Object readDataFromFile(File file) throws Exception {
        BufferedReader reader = null;
        List<Object> objLst = new ArrayList<>();

        try {
            String line = "";
            reader = new BufferedReader(new java.io.FileReader(file));
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] dataFields = line.split(",");
                objLst.add(dataFields);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objLst;
    }

    public static void main(String[] args) {

        try {
            ArrayList<String[]> list = (ArrayList<String[]>) new CsvReader().readDataFromFile(new File("testfile.csv"));
            for (String[] arr : list) {
                for (String s : arr) {
                    System.out.print(s);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object readDataFromURL(String url) {
        return null;
    }
}
