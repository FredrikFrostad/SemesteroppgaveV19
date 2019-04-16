package com.programutvikling.models.inputhandler;

import com.programutvikling.models.exceptions.*;

public class Inputhandler {

    /**
     * Method for validating the formatting of an Email adress.
     * @param email String containing the email adress to be verified
     * @return True if the parameter String passes all checks
     * @throws InvalidEmailException Exception is thrown when the parameter String is illegally formatted
     */
    public static boolean checkValidEmailFormat(String email) throws InvalidEmailException {
        String[] splitAlpha = email.split("@");
        if (splitAlpha.length != 2) {
            throw new InvalidEmailException("Not a valid email adress, does not contain @");
        }
        if (!splitAlpha[1].contains(".") || splitAlpha[1].contains("..") || splitAlpha[0].contains("..")) {
            throw new InvalidEmailException("Not a vaild email adress");
        }
        return true;
    }

    /**
     * Method for validating the formatting of names
     * @param name String containing the name to be verified
     * @return true if the parameter String passes all checks
     * @throws InvalidNameFormatException Exception is thrown when the parameter String is illegally formatted
     */
    public static boolean checkValidNameFormat(String name) throws InvalidNameFormatException {
        if (name.length() < 2) {
            throw new InvalidNameFormatException("Names need to be at least 2 characters in length");
        }
        if (name.matches("[0-9]")) {
            throw new InvalidNameFormatException("Numbers or special characters are not allowed here");
        }
        if (name.length() > 25) {
            throw new InvalidNameFormatException("Names cannot exceed 25 characters in length");
        }
        /* TODO: Fjern denne iffen hvis den ikke trengs
        if (!name.matches("[a-åA-Å]]")) {
            throw new InvalidNameFormatException("Pleace enter a valid Name");
        }
         */
        return true;
    }

    public static boolean checkValidForsikrNr(String forsikrNr) throws InvalidNumberFormatException{
        if (forsikrNr.isBlank()) {
            throw new InvalidNumberFormatException("This field cannot be empty. Please enter a valid forsikrNr");
        }
        if (forsikrNr.matches("[a-åA-Å]") || !forsikrNr.matches("[0-9]")) {
            throw new InvalidForsikrNrException("Illegal forsikrNr entered. Please enter a valid number");
        }
        if (forsikrNr.length() > 8) {
            throw new InvalidForsikrNrException("The number entered cannot be longer than 8 digits");
        }
        return true;
    }

    public static boolean checkValidFakturaAdresse(String fakturaAdresse) throws InvalidAddressException {
        
        return true;
    }
}
