package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JobjWriterTest {

    @Test
    public void writeDataToFile() {


        for (int i = 0; i < 10; i++) {
            String name = "Knut" + i;
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

        File file = new File("testfile.txt");


        FileWriter writer = new JobjWriter();
        FileReader reader = new JobjReader();
        ArrayList<?> deserialized = null;
        ArrayList<Kunde> original = MainApp.getClientList();

        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());

        try {
            writer.writeObjectDataToFile(file, MainApp.getClientList());
        } catch (Exception e) {}

        assertTrue(file.exists());

        try {
            deserialized = (ArrayList<?>) reader.readDataFromFile(file);
        } catch (Exception e) {}

        assertTrue(deserialized.get(0) instanceof Kunde);

        for (int i = 0; i < original.size()-1; i++) {
            assertEquals(original.get(i).toString(), deserialized.get(i).toString());
        }

    }
}
