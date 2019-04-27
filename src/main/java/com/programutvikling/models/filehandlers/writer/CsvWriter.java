package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

public class CsvWriter extends FileWriter {

    /**
     * Method for generating a csv file from a single objekt
     *
     * @param file The file to be written
     * @param obj  The objekt containing data to be written
     * @throws IOException
     */
    @Override
    public void writeObjectDataToFile(File file, Object obj) throws IOException {
        java.io.FileWriter fWriter = new java.io.FileWriter(file);

        String[] objData = obj.toString().split("\\,");
        //objData = trimHeadTail(objData);

        //Generating header
        fWriter.append(getHeaderCsv(objData));
        fWriter.append("\n");

        //Generating data
        fWriter.append(getDataCsv(objData));
        fWriter.append("\n");

        fWriter.flush();
        fWriter.close();
    }


    /**
     * Method for generating a csv file from a list of objects.
     * @param file The file to be written
     * @param list List containing all objekts to be writte
     * @throws IOException
     */
    public void writeDatabaseToFile(File file, ArrayList<Object> list ) throws IOException {
        boolean isFirstLine = true;
        java.io.FileWriter fWriter = new java.io.FileWriter(file);

        for (Object o : list) {
            String[] objData = o.toString().split("\\,");

            for (String s : objData) System.out.print(objData);

            //Getting header for datafields if firstline of CSV file
            if (isFirstLine) {
                //Writing header with datafield names
                fWriter.append(getHeaderCsv(objData));
                fWriter.append("\n");
                isFirstLine = false;
            }
            // If not first line, object data is written to fields in csv file
            else {
                fWriter.append(getDataCsv(objData));
                fWriter.append("\n");
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

        if (data[0].toString().matches("^type")) return data;

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
        //System.out.println(out.toString());
        return out.toString();
    }

    //TODO: Everything below is testing garbage, remove when working as planned

    public static void main(String[] args) {

        ArrayList<Object> list = new ArrayList<>();
        list.add(new Kunde("Kjell1", "Olsen", "123456", "Elgfaret 11 2022 Gjerdrum"));
        list.add(new Kunde("Kjell2", "Olsen", "123457", "Elgfaret 11 2022 Gjerdrum"));
        list.add(new Kunde("Kjell3", "Olsen", "123458", "Elgfaret 11 2022 Gjerdrum"));
        list.add(new Kunde("Kjell4", "Olsen", "123459", "Elgfaret 11 2022 Gjerdrum"));
        list.add(new Kunde("Kjell5", "Olsen", "123460", "Elgfaret 11 2022 Gjerdrum"));
        list.add(new Kunde("Kjell6", "Olsen", "123461", "Elgfaret 11 2022 Gjerdrum"));

        ArrayList<Object> listfors = new ArrayList<>();
        listfors.add(new Båt(
                12000,
                400000,
                "Betingelser",
                "Kjell Normann",
                "BD1234",
                "Daycriuser",
                "Ibiza",
                32,
                2014,
                "Utenbords",
                "150"));
        listfors.add(new Båt(
                12000,
                400000,
                "Betingelser",
                "Kjell Normann",
                "BD1234",
                "Daycriuser",
                "Ibiza",
                32,
                2014,
                "Utenbords",
                "150"));
        listfors.add(new Båt(
                12000,
                400000,
                "Betingelser",
                "Kjell Normann",
                "BD1234",
                "Daycriuser",
                "Ibiza",
                32,
                2014,
                "Utenbords",
                "150"));
        listfors.add(new Båt(
                12000,
                400000,
                "Betingelser",
                "Kjell Normann",
                "BD1234",
                "Daycriuser",
                "Ibiza",
                32,
                2014,
                "Utenbords",
                "150"));
        try {
            new CsvWriter().writeObjectDataToFile(new File("testfileKunde.csv"), list.get(0));
            new CsvWriter().writeDatabaseToFile(new File("testfileKundeArray.csv"), list);
            new CsvWriter().writeObjectDataToFile(new File("testbåt.csv"), listfors.get(0));
            new CsvWriter().writeDatabaseToFile(new File("testbåtarray.csv"), listfors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
