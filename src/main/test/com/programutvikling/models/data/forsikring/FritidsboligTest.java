package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class FritidsboligTest {

    @Test
    public void verifySerialization() {
        Fritidsbolig f1 = new Fritidsbolig();
        f1.setForsikrNr(123456);
        try {
            f1.setAvtaleOpprettet(LocalDate.now());
        } catch (Exception e) {e.printStackTrace();}
        f1.setBetingelser("Betingelser");
        f1.setForsikringsbeløpInnbo(2000.0);
        f1.setForsikringsbeløpByggning(200000.0);
        f1.setAreal(200);
        f1.setStandard("God");
        f1.setByggemateriale("Tre");
        f1.setBoligtype("Hytte");
        f1.setAdresse("Adresse");
        f1.setPremieAnnum(20000.0);
        f1.setByggeaar(2003);


        Fritidsbolig f2 = null;
        JobjReader reader = new JobjReader();
        JobjWriter writer = new JobjWriter();
        File file = new File("testfile.jobj");

        try {
            writer.writeObjectDataToFile(file, f1);
            f2 = (Fritidsbolig) reader.readDataFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(f1.toString(), f2.toString());
        file.delete();

        System.out.println(f1.toString());
    }
}