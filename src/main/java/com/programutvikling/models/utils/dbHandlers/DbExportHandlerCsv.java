package com.programutvikling.models.utils.dbHandlers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.*;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.data.skademelding.Skademelding;
import com.programutvikling.models.filehandlers.writer.CsvWriter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DbExportHandlerCsv {

    private ArrayList<Forsikring> exportListPolicy;
    private ArrayList<Object> exportListDamage;
    private ArrayList<Object>  boatList;
    private ArrayList<Object> holidayResiddenceList;
    private ArrayList<Object> travelList;
    private ArrayList<Object> villaList;
    private ArrayList<Object> clients;
    private String filePath;

    public DbExportHandlerCsv() {
        this.boatList = new ArrayList<>();
        this.holidayResiddenceList = new ArrayList<>();
        this.travelList = new ArrayList<>();
        this.villaList = new ArrayList<>();
        this.exportListPolicy = new ArrayList<>();
        this.exportListDamage = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public DbExportHandlerCsv(String filePath) {
        this.boatList = new ArrayList<>();
        this.holidayResiddenceList = new ArrayList<>();
        this.travelList = new ArrayList<>();
        this.villaList = new ArrayList<>();
        this.exportListPolicy = new ArrayList<>();
        this.exportListDamage = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * This method dumps all of the dataobjects in the program into separate csv files based on the object type.
     * The files are stored in a folder defined by the database filepath variable in the MainApp class.
     */
    public void exportDbAsCsv() {

        // Iterating over all clients and adding policies to list
        for (Kunde k : MainApp.getClientList()) {
            exportListPolicy.addAll(k.getForsikringer());
            exportListDamage.addAll(k.getSkademeldinger());
        }

        // Sorting policy list for cleaner looking export
        sortPolicyList(exportListPolicy);

        // Making separate arraylists for each policy type
        populatePolicyLists();

        // Writing all data to files
        try {
            writeListsToFile(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for iterating over all client objects and storing the
     * policy objects contained inside in separate lists
     */
    private void populatePolicyLists() {
        for (Forsikring f : exportListPolicy) {
            if (f.getType().equals(ObjectType.BÅT)) boatList.add(f);
            else if (f.getType().equals(ObjectType.FRITIDSBOLIG)) holidayResiddenceList.add(f);
            else if (f.getType().equals(ObjectType.REISE)) travelList.add(f);
            else if (f.getType().equals(ObjectType.VILLAINNBO)) villaList.add(f);
        }
    }


    /**
     * Writes the data-object lists to file. These files are used by the program for loading and saving data,
     * therefor filenames and locations are hardcoded.
     * @throws IOException
     */
    private void writeListsToFile(String filePath) throws IOException{
        if (filePath == null) {
            filePath = MainApp.getDatabaseFilePath().getAbsolutePath() + File.separator;
        } else {
            String[] path = filePath.split("\\.");
            path[0] = path[0] + File.separator;
            new File(path[0]).mkdir();
            filePath = path[0];

        }
        System.out.println(filePath);
        clients.addAll(MainApp.getClientList());

        if (MainApp.getClientList().size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "clients.csv"), clients);
        }
        if (boatList.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_boat.csv"), boatList);
        }
        if (holidayResiddenceList.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_holidayResidence.csv"), holidayResiddenceList);
        }
        if (travelList.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_travel.csv"), travelList);
        }
        if (villaList.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_villa.csv"), villaList);
        }
        if (exportListDamage.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_injuryReport.csv"), exportListDamage);
        }
    }


    /**
     * Sort clients based on policy Number.
     * This is not necessary, but id done so that the exported files look cleaner
     * @param list Arraylist of policy objects to be sorted
     */
    private void sortClientsList(ArrayList<Kunde> list) {
        Collections.sort(list, (a,b) -> {
            return  a.getForsikrNr() - b.getForsikrNr();
        });
    }

    /**
     * Sorts policies based on type, then on policy number
     * This is not necessary, but is done so that the exported files look cleaner
     * @param list Arraylist of policy Objects to be sorted
     */
    private void sortPolicyList(ArrayList<Forsikring> list) {
        Collections.sort(list, (a,b) -> {
            int res = a.getType().compareTo(b.getType());
            return res != 0 ? res : a.getForsikrNr() - b.getForsikrNr();
        });
    }
}
