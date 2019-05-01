package com.programutvikling.models.utils.dbHandlers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.data.skademelding.Skademelding;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import java.io.File;
import java.util.*;

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
        //ArrayList<Kunde> clientList = MainApp.getClientList();
        ArrayList<Kunde> clientList = new ArrayList<>();
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

                //Building client objects
                if (file.getName().equals("clients.csv")) {
                    ArrayList<String[]> list = reader.readDataFromFile(new File(file.getAbsolutePath()));
                    buildClientObjects(list, clientList);
                    stripDuplicatesFromList(clientList);
                    MainApp.getClientList().addAll(clientList);
                }
                // Building injuryReport Objects
                else if (file.getName().equals("policy_injuryReport.csv")){
                    ArrayList<String[]>  reportList = reader.readDataFromFile(new File(file.getAbsolutePath()));
                    buildInjuryReportObjects(reportList, clientList);
                }
                // Building policy objects
                else {
                    ArrayList<String[]> policyList = reader.readDataFromFile(new File(file.getAbsolutePath()));
                    buildPolicyObjects(policyList, clientList);
                }
            }
        }
    }

    /**
     * Method for checking if an object is already loaded. This method works on
     * the premise no two client objects can have the same policynumber.
     * This method should probably be implemented using binary search, as it is very computationally
     * expensive at this point
     */
    private void stripDuplicatesFromList(ArrayList<Kunde> clientList) {
        ArrayList<Kunde> existingClients = MainApp.getClientList();

        //Sorting arrays comparing forsikrNr
        Collections.sort(clientList, Comparator.comparingInt(Kunde::getForsikrNr));
        Collections.sort(existingClients, Comparator.comparingInt(Kunde::getForsikrNr));

        // Getting index of last element in the list of existing clients, and checking that the list
        // is not empty
        int lastElement = existingClients.size()-1;
        int i = 0;
        if (lastElement >= 0) {

            // Iterating over list of new elements. For each new element we need to loop over the list of old elements
            // Since this can be computationally expensive, we break out of the nested loop as early as possible
            Iterator<Kunde> itr = clientList.iterator();
            while (itr.hasNext()) {
                int newClient = itr.next().getForsikrNr();
                if (newClient < existingClients.get(lastElement).getForsikrNr()) {
                    for (;i <= lastElement; i++) {
                        if (existingClients.get(i).getForsikrNr() == newClient) {
                            itr.remove();
                            break;
                        } else if (existingClients.get(i).getForsikrNr() > newClient) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Builds client objects read from a csv file and adds them to an arraylist.
     * @param list Arraylist containing string arrays. Each string array represents one dataobject
     * @param clientList Arraylist where the objects genereated from the csv file are stored
     * @throws Exception when file cannot be read or when encountering invalid data
     */
    private void buildClientObjects(ArrayList<String[]> list, ArrayList<Kunde> clientList) throws Exception{
        CsvObjectBuilder builder = new CsvObjectBuilder();

        // Building customer object from string and adding them to list.
        for (String[] s : list) {
                clientList.add((Kunde) builder.buildObjectFromString(s));
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