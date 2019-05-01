package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class BåtTest {

    @Test
    public void verifySerialization() {
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
        Båt b2 = null;

        JobjWriter writer = new JobjWriter();
        File file = new File("testfile.jobj");
        JobjReader reader = new JobjReader();


        try {
            writer.writeObjectDataToFile(file, b1);
            b2 = (Båt)reader.readDataFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

       assertEquals(b1.toString(), b2.toString());
        file.delete();
    }



}