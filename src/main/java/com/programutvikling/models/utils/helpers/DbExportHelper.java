package com.programutvikling.models.utils.helpers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.*;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.writer.CsvWriter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DbExportHelper {

    private ArrayList<Forsikring> exportList;
    private ArrayList<Object>  boatList;
    private ArrayList<Object> holidayResiddenceList;
    private ArrayList<Object> travelList;
    private ArrayList<Object> villaList;
    private ArrayList<Object> clients;

    public DbExportHelper() {
        this.boatList = new ArrayList<>();
        this.holidayResiddenceList = new ArrayList<>();
        this.travelList = new ArrayList<>();
        this.villaList = new ArrayList<>();
        this.exportList = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public void exportDbAsCsv() {

        // Iterating over all clients and adding policies to list
        for (Kunde k : MainApp.getClientList()) {
            exportList.addAll(k.getForsikringer());
        }

        // Sorting policy list for cleaner looking export
        sortPolicyList(exportList);

        // Making separate arraylists for each policy type
        populatePolicyLists();

        // Writing all data to files
        try {
            writeListsToFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void populatePolicyLists() {
        for (Forsikring f : exportList) {
            System.out.println(f);
            if (f.getType().equals(ObjectType.BÅT)) boatList.add(f);
            else if (f.getType().equals(ObjectType.FRITIDSBOLIG)) holidayResiddenceList.add(f);
            else if (f.getType().equals(ObjectType.REISE)) travelList.add(f);
            else if (f.getType().equals(ObjectType.VILLAINNBO)) villaList.add(f);
        }
    }

    private void writeListsToFile() throws IOException{
        String filePath = MainApp.getDatabaseFilePath().getAbsolutePath() + File.separator;
        System.out.println(filePath);
        clients.addAll(MainApp.getClientList());

        if (MainApp.getClientList().size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "clients.csv"), clients);
        }
        if (boatList.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_boat.csv"), boatList);
        }
        if (holidayResiddenceList.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_holidayResidence"), holidayResiddenceList);
        }
        if (travelList.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_travel.csv"), travelList);
        }
        if (villaList.size() > 0) {
            new CsvWriter().writeDatabaseToFile(new File(filePath + "policy_villa.csv"), villaList);
        }
    }


    private void sortPolicyList(ArrayList<Forsikring> list) {
        // Sorting policies based on type, then on policy number
        // This is not necessary, but is done so that the exported files look cleaner
        Collections.sort(list, new Comparator<Forsikring>() {
            @Override
            public int compare(Forsikring o1, Forsikring o2) {
                int res =  o1.getType().compareTo(o2.getType());
                return res != 0 ? res : o1.getForsikrNr() - o2.getForsikrNr();
            }
        });
    }
}
