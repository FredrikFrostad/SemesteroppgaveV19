package com.programutvikling.models.inputhandler;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputhandlerTest {

    //TODO: Legg til så mange testcaser som mulig her. Jeg tester i utgangspunket ganske overfladisk

    @Test
    public void checkValidEmailFormat() {
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
            b = Inputhandler.checkValidEmailFormat(valid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(valid,b);

        b = false;
        int index = 0;
        for (String s : invalid) {

            try {
                b = Inputhandler.checkValidEmailFormat(s);
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
        boolean b = false;

        String[] valid = {
                "1",
                "12",
                "123",
                "1234",
                "12345",
                "123456",
                "1234567",
                "12345678"};
        String[] invalid = {
                " ",
                "123d345",
                "123456789",
                "asdf",
                "",
                "-12344"};

        //Checking list of valid numbers
        System.out.println("\nChecking list of valid forsikrNumbers:");
        int index = 0;
        for (String s : valid) {
            try {
                b = Inputhandler.checkValidForsikrNr(s);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            assertTrue("Failed string: " + s, b);
            System.out.println("Passed at index " + index++);
            b = false;
        }

        //Checking list of invalid numbers
        System.out.println("\nChecking list of invalid forsikrNumbers:");
        index = 0;
        b = false;
        for (String s : invalid) {
            try {
                b = Inputhandler.checkValidForsikrNr(s);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            assertFalse("Failed string: " + s, b);
            System.out.println("Passed at index " + index++);
            b = false;
        }
    }

    @Test
    public void checkValidFakturaAdresse() {
    }

    @Test
    public void main() {
    }
}