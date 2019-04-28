package com.programutvikling.models.utils.helpers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class DbImportHelper extends Task {

    //TODO: This should throw exeption up to calling class, also this method is a mess, clean it up!!
    public void importDbFromCsv() {

        String filePath = MainApp.getDatabaseFilePath().getAbsolutePath() + File.separator;
        File[] dbFiles = new File(filePath).listFiles();
        ArrayList<Kunde> clientList = MainApp.getClientList();
        ArrayList<String[]> policyList = new ArrayList<>();
        CsvReader reader;
        Thread thread;

        for (File file : dbFiles) {
            try {
                if (file.getName().equals("clients.csv")) {
                    thread = new Thread(reader = new CsvReader(new File(file.getAbsolutePath())));
                    thread.start();
                    thread.join();

                    //TODO: fiks casting problemet med return value variabelen i csvreader
                    ArrayList<String[]> list = (ArrayList<String[]>) reader.getReturnValue();
                    for (String[] s : list) {
                        Kunde k = (Kunde) new CsvObjectBuilder().buildObjectFromString(s);
                        if (!clientListContains(clientList, k)) {
                            clientList.add((Kunde) new CsvObjectBuilder().buildObjectFromString(s));
                        }
                    }
                } else {
                    thread = new Thread(reader = new CsvReader(new File(file.getAbsolutePath())));
                    thread.start();
                    thread.join();
                    policyList = (ArrayList<String[]>) reader.getReturnValue();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String[] s : policyList) {
            try {
                Forsikring f = (Forsikring) new CsvObjectBuilder().buildObjectFromString(s);
                for (Kunde k : clientList) {
                    if (k.getForsikrNr() == f.getForsikrNr()) {
                        k.getForsikringer().add(f);
                    }
                }
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
