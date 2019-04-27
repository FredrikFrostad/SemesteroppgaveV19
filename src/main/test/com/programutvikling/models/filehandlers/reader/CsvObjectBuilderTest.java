package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.writer.CsvWriter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CsvObjectBuilderTest {


    Kunde k1 = new Kunde(
            "Kjell",
            "Olsen",
            123456,
            "Elgfaret 11 2022 Gjerdrum");


    @Test
    public void buildObjectFromString() {
        Kunde k2 = null;

        try {
            new CsvWriter().writeObjectDataToFile(new File("testfile.csv"), k1);
            File file = new File("testfile.csv");
            CsvReader reader = new CsvReader(file);
            Thread thread = new Thread(reader);
            thread.start();
            thread.join();


            ArrayList<String[]> lst = (ArrayList<String[]>)reader.getReturnValue();
            k2 = (Kunde) new CsvObjectBuilder().buildObjectFromString(lst.get(0));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(k1.toString(), k2.toString());
        System.out.println(k1.toString());
        System.out.println(k2.toString());
    }
}