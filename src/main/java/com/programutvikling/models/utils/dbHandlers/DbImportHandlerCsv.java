package com.programutvikling.models.utils.dbHandlers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.data.skademelding.Skademelding;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DbImportHandlerCsv {


    /**
     * This method imports the entire database from a set of csv files. The files need to be located in the same folder,
     * and follow the naming conventions establishes by the DbExportHelper method. Otherwise this method will fail in its
     * current (quite naive) implementation.
     * @param path Path to the folder where the files are located, this can be null, which will make the method load
     *             data from the programs default database folder
     * @throws Exception when filereading fails or garbage data is read
     */
    public void importDbFromCsv(String path) throws Exception {

        String filePath = null;
        if (path == null) {
            filePath = MainApp.getDatabaseFilePath().getAbsolutePath() + File.separator;
        } else {
            filePath = path;
        }

        File[] dbFiles = new File(filePath).listFiles();
        ArrayList<Kunde> clientList = MainApp.getClientList();
        CsvReader reader = new CsvReader();
        CsvObjectBuilder builder = new CsvObjectBuilder();

        // Database csv files are named in such a way that the clientfiles
        // always come first when the file array is sorted. This is obviously a naive aproach.....
        // This could be improved by looking at the file content and making sure that the file containing client
        // data is imported first.
        Arrays.sort(dbFiles);

        for (File file : dbFiles) {
            System.out.println(file.getName());
            if (file.getName().split("\\.")[1].equals("csv")) {

                if (file.getName().equals("clients.csv")) {

                    // This boolean is set to avoid doing an expensive check for duplicate imports when the database is
                    // loaded at program start.
                    // Ideally this should not be done in this way, and we should instead implement a more effective
                    // way to check for duplicates. At the moment the check cost is at least n squared.
                    boolean startUp = false;
                    if (MainApp.getClientList().isEmpty()) {
                        startUp = true;
                    }

                    ArrayList<String[]> list = reader.readDataFromFile(new File(file.getAbsolutePath()));
                    buldClientObjects(list, startUp, clientList);

                }
                else if (file.getName().equals("policy_injuryReport.csv")){
                    ArrayList<String[]>  reportList = reader.readDataFromFile(new File(file.getAbsolutePath()));
                    buildInjuryReportObjects(reportList, clientList);
                } else {
                    ArrayList<String[]> policyList = reader.readDataFromFile(new File(file.getAbsolutePath()));
                    buildPolicyObjects(policyList, clientList);
                }
            }
        }
    }

    /**
     * Method for checking if an object is already loaded. This method works on
     * the premise no two client objects can have the same policynumber
     * This method is usually called in a loop, and is obviously very slow. If the parameter list
     * is sorted this can be done using binary search to greatly improve performance. 
     * @return true if the object is in the list
     */
    private boolean clientListContains(ArrayList<Kunde> list, Kunde client) {

        for (Kunde k : list) {
            if (k.getForsikrNr() == client.getForsikrNr()) return true;
        }
        return false;
    }

    /**
     * Builds client objects read from a csv file and adds them to an arraylist.
     * @param list Arraylist containing string arrays. Each string array represents one dataobject
     * @param startUp boolean used as a flag to skip duplicate check on program startup, as this is
     *                extremely computationaly expensive on large datasets
     * @param clientList Arraylist where the objects genereated from the csv file are stored
     * @throws Exception when file cannot be read or when encountering invalid data
     */
    private void buldClientObjects(ArrayList<String[]> list, boolean startUp, ArrayList<Kunde> clientList) throws Exception{
        CsvObjectBuilder builder = new CsvObjectBuilder();

        // Building customer object from string and adding them to list.
        for (String[] s : list) {
            Kunde k = (Kunde) builder.buildObjectFromString(s);

            // This procedure is horribly slow when adding large datasets.
            // Using startUp flag to skip duplicate check when populating clientobject arraylist
            // on program startup
            if (startUp) {
                clientList.add((Kunde) builder.buildObjectFromString(s));
            } else if (!clientListContains(clientList, k)) {
                clientList.add((Kunde) builder.buildObjectFromString(s));
            }
        }
    }

    /**
     * Builds policy objects read from a csv file and adds them to an arraylist
     * @param policyList Arraylist containing string arrays. Each string array represents one dataobject
     * @param clientList Arraylist where the objects genereated from the csv file are stored
     * @throws Exception when file cannot be read or when encountering invalid data
     */
    private void buildPolicyObjects(ArrayList<String[]> policyList, ArrayList<Kunde> clientList) throws Exception {
        for (String[] s : policyList) {

            Forsikring f = (Forsikring) new CsvObjectBuilder().buildObjectFromString(s);

            for (Kunde k : clientList) {
                if (k.getForsikrNr() == f.getForsikrNr()) {
                    k.getForsikringer().add(f);
                    break;
                }
            }
        }
    }

    private void buildInjuryReportObjects(ArrayList<String[]> reportList, ArrayList<Kunde> clientList) throws Exception {
        for (String [] s : reportList) {

            Skademelding skade = (Skademelding)new CsvObjectBuilder().buildObjectFromString(s);

            for (Kunde k : clientList) {
                if (k.getForsikrNr() == skade.getForsikrNr()) {
                    k.getSkademeldinger().add(skade);
                }
            }
        }
    }
}
