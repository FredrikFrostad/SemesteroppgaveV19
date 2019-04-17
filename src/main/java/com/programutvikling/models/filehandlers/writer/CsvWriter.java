package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Reise;
import com.programutvikling.models.data.kunde.Kunde;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

public class CsvWriter extends FileWriter {

    /**
     * Method for generating a csv file from a single objekt
     * @param file The file to be written
     * @param obj The objekt containing data to be written
     * @throws IOException
     */
    @Override
    public void writeDataToFile(File file, Object obj) throws IOException {
        java.io.FileWriter fWriter = new java.io.FileWriter(file);

        String[] objData = obj.toString().split("\\,");
        objData = trimHeadTail(objData);

        //Generating header
        fWriter.append(getHeaderCsv(objData));
        fWriter.append("\n");
        //TODO: remove test print
        System.out.println(getHeaderCsv(objData));

        //Generating data
        fWriter.append(getDataCsv(objData));
        fWriter.append("\n");
        //TODO: remove test print
        System.out.println(getDataCsv(objData));

        fWriter.flush();
        fWriter.close();
    }

    /**
     * Method for generating a csv file from a list of objects.
     * @param file The file to be written
     * @param list List containing all objekts to be writte
     * @throws IOException
     */
    @Override
    public void writeDataToFile(File file, ArrayList<Kunde> list ) throws IOException {
        boolean isFirstLine = true;
        java.io.FileWriter fWriter = new java.io.FileWriter(file);

        for (Kunde k : list) {
            String[] objData = k.toString().split("\\,");
            objData = trimHeadTail(objData);

            //Getting header for datafields
            if (isFirstLine) {
                //Writing header with datafield names
                fWriter.append(getHeaderCsv(objData));
                fWriter.append("\n");
                isFirstLine = false;
                //TODO: remove test print
                System.out.println(getHeaderCsv(objData));
            }
            else {
                fWriter.append(getDataCsv(objData));
                fWriter.append("\n");
                //TODO: remove test print
                System.out.println(getDataCsv(objData));
            }
        }
        fWriter.flush();
        fWriter.close();
    }

    /**
     * Helper method for making the header for the datafields in a csv file
     * @param data String array containing all datafields, this must be comma separated
     * @return A comma separated string containing all datafield names
     */
    private String getHeaderCsv(String[] data) {
        StringJoiner header = new StringJoiner(",");
        for (int i = 0; i < data.length; i++) {

            //Trimming whitespace
            data[i] = data[i].trim();

            //Building header string
            String[] tmp = data[i].split("\\=");
            header.add(tmp[0]);
        }
        return header.toString();
    }

    /**
     * Helper method for trimming garbage from head and tail end of a string split into an array
     * @param data String[] where the first and last element is to be processed
     * @return A correctly formatted String[]
     */
    private String[] trimHeadTail(String[] data) {
        String[] temp = data[0].split("\\{");
        data[0] = temp[1];

        int last = data.length -1;
        temp = data[last].split("\\}");
        data[last] = temp[0];

        return data;
    }

    /**
     * Helper method for formatting datafields.
     * @param data String array containing the objects datafields
     * @return String containing comma separated datafields
     */
    private String getDataCsv(String[] data) {
        StringJoiner out = new StringJoiner(",");

        for (int i = 0; i < data.length; i++) {

            //Trimming whitespace
            data[i] = data[i].trim();

            //Building header string
            String[] tmp = data[i].split("\\=");

            //Building out string
            out.add(tmp[1]);
        }
        return out.toString();
    }

    //TODO: Everything below is testing garbage, remove when working as planned

    public static void main(String[] args) {

        Kunde k = new Kunde("Kjell", "Olsen", "123456", "Elgfaret 11 2022 Gjerdrum");

        try {
            new CsvWriter().writeDataToFile(new File("testfileKunde.csv"), k);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
