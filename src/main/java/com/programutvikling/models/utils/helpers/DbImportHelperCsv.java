package com.programutvikling.models.utils.helpers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DbImportHelperCsv{

    //TODO: This should throw exeption up to calling class, also this method is a mess, clean it up!!


    public void importDbFromCsv(String path) {

        String filePath = null;
        if (path == null) {
            filePath = MainApp.getDatabaseFilePath().getAbsolutePath() + File.separator;
        } else {
            filePath = path;
        }

        File[] dbFiles = new File(filePath).listFiles();
        ArrayList<Kunde> clientList = MainApp.getClientList();
        ArrayList<String[]> policyList;
        CsvReader reader = new CsvReader();
        CsvObjectBuilder builder = new CsvObjectBuilder();

        Arrays.sort(dbFiles);

        for (File file : dbFiles) {
            System.out.println(file.getAbsolutePath());
            try {
                if (file.getName().equals("clients.csv")) {
                    boolean startUp = false;
                    if (MainApp.getClientList().isEmpty()) {
                        startUp = true;
                    }
                    ArrayList<String[]> list = reader.readDataFromFile(new File(file.getAbsolutePath()));

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
                } else {
                    policyList = reader.readDataFromFile(new File(file.getAbsolutePath()));
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
}
