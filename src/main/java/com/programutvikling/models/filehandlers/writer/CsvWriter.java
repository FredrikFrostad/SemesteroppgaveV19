package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

public class CsvWriter extends FileWriter {

    @Override
    public void writeDataToFile(File file, Object obj) throws IOException {

    }

    @Override
    public void writeDataToFile(File file, ArrayList<Kunde> list ) throws IOException {
        boolean isFirstLine = true;
        java.io.FileWriter fWriter = new java.io.FileWriter(file);

        for (Kunde k : list) {
            String[] objData = k.toString().split("\\,");
            String[] temp = objData[0].split("\\{");
            objData[0] = temp[1];

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
    public String getHeaderCsv(String[] data) {
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
     * Helper method for formatting datafields.
     * @param data String array containing the objects datafields
     * @return String containing comma separated datafields
     */
    public String getDataCsv(String[] data) {
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

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            MainApp.getClientList().add(new Kunde(
                    "Knut"+i,
                    "Hagen",
                    "123456",
                    "Testerudbakke 3 9989 Nordpå"));
        }

        try {
        new CsvWriter().writeDataToFile(new File("testfile.csv"), MainApp.getClientList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
