package com.programutvikling.models.data.kunde;

import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

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
            writer.writeObjectDataToFile(file, kunde);
            kundeFromFile = (Kunde)reader.readDataFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Båt b2 = (Båt)kunde.getForsikringer().get(0);
        Båt b3 = (Båt)kundeFromFile.getForsikringer().get(0);

        assertEquals(kunde.toString(), kundeFromFile.toString());
        assertEquals(b2.toString(), b3.toString());


        file.delete();
    }

    @Test
    public void setKundeOpprettet() {
        Kunde k1 = new Kunde();
        Kunde k2 = new Kunde(
                "Test",
                "Testesen",
                "123456",
                "Testeveien 11 1111 Testerud"
        );


        try {
            k1.setKundeOpprettet(LocalDate.parse("1999-12-12"));
            k2.setKundeOpprettet(LocalDate.parse("1999-12-12"));
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("1999-12-12", k1.getKundeOpprettet().toString());
        assertEquals(LocalDate.now().toString(), k2.getKundeOpprettet().toString());
    }
}