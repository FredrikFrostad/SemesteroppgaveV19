package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JobjWriterTest {

    @Test
    public void writeDataToFile() {


        for (int i = 0; i < 10; i++) {
            String name = "Knut"+i;
            MainApp.getClientList().add(new Kunde(
                    name,
                    "Hagen",
                    "123456",
                    "Testerudbakke 3 9989 Nordpå"));
        }

        for (Kunde k : MainApp.getClientList()) {
            k.getForsikringer().add(new Båt(
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
        }

        File file = new File("dataAsJobj.jobj");
        FileWriter writer = new JobjWriter();
        FileReader reader = new JobjReader();
        ArrayList<Kunde> deserialized = null;
        ArrayList<Kunde> original = MainApp.getClientList();
        try {
            writer.writeDataToFile(file, MainApp.getClientList());
            deserialized = (ArrayList<Kunde>)reader.readDataFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        for (int i = 0; i < original.size() -1; i++) {
            String k1 = original.get(i).toString();
            String k2 = deserialized.get(i).toString();
            assertEquals(k1, k2);
        }

        for (Kunde kunde : original) {
            System.out.println(kunde.toString());
            for (Forsikring f : kunde.getForsikringer()) {
                System.out.println(f.toString());

            }
            System.out.println();
        }
        file.delete();
    }
}