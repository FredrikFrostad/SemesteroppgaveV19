package com.programutvikling.models.data.kunde;

import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class KundeTest {



    @Test
    public void objectIntegrityWhenSerializing() {
        Kunde kunde = new Kunde("Knut", "Hagen", "123456", "Testerudbakke 3 9989 Nordpå");
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

    }
}