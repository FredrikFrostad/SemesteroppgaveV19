package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FritidsboligTest {

    @Test
    public void verifySerialization() {
        Fritidsbolig f1 = new Fritidsbolig(
                3500,
                1000000,
                "Betingelser",
                "Hyttelia 5 2045 Hyttestad",
                2003,
                "Hytte",
                "Bindingsverk, tre",
                "Høy",
                88,
                800000,
                200000);

        Fritidsbolig f2 = null;
        JobjReader reader = new JobjReader();
        JobjWriter writer = new JobjWriter();
        File file = new File("testfile.jobj");

        try {
            writer.writeDataToFile(file, f1);
            f2 = (Fritidsbolig) reader.readDataFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(f1.toString(), f2.toString());
        file.delete();

        System.out.println(f1.toString());
    }
}