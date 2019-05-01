package com.programutvikling.models.filehandlers.writer;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
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
            Båt b1 = new Båt();
            b1.setEffekt("120");
            b1.setMotorType("Innenbords");
            b1.setÅrsmodell(2003);
            b1.setLengde(20);
            b1.setModell("Princess");
            b1.setTypeBåt("Jolle");
            b1.setRegNr("BD4556");
            b1.setEier("Kjell Pettersen");
            b1.setForsikringsSum(43434);
            b1.setPremieAnum(66666);
            b1.setForsikrNr(1234);
            try {
                b1.setAvtaleOpprettet(LocalDate.now());
            }catch (Exception e) {e.printStackTrace();}
            k.getForsikringer().add(b1);
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
