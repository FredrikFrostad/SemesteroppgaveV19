package com.programutvikling.models.inputhandler;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputhandlerTest {

    //TODO: Legg til så mange testcaser som mulig her. Jeg tester i utgangspunket ganske overfladisk

    @Test
    public void checkValidEmailFormat() {
        Inputhandler handler = new Inputhandler();
        boolean b = false;

        String valid = "test.testing@testmail.com";
        String[] invalid =
        {"test.testing.testmail.com",
        "test.testing@testmail",
        "test..testing@testmail.com",
        "fdurovrj904wej8@fjdlkjfklasdf",
        "test@@testmail.com"
        };

        try {
            b = handler.checkValidEmailFormat(valid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(b);

        b = false;
        int index = 0;
        for (String s : invalid) {

            try {
                b = handler.checkValidEmailFormat(s);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            assertFalse(b);
            b = false;
            System.out.println("Passed at index " + index++);
        }
    }

    @Test
    public void checkValidNameFormat() {
    }

    @Test
    public void checkValidForsikrNr() {
    }

    @Test
    public void checkValidFakturaAdresse() {
    }

    @Test
    public void main() {
    }
}