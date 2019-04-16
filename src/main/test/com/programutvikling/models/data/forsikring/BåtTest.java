package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class BåtTest {

    @Test
    public void verifySerialization() {
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
        Båt b2 = null;

        JobjWriter writer = new JobjWriter();
        JobjReader reader = new JobjReader();
        File file = new File("testfile.jobj");

        try {
            writer.writeDataToFile(file, b1);
            b2 = (Båt)reader.readDataFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(b1.toString(), b2.toString());
        file.delete();
    }



}