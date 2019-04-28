package com.programutvikling.models.utils.helpers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DbImportHelperCsv extends Task {

    //TODO: This should throw exeption up to calling class, also this method is a mess, clean it up!!
    public void importDbFromCsv() {

        String filePath = MainApp.getDatabaseFilePath().getAbsolutePath() + File.separator;
        System.out.println("Printfilepath 1 " + filePath);
        File[] dbFiles = new File(filePath).listFiles();
        System.out.println("Antall filer: " + dbFiles.length);
        ArrayList<Kunde> clientList = MainApp.getClientList();
        ArrayList<String[]> policyList;
        CsvReader reader = new CsvReader();

        Arrays.sort(dbFiles);

        for (File file : dbFiles) {
            System.out.println(file.getAbsolutePath());
            try {
                if (file.getName().equals("clients.csv")) {
                    ArrayList<String[]> list = reader.readDataFromFile(new File(file.getAbsolutePath()));
                    for (String[] s : list) {
                        Kunde k = (Kunde) new CsvObjectBuilder().buildObjectFromString(s);
                        if (!clientListContains(clientList, k)) {
                            clientList.add((Kunde) new CsvObjectBuilder().buildObjectFromString(s));
                        }
                    }
                } else {
                    policyList = reader.readDataFromFile(new File(file.getAbsolutePath()));
                    for (String[] s : policyList) {

                        Forsikring f = (Forsikring) new CsvObjectBuilder().buildObjectFromString(s);

                        for (Kunde k : clientList) {
                            if (k.getForsikrNr() == f.getForsikrNr()) {
                                k.getForsikringer().add(f);
                            }
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Method for checking if an object is already loaded. This method works on
     * the premise no two client objects can have the same policynumber
     * @return true if the object is in the list
     */
    private boolean clientListContains(ArrayList<Kunde> list, Kunde client) {

        for (Kunde k : list) {
            if (k.getForsikrNr() == client.getForsikrNr()) return true;
        }
        return false;
    }

    @Override
    protected Object call() throws Exception {
        return null;
    }
}
