package com.programutvikling.models.inputhandlers;

import com.programutvikling.models.exceptions.*;

public class Inputvalidator {

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
        if (!splitAlpha[1].contains(".") || splitAlpha[1].contains("..") ||
                splitAlpha[0].contains("..") || splitAlpha[1].contains("@")) {
            throw new InvalidEmailException("Not a vaild email adress");
        }

        String[] splitDomain = splitAlpha[1].split(".");
        if (splitDomain.length > 0 && splitDomain[splitDomain.length - 1].contains("[0-9]")) {
            throw new InvalidEmailException(splitDomain[splitDomain.length - 1 ] + " is not a valid domain");
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

    /**
     * Method for validating the formatting of insurance policy numbers
     * @param forsikrNr String containing the number to be verified
     * @return true if the parameter String passes all checks
     * @throws InvalidNumberFormatException Exception is thrown when the parameter String is illegally formatted
     */
    public static boolean checkValidForsikrNr(String forsikrNr) throws InvalidNumberFormatException{
        //Check if the field is blank
        if (forsikrNr.isBlank()) {
            throw new InvalidNumberFormatException("This field cannot be empty. Please enter a valid forsikrNr");
        }
        //Check if is number
        if (!forsikrNr.matches("-?\\d+(\\.\\d+)?")) {
            throw new InvalidForsikrNrException("Illegal forsikrNr entered. Please enter a valid number");
        }
        //Check if positive number
        int test = Integer.parseInt(forsikrNr);
        if (test < 0) {
            throw new InvalidForsikrNrException("Illegal number entered, the forsikrNr cannot be negative");
        }
        if (forsikrNr.length() > 8) {
            throw new InvalidForsikrNrException("The number entered cannot be longer than 8 digits");
        }
        return true;
    }

    /**
     * Method for validating the billing adress
     * @param fakturaAdresse String containing the adress to be verified
     * @return true if the parameter String passes all checks
     * @throws InvalidAddressException Exception is thrown when the parameter String is illegally formatted
     */
    //TODO: implementer metodene under i checkValidFakturaAdresse()
    public static boolean checkValidFakturaAdresse(String fakturaAdresse) throws InvalidAddressException {
        String addressFormatMsg = "Adress must be on the form of. Streetname Streetnumber Zipcode Location";

        //Splitting address string on zip code
        String[] splitZip = fakturaAdresse.split("[0-9]{4}");

        //Checking location validity
        if (splitZip.length != 2 || splitZip[2].contains("[0-9]") || !splitZip[2].contains("[a-åA-Å]")) {
            throw new InvalidAddressException(addressFormatMsg);
        }

        //Checking street address validity
        String[] splitWhtSpc = splitZip[1].split(" ");
        int i = splitWhtSpc.length;
        if (!splitWhtSpc[i-1].contains("[0-9]") || splitWhtSpc[i-1].length() > 5) {
            throw new InvalidAddressException(addressFormatMsg);
        }

        return true;
    }

    public static boolean checkIfValidNumber(String numberString) throws InvalidNumberFormatException {
        if (!numberString.matches("-?\\d+(\\.\\d+)?")) {
            throw new InvalidNumberFormatException("Not a valid number");
        }
        return true;
    }

    /**
     * Methode for validating a Norwegian zip code
     * @param zip
     * @return true if the zip-code given is correct
     */
    private static boolean validateZip(String zip){

        return zip.matches("\\d{4}");
    }

    /**
     * Methode for validating address.
     * @param address
     * @return true if the address given is correct
     */
    private static boolean validateAddress(String address){

        // this regex gives validation for the format "123, Street"
        return address.matches("\\\\d+\\\\s+([a-zA-Z]+|[a-zA-Z]+\\\\s[a-zA-Z]+)");
    }

    public static void main(String[] args) {

        String s = "123234";
        System.out.println(s.matches("-?\\d+(\\.\\d+)?"));
    }
}
