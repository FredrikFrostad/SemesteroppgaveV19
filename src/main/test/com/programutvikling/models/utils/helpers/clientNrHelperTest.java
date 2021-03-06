package com.programutvikling.models.utils.helpers;

import com.programutvikling.mainapp.MainApp;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class clientNrHelperTest {

    File file = new File(System.getProperty("user.home") + "/" + MainApp.getPROJECTFOLDER() + "/" + ".clientNumbers");

    @Test
    public void init() {

        ClientNrHelper helper = new ClientNrHelper();
        helper.init();

        // Checking if clientNumberFile exists

        assertTrue(file.exists());

        int nr = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            nr = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(nr > 0);
    }


    @Test
    public void appendClient() {



        int int1 = -1, int2 = -1, int3 = -1;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int1 = Integer.parseInt(reader.readLine());
            reader.close();

            ClientNrHelper helper = new ClientNrHelper();
            int2 = helper.appendClient();

            reader = new BufferedReader(new FileReader(file));
            int3 = Integer.parseInt(reader.readLine());
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(int1 > 0);
        assertEquals(int2, int1 + 1);
        assertEquals(int2, int3);

    }
}