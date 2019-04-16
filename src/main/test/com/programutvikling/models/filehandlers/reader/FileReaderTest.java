package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.exceptions.InvalidFileFormatException;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileReaderTest {

    @Test
    public void getFile() {
    }

    @Test
    public void getExtension() {
        File csv = new File("test.csv");
        File jobj = new File("test.jobj");
        File inv1 = new File("test.txt");
        File inv2 = new File("test");

        String ext = null;

        try {
            ext = FileReader.getExtension(csv);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        assertEquals(".csv",ext);

        ext = null;
        try {
            ext = FileReader.getExtension(jobj);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        assertEquals(".jobj", ext);

        ext = null;
        try {
            ext = FileReader.getExtension(inv1);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        assertNull(ext);

        ext = null;
        try {
            ext = FileReader.getExtension(inv2);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        assertNull(ext);

        csv.delete();
        jobj.delete();
        inv1.delete();
        inv2.delete();
    }
}