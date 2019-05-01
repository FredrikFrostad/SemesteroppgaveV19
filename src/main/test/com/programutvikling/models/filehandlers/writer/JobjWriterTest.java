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

        ArrayList<Kunde> original = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String name = "Knut" + i;
            original.add(new Kunde(name, "Etternavn" + i, i, "Adresse" + i));
        }

        for (Kunde k : original) {
            k.getForsikringer().add(new Båt(
                    123456,
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

        File file = new File("testfile.jobj");


        FileWriter writer = new JobjWriter();
        FileReader reader = new JobjReader();
        ArrayList<?> deserialized = null;


        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());

        try {
            writer.writeObjectDataToFile(file, original);
        } catch (Exception e) {}

        assertTrue(file.exists());

        try {
           deserialized = (ArrayList<?>) reader.readDataFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(deserialized.get(0) instanceof Kunde);

        for (int i = 0; i < original.size()-1; i++) {
            assertEquals(original.get(i).toString(), deserialized.get(i).toString());
        }

    }
}
