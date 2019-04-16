package com.programutvikling.models.data.kunde;

import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class KundeTest {



    @Test
    public void objectIntegrityWhenSerializing() {
        Kunde kunde = new Kunde(
                "Knut",
                "Hagen",
                "123456",
                "Testerudbakke 3 9989 Nordpå");

        Båt b1 = new Båt(
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
                "150");

        kunde.getForsikringer().add(b1);
        Kunde kundeFromFile = null;
        JobjWriter writer = new JobjWriter();
        JobjReader reader = new JobjReader();
        File file = new File("testfile.jobj");

        try {
            writer.writeDataToFile(file, kunde);
            kundeFromFile = (Kunde)reader.readDataFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(kunde.toString(), kundeFromFile.toString());

        Båt b2 = (Båt)kunde.getForsikringer().get(0);
        Båt b3 = (Båt)kundeFromFile.getForsikringer().get(0);

        assertEquals(b2.toString(), b3.toString());
        System.out.println(b2.toString());
        System.out.println(b3.toString());

        file.delete();
    }
}